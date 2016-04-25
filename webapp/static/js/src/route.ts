/**
 * Created by mariusz on 25.04.16.
 */

///<reference path="../typings/main.d.ts"/>

module TwitterClone.Route {

    export class Routing {
        static get(routeProvider:angular.route.IRouteProvider) {
            routeProvider.when("/tweets", {
                controller: "TweetController",
                templateUrl: "/view/tweets.html",
                controllerAs: "tweetCtrl"
            })
        }
    }
}
