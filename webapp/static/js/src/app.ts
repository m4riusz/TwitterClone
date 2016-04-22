/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="tweetController.ts"/>
///<reference path="userController.ts"/>
///<reference path="../typings/main.d.ts"/>
///<reference path="userService.ts"/>
///<reference path="tweetService.ts"/>


const appModule = angular.module("twitter", ["ngRoute"]);

appModule.factory("UserService", ["$http", ($http) => new TwitterClone.Services.UserService($http)]);
appModule.factory("TweetService", ["$http", ($http) => new TwitterClone.Services.TweetService($http)]);

appModule.controller("UserController", ["$scope", "UserService", ($scope, userService) => new TwitterClone.Controllers.UserController($scope, userService)]);
appModule.controller("TweetController", ["$scope", "TweetService", ($scope, tweetService) => new TwitterClone.Controllers.TweetController($scope, tweetService)]);