/**
 * Created by mariusz on 16.02.16.
 */

app.controller("CommentCtrl", function ($scope, $routeParams, TweetService) {
    $scope.tweetComments = [];
    $scope.comment = "";
    $scope.tweet = {};

    $scope.getCommentedTweet = function () {
        TweetService.getTweetById($routeParams.tweetId)
            .success(function (data) {
                $scope.tweet = data;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.getComments = function () {
        TweetService.getCommentsFromTweet({"id": $routeParams.tweetId})
            .success(function (data) {
                $scope.tweetComments = data;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.sendComment = function () {
        console.log($scope.comment);
        TweetService.sendComment($routeParams.tweetId, $scope.comment)
            .success(function () {
                $scope.getComments();
                $scope.comment = "";
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            })
    }
});