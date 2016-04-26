/**
 * Created by mariusz on 22.04.16.
 */
///<reference path="../typings/main.d.ts"/>
///<reference path="tweetService.ts"/>
var TwitterClone;
(function (TwitterClone) {
    var Controllers;
    (function (Controllers) {
        var TweetController = (function () {
            function TweetController($scope, tweetService) {
                this.scope = $scope;
                this.tweetService = tweetService;
            }
            TweetController.prototype.getTweets = function () {
                var _this = this;
                this.tweetService.getTweets(function (result) {
                    _this.allTweets = result;
                });
            };
            TweetController.prototype.getTweetsByUserId = function (userId) {
                var _this = this;
                this.tweetService.getTweetsByUserId(userId, function (result) {
                    _this.tweetsFromUser = result;
                });
            };
            TweetController.prototype.getTweetById = function (tweetId) {
                var _this = this;
                this.tweetService.getTweetById(tweetId, function (result) {
                    _this.selectedTweet = result;
                });
            };
            TweetController.prototype.getCommentsFromTweetByTweetId = function (tweetId) {
                var _this = this;
                this.tweetService.getCommentsFromTweetByTweetId(tweetId, function (result) {
                    _this.comments = result;
                });
            };
            TweetController.prototype.getTweetsFromFollowingUsers = function (userId) {
                var _this = this;
                this.tweetService.getTweetsFromFollowingUsers(userId, function (result) {
                    _this.tweetsFromFollowing = result;
                });
            };
            TweetController.prototype.createTweet = function (tweetContent) {
                var _this = this;
                this.tweetService.createTweet(tweetContent, function (result) {
                    _this.getTweets();
                });
            };
            TweetController.prototype.deleteTweet = function (tweetId) {
                var _this = this;
                this.tweetService.deleteTweet(tweetId, function (result) {
                    _this.getTweets();
                });
            };
            TweetController.prototype.createTweetComment = function (tweetId, tweet) {
                this.tweetService.createTweetComment(tweetId, tweet, function (result) {
                    console.log(result);
                });
            };
            return TweetController;
        }());
        Controllers.TweetController = TweetController;
    })(Controllers = TwitterClone.Controllers || (TwitterClone.Controllers = {}));
})(TwitterClone || (TwitterClone = {}));
//# sourceMappingURL=tweetController.js.map