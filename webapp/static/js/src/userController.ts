/**
 * Created by mariusz on 13.04.16.
 */

module TwitterClone.Controllers {

    export class UserController {

        private scope:ng.IScope;
        private userService:Services.IUserService;
        public users:Models.User[];


        constructor(scope:angular.IScope, userService:Services.IUserService) {
            this.scope = scope;
            this.userService = userService;
        }

        public getUsers() {
            this.userService.getAllUsers(result => {
                this.users = result;
            })
        }
    }
}