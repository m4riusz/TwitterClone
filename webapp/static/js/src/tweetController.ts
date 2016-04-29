/**
 * Created by mariusz on 22.04.16.
 */

///<reference path="../typings/main.d.ts"/>
///<reference path="tweetService.ts"/>


module TwitterClone.Controllers {
    
    export class TweetController {
        private scope:ng.IScope;
        private tweetService:Services.ITweetService;
        public routeParams:ng.route.IRouteParamsService;
        public allTweets:Models.Tweet[];
        public tweetsFromUser:Models.Tweet[];
        public selectedTweet:Models.Tweet;
        public comments:Models.Tweet[];
        public tweetsFromFollowing:Models.Tweet[];
        public tweetContent:string;
        public commentContent:string;

        constructor($routeParams:ng.route.IRouteParamsService, $scope:ng.IScope, tweetService:Services.ITweetService) {
            this.routeParams = $routeParams;
            this.scope = $scope;
            this.tweetService = tweetService;
        }

        getTweets() {
            this.tweetService.getTweets(result => {
                this.allTweets = result
            });
        }

        getTweetsByUserId(userId:number) {
            this.tweetService.getTweetsByUserId(userId, result=> {
                this.tweetsFromUser = result;
            });
        }

        getTweetById(tweetId:number) {
            this.tweetService.getTweetById(tweetId, result=> {
                this.selectedTweet = result;
            });
        }

        getCommentsFromTweetByTweetId(tweetId:number) {
            this.tweetService.getCommentsFromTweetByTweetId(tweetId, result=> {
                this.comments = result;
            });

        }

        getTweetsFromFollowingUsers(userId:number) {
            this.tweetService.getTweetsFromFollowingUsers(userId, result=> {
                this.tweetsFromFollowing = result;
            });
        }

        createTweet(tweetContent:string) {
            this.tweetService.createTweet(tweetContent, result => {
                this.getTweets();
                this.tweetContent = "";
            });
        }

        deleteTweet(tweetId:number) {
            this.tweetService.deleteTweet(tweetId, result => {
                this.getTweets();
                this.getCommentsFromTweetByTweetId(tweetId);
            });
        }

        createTweetComment(tweetId:number, tweet:string) {
            this.tweetService.createTweetComment(tweetId, tweet, result => {
                this.getCommentsFromTweetByTweetId(tweetId);
                this.commentContent = "";
            });
        }
    }
}
