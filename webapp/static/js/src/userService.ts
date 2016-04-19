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
    }

    export class UserService implements IUserService {

        private http:ng.IHttpService;

        constructor(http:ng.IHttpService) {
            this.http = http;
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
            return this.http.head(TwitterClone.Urls.getCurrentUser)
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
    }
}