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
        public tweetsFromUser:TwitterClone.Models.Tweet[];
        public selectedTweet:TwitterClone.Models.Tweet;
        public comments:TwitterClone.Models.Tweet[];
        public tweetsFromFollowing:TwitterClone.Models.Tweet[];

        constructor($scope:ng.IScope, tweetService:TwitterClone.Services.ITweetService) {
            this.scope = $scope;
            this.tweetService = tweetService;
        }

        getTweets() {
            this.tweetService.getTweets(result => {
                this.allTweets = result;
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


    }


}
