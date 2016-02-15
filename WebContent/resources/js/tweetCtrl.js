app.controller("TweetCtrl", function ($scope, $routeParams, TweetService) {

    $scope.latestTweets = [];
    $scope.tweetComments = [];

    $scope.getTweets = function () {
        TweetService.getLatestTweets()
            .success(function (data) {
                $scope.latestTweets = data;
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
    }
});