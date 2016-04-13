/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="model.ts"/>
///<reference path="../typings/main.d.ts"/>
var TwitterClone;
(function (TwitterClone) {
    var Services;
    (function (Services) {
        class UserService {
            constructor(http) {
                this.http = http;
            }
            getAllUsers(callback) {
                return this.http.get("/rest/user")
                    .success((data) => {
                    callback(data);
                    return data;
                }).error((error) => {
                    callback(error);
                    return error;
                });
            }
        }
        Services.UserService = UserService;
    })(Services = TwitterClone.Services || (TwitterClone.Services = {}));
})(TwitterClone || (TwitterClone = {}));
