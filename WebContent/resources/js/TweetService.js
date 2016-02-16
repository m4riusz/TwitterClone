/**
 * Created by mariusz on 15.02.16.
 */

app.factory("TweetService", function ($http) {

        return {
            getLatestTweets: function () {
                return $http({
                    method: "get",
                    url: "/rest/user/tweet"
                });
            },

            getCommentsFromTweet: function (tweet) {
                return $http({
                    method: "get",
                    url: "/rest/user/tweet/" + tweet.id + "/comment"
                });
            },

            sendComment: function (tweetId, comment) {
                return $http({
                    method: "post",
                    url: "/rest/user/tweet/" + tweetId + "/comment",
                    data: JSON.stringify({"content": comment}),
                    headers: {"Content-Type": "application/json"}
                });
            },

            getTweetsByUserId: function (userId) {
                return $http({
                    method: "get",
                    url: "/rest/user/" + userId + "/tweet"
                });
            },
            getTweetById: function (tweetId) {
                return $http({
                    method: "get",
                    url: "/rest/user/tweet/" + tweetId
                });
            },

            sendTweet: function (message) {
                return $http({
                    method: "post",
                    url: "/rest/user/tweet",
                    data: JSON.stringify({"content": message}),
                    headers: {"Content-Type": "application/json"}
                });
            },

            deleteTweet: function (message) {
                return $http({
                    method: "delete",
                    url: "/rest/user/tweet",
                    data: JSON.stringify({"content": message}),
                    headers: {"Content-Type": "application/json"}
                });
            }


        }
    }
);