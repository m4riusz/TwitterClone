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
        public currentUser:TwitterClone.Models.User;
        public selectedUser:TwitterClone.Models.User;
        public followers:TwitterClone.Models.User[];
        public followingUsers:TwitterClone.Models.User[];

        constructor($scope:ng.IScope, userService:TwitterClone.Services.IUserService) {
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
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        editUserRole(user:TwitterClone.Models.User) {
            this.userService.editUserRole(user, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        banUserById(userId:number) {
            this.userService.banUserById(userId, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        unbanUserById(userId:number) {
            this.userService.unbanUserById(userId, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        createUser(user:TwitterClone.Models.User) {
            this.userService.createUser(user, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        followUser(user:TwitterClone.Models.User) {
            this.userService.followUser(user, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }

        unfollowUser(user:TwitterClone.Models.User) {
            this.userService.unfollowUser(user, result => {
                if (result == false) {
                    alert("Nie udalo sie!");
                }
            });
        }
    }
}