/**
 * Created by mariusz on 25.04.16.
 */

///<reference path="../typings/main.d.ts"/>

module TwitterClone.Route {

    export class Routing {
        static get(routeProvider:angular.route.IRouteProvider) {
            routeProvider.when("/tweets", {
                controller: "TweetController as tweetCtrl",
                templateUrl: "/view/tweets.html",
            });
            routeProvider.when("/tweets/:tweetId", {
                controller: "TweetController as tweetCtrl",
                templateUrl: "/view/tweet.html"
            });
        }
    }
}
