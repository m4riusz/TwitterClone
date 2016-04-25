/**
 * Created by mariusz on 22.04.16.
 */
///<reference path="../typings/main.d.ts"/>
///<reference path="apiUrl.ts"/>
///<reference path="model.ts"/>

module TwitterClone.Services {

    export interface ITweetService {
        getTweets (callback:(data:TwitterClone.Models.Tweet[])=>void);
        getTweetsByUserId(userId:number, callback:(data:TwitterClone.Models.Tweet[])=>void);
        getTweetById(tweetId:number, callback:(data:TwitterClone.Models.Tweet)=>void);
        getCommentsFromTweetByTweetId(tweetId:number, callback:(data:TwitterClone.Models.Tweet[])=>void);
        getTweetsFromFollowingUsers(userId:number, callback:(data:TwitterClone.Models.Tweet[])=>void);
        createTweet(tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void);
        deleteTweet(tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void);
        createTweetComment(tweetId:number, tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void);
    }

    export class TweetService implements ITweetService {

        private http:ng.IHttpService;

        constructor($http:ng.IHttpService) {
            this.http = $http;
        }

        public getTweets(callback:(data:TwitterClone.Models.Tweet[])=>void) {
            this.http.get(TwitterClone.Urls.getTweets)
                .success((data:TwitterClone.Models.Tweet[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getTweetsByUserId(userId:number, callback:(data:TwitterClone.Models.Tweet[])=>void) {
            this.http.get(TwitterClone.Urls.getTweetsFromUser(userId))
                .success((data:TwitterClone.Models.Tweet[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getTweetById(tweetId:number, callback:(data:TwitterClone.Models.Tweet)=>void) {
            this.http.get(TwitterClone.Urls.getTweetById(tweetId))
                .success((data:TwitterClone.Models.Tweet)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getCommentsFromTweetByTweetId(tweetId:number, callback:(data:TwitterClone.Models.Tweet[])=>void) {
            this.http.get(TwitterClone.Urls.getCommentsFromTweet(tweetId))
                .success((data:TwitterClone.Models.Tweet[]) => {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getTweetsFromFollowingUsers(userId:number, callback:(data:TwitterClone.Models.Tweet[])=>void) {
            this.http.get(TwitterClone.Urls.getTweetsFromFollowingUsers(userId))
                .success((data:TwitterClone.Models.Tweet[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })
        }

        public createTweet(tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void) {
            this.http.post(TwitterClone.Urls.createTweet, tweet)
                .success((result:boolean)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false
                });
        }

        public deleteTweet(tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void) {
            this.http.delete(TwitterClone.Urls.deleteTweet, tweet)
                .success((result:boolean)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false
                });
        }

        public createTweetComment(tweetId:number, tweet:TwitterClone.Models.Tweet, callback:(result:boolean)=>void) {
            this.http.post(TwitterClone.Urls.createTweetComment(tweetId), tweet)
                .success((result:boolean)=> {
                    callback(result as boolean);
                    return true;
                })
                .error((error)=> {
                    callback(error as boolean);
                    return false
                });
        }
    }

}
