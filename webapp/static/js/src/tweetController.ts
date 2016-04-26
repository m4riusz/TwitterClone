/**
 * Created by mariusz on 22.04.16.
 */

///<reference path="../typings/main.d.ts"/>
///<reference path="tweetService.ts"/>


module TwitterClone.Controllers {

    import getTweets = TwitterClone.Urls.getTweets;
    export class TweetController {
        private scope:ng.IScope;
        private tweetService:Services.ITweetService;
        public allTweets:Models.Tweet[];
        public tweetsFromUser:Models.Tweet[];
        public selectedTweet:Models.Tweet;
        public comments:Models.Tweet[];
        public tweetsFromFollowing:Models.Tweet[];
        public tweetContent:string;

        constructor($scope:ng.IScope, tweetService:Services.ITweetService) {
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
            });
        }

        deleteTweet(tweetId:number) {
            this.tweetService.deleteTweet(tweetId, result => {
                this.getTweets();
            });
        }

        createTweetComment(tweetId:number, tweet:Models.Tweet) {
            this.tweetService.createTweetComment(tweetId, tweet, result => {
                console.log(result);
            });
        }
    }
}
