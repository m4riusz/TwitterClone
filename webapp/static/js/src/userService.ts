/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="model.ts"/>
///<reference path="../typings/main.d.ts"/>
///<reference path="apiUrl.ts"/>


module TwitterClone.Services {

    import getUserById = TwitterClone.Urls.getUserById;
    export interface IUserService {
        getAllUsers(callback:(data:TwitterClone.Models.User[])=>void);
        getCurrentUser(callback:(data:TwitterClone.Models.User)=>void);
        getUserById(userId:number, callback:(data:TwitterClone.Models.User)=>void);
        getFollowersFromUserByUserId(userId:number, callback:(data:TwitterClone.Models.User[])=>void);
        getFollowingUsersFromUserByUserId(userId:number, callback:(data:TwitterClone.Models.User[])=>void);

        editUserPassword(user:TwitterClone.Models.User, callback:(result:boolean)=>void);
        editUserRole(user:TwitterClone.Models.User, callback:(result:boolean)=>void);
        banUserById(userId:number, callback:(result:boolean)=>void);
        unbanUserById(userId:number, callback:(result:boolean)=>void);
        createUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void);
        followUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void);
        unfollowUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void);
    }

    export class UserService implements IUserService {

        private http:ng.IHttpService;

        constructor($http:ng.IHttpService) {
            this.http = $http;
        }

        public getAllUsers(callback:(data:TwitterClone.Models.User[]) =>void) {
            return this.http.get(TwitterClone.Urls.getAllUsers)
                .success((data:TwitterClone.Models.User[]) => {
                    callback(data);
                    return data;
                })
                .error((error) => {
                    callback(error);
                    return error;
                });
        }

        public getCurrentUser(callback:(data:TwitterClone.Models.User)=>void) {
            return this.http.get(TwitterClone.Urls.getCurrentUser)
                .success((data:TwitterClone.Models.User)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })
        }

        public getUserById(userId:number, callback:(data:TwitterClone.Models.User)=>void) {
            this.http.get(TwitterClone.Urls.getUserById(userId))
                .success((data:TwitterClone.Models.User)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getFollowersFromUserByUserId(userId:number, callback:(data:TwitterClone.Models.User[])=>void) {
            this.http.get(TwitterClone.Urls.getFollowersFromUserByUserId(userId))
                .success((data:TwitterClone.Models.User[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })

        }

        public getFollowingUsersFromUserByUserId(userId:number, callback:(data:TwitterClone.Models.User[])=>void) {
            this.http.get(TwitterClone.Urls.getFollowingUsersFromUserByUserId(userId))
                .success((data:TwitterClone.Models.User[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public editUserPassword(user:TwitterClone.Models.User, callback:(result:boolean)=>void) {
            this.http.put(TwitterClone.Urls.editUserPassword, user)
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public editUserRole(user:TwitterClone.Models.User, callback:(result:boolean)=>void) {
            this.http.post(TwitterClone.Urls.editUserRole, user)
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public banUserById(userId:number, callback:(result:boolean)=>void) {
            this.http({
                    method: "POST",
                    url: TwitterClone.Urls.editUserEnable(userId),
                })
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public unbanUserById(userId:number, callback:(result:boolean)=>void) {
            this.http({
                    method: "PUT",
                    url: TwitterClone.Urls.editUserEnable(userId),
                })
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public createUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void) {
            this.http.post(TwitterClone.Urls.createUser, user)
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public followUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void) {
            this.http.post(TwitterClone.Urls.followUser, user)
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }

        public unfollowUser(user:TwitterClone.Models.User, callback:(result:boolean)=>void) {
            this.http.delete(TwitterClone.Urls.unfollowUser, user)
                .success((result)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false;
                });
        }
    }
}