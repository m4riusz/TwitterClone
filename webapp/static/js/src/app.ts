/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="tweetController.ts"/>
///<reference path="userController.ts"/>
///<reference path="../typings/main.d.ts"/>
///<reference path="userService.ts"/>
///<reference path="tweetService.ts"/>
///<reference path="route.ts"/>
///<reference path="directive.ts"/>


const appModule = angular.module("twitter", ["ngRoute"]);

appModule.factory("UserService", ["$http", ($http) => new TwitterClone.Services.UserService($http)]);
appModule.factory("TweetService", ["$http", ($http) => new TwitterClone.Services.TweetService($http)]);

appModule.controller("UserController", ["$routeParams", "$scope", "UserService", 
    ($routeParams, $scope, userService) => new TwitterClone.Controllers.UserController($routeParams, $scope, userService)]);
appModule.controller("TweetController", ["$routeParams", "$scope", "TweetService",
    ($routeParams, $scope, tweetService) => new TwitterClone.Controllers.TweetController($routeParams, $scope, tweetService)]);

appModule.config(["$routeProvider", ($routeProvider) => TwitterClone.Route.Routing.get($routeProvider)]);

appModule.directive("myTweet", ()=> TwitterClone.Directives.Directive.getTweetDirective());

appModule.directive("myUser", ()=> TwitterClone.Directives.Directive.getUserDirective());