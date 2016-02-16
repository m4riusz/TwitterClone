app.controller('UserCtrl', function ($scope, UserService) {
    $scope.users = [];
    $scope.userDetail = {};
    $scope.followers = [];
    $scope.following = [];

    $scope.getUsers = function () {
        UserService.getAllUsers()
            .success(function (data) {
                $scope.users = data;
                if (data.length > 1) {
                    $scope.userDetail = data[0];
                }
            })
            .error(function (response) {
                console.error(response);
            });
    };

    $scope.showUser = function (userId) {
        UserService.getUserById(userId)
            .success(function (data) {
                $scope.userDetail = data;
            })
            .error(function (response) {
                console.error(response);
            })
    };

    $scope.follow = function (user) {
        UserService.follow(user)
            .success(function (user) {
                $scope.following.push(user);

                var index = $scope.users.indexOf(user);
                if (index > -1) {
                    $scope.users.splice(index, 1);
                }
            })
            .error(function (response) {
                console.error(response);
            });
    };

    $scope.unfollow = function (user) {
        UserService.unfollow(user)
            .success(function () {
                var index = $scope.following.indexOf(user);
                if (index > -1) {
                    $scope.following.splice(index, 1);
                }
            })
            .error(function (response) {
                console.error(response);
            })
    };

    $scope.getFollowers = function () {
        UserService.getFollowers()
            .success(function (data) {
                $scope.followers = data;
            })
            .error(function (response) {
                console.error(response);
            });
    };

    $scope.getFollowing = function () {
        UserService.getFollowingUsers()
            .success(function (data) {
                $scope.following = data;
            })
            .error(function (response) {
                console.error(response);
            })
    };

});
