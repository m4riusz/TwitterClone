/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Controllers {

    export class UserController {

        private scope:ng.IScope;
        private userService:TwitterClone.Services.IUserService;
        public users:TwitterClone.Models.User[];


        constructor(scope:angular.IScope, userService:TwitterClone.Services.IUserService) {
            this.scope = scope;
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