/**
 * Created by mariusz on 09.02.16.
 */

var app = angular.module("TwitterApp", ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/users", {
            controller: "UserCtrl",
            templateUrl: "/users"
        })
        .when("/users/:userId", {
            controller: "ProfileCtrl",
            templateUrl: function (params) {
                return "/users/" + params.userId;
            }
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
            controller: "CommentCtrl",
            templateUrl: function (params) {
                return '/tweets/' + params.tweetId;
            }
        })
        .otherwise({
            redirectTo: "/users"
        });


});

