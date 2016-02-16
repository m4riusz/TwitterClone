/**
 * Created by mariusz on 16.02.16.
 */

app.controller("CommentCtrl", function ($scope, $routeParams, TweetService) {
    $scope.tweetComments = [];
    $scope.tweet = {};

    $scope.getCommentedTweet = function () {
        TweetService.getTweetById($routeParams.tweetId)
            .success(function (data) {
                $scope.tweet = data;
            })
            .error(function (response) {
                console.error(response);
            });
    };

    $scope.getComments = function () {
        TweetService.getCommentsFromTweet({"id": $routeParams.tweetId})
            .success(function (data) {
                $scope.tweetComments = data;
            })
            .error(function (response) {
                console.error(response);
            });
    };
});