/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="model.ts"/>
///<reference path="../typings/main.d.ts"/>
///<reference path="apiUrl.ts"/>


module TwitterClone.Services {

    export interface IUserService {
        getAllUsers(callback:(data:Models.User[])=>void);
        getCurrentUser(callback:(data:Models.User)=>void);
        getUserById(userId:number, callback:(data:Models.User)=>void);
        getFollowersFromUserByUserId(userId:number, callback:(data:Models.User[])=>void);
        getFollowingUsersFromUserByUserId(userId:number, callback:(data:Models.User[])=>void);

        editUserPassword(user:Models.User, callback:(result:boolean)=>void);
        editUserRole(user:Models.User, callback:(result:boolean)=>void);
        banUserById(userId:number, callback:(result:boolean)=>void);
        unbanUserById(userId:number, callback:(result:boolean)=>void);
        createUser(user:Models.User, callback:(result:boolean)=>void);
        followUser(user:Models.User, callback:(result:boolean)=>void);
        unfollowUser(user:Models.User, callback:(result:boolean)=>void);
    }

    export class UserService implements IUserService {

        private http:ng.IHttpService;

        constructor($http:ng.IHttpService) {
            this.http = $http;
        }

        public getAllUsers(callback:(data:Models.User[]) =>void) {
            return this.http.get(Urls.getAllUsers)
                .success((data:Models.User[]) => {
                    callback(data);
                    return data;
                })
                .error((error) => {
                    callback(error);
                    return error;
                });
        }

        public getCurrentUser(callback:(data:Models.User)=>void) {
            return this.http.get(Urls.getCurrentUser)
                .success((data:Models.User)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })
        }

        public getUserById(userId:number, callback:(data:Models.User)=>void) {
            this.http.get(Urls.getUserById(userId))
                .success((data:Models.User)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getFollowersFromUserByUserId(userId:number, callback:(data:Models.User[])=>void) {
            this.http.get(Urls.getFollowersFromUserByUserId(userId))
                .success((data:Models.User[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })

        }

        public getFollowingUsersFromUserByUserId(userId:number, callback:(data:Models.User[])=>void) {
            this.http.get(Urls.getFollowingUsersFromUserByUserId(userId))
                .success((data:Models.User[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public editUserPassword(user:Models.User, callback:(result:boolean)=>void) {
            this.http.put(Urls.editUserPassword, user)
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public editUserRole(user:Models.User, callback:(result:boolean)=>void) {
            this.http.post(Urls.editUserRole, user)
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public banUserById(userId:number, callback:(result:boolean)=>void) {
            this.http({
                    method: "POST",
                    url: Urls.editUserEnable(userId),
                })
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public unbanUserById(userId:number, callback:(result:boolean)=>void) {
            this.http({
                    method: "PUT",
                    url: Urls.editUserEnable(userId),
                })
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public createUser(user:Models.User, callback:(result:boolean)=>void) {
            this.http.post(Urls.createUser, user)
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public followUser(user:Models.User, callback:(result:boolean)=>void) {
            this.http.post(Urls.followUser, user)
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public unfollowUser(user:Models.User, callback:(result:boolean)=>void) {
            this.http.delete(Urls.unfollowUser, user)
                .success((result:boolean)=> {
                    callback(result as boolean);
                })
                .error((error)=> {
                    console.error(error);
                });
        }
    }
}