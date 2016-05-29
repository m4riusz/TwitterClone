/**
 * Created by mariusz on 13.04.16.
 */

///<reference path="userService.ts"/>
///<reference path="../typings/main.d.ts"/>


module TwitterClone.Controllers {

    export class UserController {
        private scope:ng.IScope;
        private userService:TwitterClone.Services.IUserService;
        private routeParams:ng.route.IRouteParamsService;
        public users:TwitterClone.Models.User[];
        public currentUser:TwitterClone.Models.User;
        public selectedUser:TwitterClone.Models.User;
        public followers:TwitterClone.Models.User[];
        public followingUsers:TwitterClone.Models.User[];

        constructor($routeParams:ng.route.IRouteParamsService, $scope:ng.IScope, userService:TwitterClone.Services.IUserService) {
            this.routeParams = $routeParams;
            this.scope = $scope;
            this.userService = userService;
        }

        getAllUsers() {
            this.userService.getAllUsers(result => {
                this.users = result;
            });
        }

        getCurrentUser() {
            this.userService.getCurrentUser(result=> {
                this.currentUser = result;
            });
        }

        getUserById(userId:number) {
            this.userService.getUserById(userId, result=> {
                this.selectedUser = result;
            });
        }

        getFollowersFromUserByUserId(userId:number) {
            this.userService.getFollowersFromUserByUserId(userId, result=> {
                this.followers = result;
            });
        }

        getFollowingUsersFromUserByUserId(userId:number) {
            this.userService.getFollowingUsersFromUserByUserId(userId, result=> {
                this.followingUsers = result;
            });
        }

        editUserPassword(user:TwitterClone.Models.User) {
            this.userService.editUserPassword(user, result=> {
                console.log(result);
            });
        }

        editUserRole(user:TwitterClone.Models.User) {
            this.userService.editUserRole(user, result => {
                console.log(result);
            });
        }

        banUserById(userId:number) {
            this.userService.banUserById(userId, result => {
                console.log(result);
                this.getUserById(userId);
            });
        }

        unbanUserById(userId:number) {
            this.userService.unbanUserById(userId, result => {
                console.log(result);
                this.getUserById(userId);
            });
        }

        createUser(user:TwitterClone.Models.User) {
            this.userService.createUser(user, result => {
                console.log(result);
            });
        }

        followUser(userId:number) {
            this.userService.followUser(userId, result => {
                console.log(result);
                this.getFollowersFromUserByUserId(userId);
            });
        }

        unfollowUser(userId:number) {
            this.userService.unfollowUser(userId, result => {
                console.log(result);
                this.getFollowersFromUserByUserId(userId);
            });
        }
    }
}