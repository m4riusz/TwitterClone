app.controller("TweetCtrl", function ($scope, TweetService) {

    $scope.latestTweets = [];
    $scope.message = "";
    $scope.tweetsFromFollowingUsers = [];

    $scope.sendTweet = function () {
        TweetService.sendTweet($scope.message)
            .success(function () {
                $scope.getTweets();
                $scope.message = "";
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.getTweets = function () {
        TweetService.getLatestTweets()
            .success(function (data) {
                $scope.latestTweets = data;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.deleteTweet = function (tweet) {
        TweetService.deleteTweet(tweet.id)
            .success(function () {
                tweet.content = "Tweet has been deleted!";
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            });
    };

    $scope.getTweetsFromFollowers = function () {
        TweetService.getTweetsFromFollowers()
            .success(function (data) {
                $scope.tweetsFromFollowingUsers = data;
            })
            .error(function (response) {
                console.error(response);
                alert(response.message);
            })
    }

});