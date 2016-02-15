/**
 * Created by mariusz on 09.02.16.
 */

var app = angular.module("twitterApp", ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/users", {
            controller: "UserCtrl",
            templateUrl: "/users"
        })
        .when("/followers", {
            controller: "UserCtrl",
            templateUrl: "/followers"
        })
        .when("/following", {
            controller: "UserCtrl",
            templateUrl: "/following"
        })
        .when("/tweets", {
            controller: "TweetCtrl",
            templateUrl: "/tweets"
        })
        .when("/tweets/:tweetId", {
            controller: "TweetCtrl",
            templateUrl: function (params) {
                return '/tweets/' + params.tweetId;
            }
        })
        .otherwise({
            redirectTo: "/users"
        });


});

