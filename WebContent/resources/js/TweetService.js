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

        createTweet: function (tweet) {
            return $http({
                method: "post",
                url: "/rest/user/tweet",
                data: JSON.stringify(tweet),
                headers: {"Content-Type": "application/json"}
            });
        },

        deleteTweet: function (tweet) {
            return $http({
                method: "delete",
                url: "/rest/user/tweet",
                data: JSON.stringify(tweet),
                headers: {"Content-Type": "application/json"}
            });
        }

    }
});