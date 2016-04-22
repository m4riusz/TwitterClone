/**
 * Created by mariusz on 22.04.16.
 */

///<reference path="../typings/main.d.ts"/>
///<reference path="tweetService.ts"/>


module TwitterClone.Controllers {

    export class TweetController {
        private scope:ng.IScope;
        private tweetService:TwitterClone.Services.ITweetService;
        public allTweets:TwitterClone.Models.Tweet[];

        constructor($scope:ng.IScope, tweetService:TwitterClone.Services.ITweetService) {
            this.scope = $scope;
            this.tweetService = tweetService;
        }

        getTweets() {
            this.tweetService.getTweets(result => {
                this.allTweets = result;
            });
        }
        

    }


}
