/**
 * Created by mariusz on 13.04.16.
 */

///<reference path="userService.ts"/>
///<reference path="../typings/main.d.ts"/>


module TwitterClone.Controllers {

    export class UserController {

        private scope:ng.IScope;
        private userService:TwitterClone.Services.IUserService;
        public users:TwitterClone.Models.User[];


        constructor($scope:ng.IScope, userService:TwitterClone.Services.IUserService) {
            this.scope = $scope;
            this.userService = userService;
        }

        public getAllUsers() {
            this.userService.getAllUsers(result => {
                console.log("AAAAAAA" + result);
                this.users = result;
            })
        }
    }
}