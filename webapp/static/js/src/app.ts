/**
 * Created by mariusz on 13.04.16.
 */
///<reference path="userController.ts"/>
///<reference path="../typings/main.d.ts"/>
///<reference path="userService.ts"/>

const appModule = angular.module("twitter", ["ngRoute"]);

appModule.factory("UserService", ["$http", ($http) => new TwitterClone.Services.UserService($http)]);

appModule.controller("UserController", ["$scope", "UserService", ($scope, userService) => new TwitterClone.Controllers.UserController($scope, userService)]);
