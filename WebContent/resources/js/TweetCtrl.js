app.controller("TweetCtrl", function ($scope, TweetService) {

    $scope.latestTweets = [];
    $scope.message = "";

    $scope.sendTweet = function () {
        TweetService.sendTweet($scope.message)
            .success(function () {
                $scope.getTweets();
                $scope.message = "";
            })
            .error(function (response) {
                console.error(response);
            });
    };

    $scope.getTweets = function () {
        TweetService.getLatestTweets()
            .success(function (data) {
                $scope.latestTweets = data;
            })
            .error(function (response) {
                console.error(response);
            });
    };

});