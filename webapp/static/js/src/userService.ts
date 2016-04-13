/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="model.ts"/>
///<reference path="../typings/main.d.ts"/>


module TwitterClone.Services {

    export interface IUserService {
        getAllUsers(callback:(data:TwitterClone.Models.User[])=>void);
    }

    export class UserService implements IUserService {

        private http:ng.IHttpService;

        constructor(http:ng.IHttpService) {
            this.http = http;
        }

        getAllUsers(callback:(data:TwitterClone.Models.User[]) =>void) {
            return this.http.get(TwitterClone.Urls.getAllUsers)
                .success((data:TwitterClone.Models.User[]) => {
                    callback(data);
                    return data;
                }).error((error) => {
                    callback(error);
                    return error;
                });
        }
    }
}