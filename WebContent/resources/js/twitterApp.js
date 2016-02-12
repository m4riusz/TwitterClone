/**
 * Created by mariusz on 09.02.16.
 */

var app = angular.module("twitterApp", ['ngRoute']);

app.config(function ($routeProvider) {

    $routeProvider
        .when("/users", {
            controller: "UserCtrl",
            templateUrl: "/users"
        });


});

