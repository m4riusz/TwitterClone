/**
 * Created by mariusz on 17.02.16.
 */

app.controller("ProfileCtrl", function ($scope, $routeParams, UserService, TweetService) {
    $scope.currentUser = {};
    $scope.currentUserTweets = [];
    $scope.currentUserFollowers = [];
    $scope.currentUserFollowings = [];

    $scope.getCurrentUser = function () {
        UserService.getUserById($routeParams.userId)
            .success(function (user) {
                $scope.currentUser = user;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            })
    };

    $scope.getCurrentUserTweets = function () {
        TweetService.getTweetsByUserId($routeParams.userId)
            .success(function (tweets) {
                $scope.currentUserTweets = tweets;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.getCurrentUserFollowers = function () {
        UserService.getFollowersByUserId($routeParams.userId)
            .success(function (followers) {
                $scope.currentUserFollowers = followers;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.getCurrentUserFollowings = function () {
        UserService.getFollowingUsersByUserId($routeParams.userId)
            .success(function (followingUsers) {
                $scope.currentUserFollowings = followingUsers;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };
});