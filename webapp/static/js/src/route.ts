/**
 * Created by mariusz on 25.04.16.
 */

///<reference path="../typings/main.d.ts"/>

module TwitterClone.Route {

    export class Routing {
        static get(routeProvider:angular.route.IRouteProvider) {
            routeProvider.when("/tweets", {
                controller: "TweetController as tweetCtrl",
                templateUrl: "/view/tweets.html"
            });
            routeProvider.when("/tweets/:tweetId", {
                controller: "TweetController as tweetCtrl",
                templateUrl: "/view/tweet.html"
            });
            routeProvider.when("/users", {
                controller: "UserController as userCtrl",
                templateUrl: "/view/users.html"
            });
            routeProvider.when("/users/:userId", {
                controller: "UserController as userCtrl",
                templateUrl: "/view/user.html"
            });
            routeProvider.when("/followers/:userId", {
                controller: "UserController as userCtrl",
                templateUrl: "/view/followers.html"
            });
            routeProvider.when("/following/:userId",{
                controller: "UserController as userCtrl",
                templateUrl: "/view/following.html"
            });
        }
    }
}
