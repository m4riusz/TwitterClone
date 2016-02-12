app.controller('UserCtrl', function ($scope, UserService) {
    $scope.users = [];
    $scope.userDetail = {};

    UserService.getAllUsers()
        .success(function (data) {
            $scope.users = data;
            if (data.length > 1) {
                $scope.userDetail = data[0];
            }
        }).error(function (response) {
        console.error(response);
    });

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
            .error(function (response) {
                console.error(response);
            });
    };
});
