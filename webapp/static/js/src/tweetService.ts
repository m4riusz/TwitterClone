/**
 * Created by mariusz on 22.04.16.
 */
///<reference path="../typings/main.d.ts"/>
///<reference path="apiUrl.ts"/>
///<reference path="model.ts"/>

module TwitterClone.Services {

    export interface ITweetService {
        getTweets (callback:(data:Models.Tweet[])=>void);
        getTweetsByUserId(userId:number, callback:(data:Models.Tweet[])=>void);
        getTweetById(tweetId:number, callback:(data:Models.Tweet)=>void);
        getCommentsFromTweetByTweetId(tweetId:number, callback:(data:Models.Tweet[])=>void);
        getTweetsFromFollowingUsers(userId:number, callback:(data:Models.Tweet[])=>void);
        createTweet(tweetContent:string, callback:(result:boolean)=>void);
        deleteTweet(tweetId:number, callback:(result:boolean)=>void);
        createTweetComment(tweetId:number, tweet:Models.Tweet, callback:(result:boolean)=>void);
    }

    export class TweetService implements ITweetService {

        private http:ng.IHttpService;

        constructor($http:ng.IHttpService) {
            this.http = $http;
        }

        public getTweets(callback:(data:Models.Tweet[])=>void) {
            this.http.get(Urls.getTweets)
                .success((data:Models.Tweet[]) => {
                    callback(data);
                    return data;
                })
                .error((error) => {
                    callback(error);
                    return error;
                });
        }

        public getTweetsByUserId(userId:number, callback:(data:Models.Tweet[])=>void) {
            this.http.get(Urls.getTweetsFromUser(userId))
                .success((data:Models.Tweet[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getTweetById(tweetId:number, callback:(data:Models.Tweet)=>void) {
            this.http.get(Urls.getTweetById(tweetId))
                .success((data:Models.Tweet)=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getCommentsFromTweetByTweetId(tweetId:number, callback:(data:Models.Tweet[])=>void) {
            this.http.get(Urls.getCommentsFromTweet(tweetId))
                .success((data:Models.Tweet[]) => {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                });
        }

        public getTweetsFromFollowingUsers(userId:number, callback:(data:Models.Tweet[])=>void) {
            this.http.get(Urls.getTweetsFromFollowingUsers(userId))
                .success((data:Models.Tweet[])=> {
                    callback(data);
                    return data;
                })
                .error((error)=> {
                    callback(error);
                    return error;
                })
        }

        public createTweet(tweetContent:string, callback:(result:boolean)=>void) {
            this.http.post(Urls.createTweet, JSON.stringify({"content": tweetContent}))
                .success((result:boolean)=> {
                    callback(result);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public deleteTweet(tweetId:number, callback:(result:boolean)=>void) {
            this.http.delete(Urls.deleteTweet(tweetId))
                .success((result:boolean)=> {
                    callback(result);
                })
                .error((error)=> {
                    console.error(error);
                });
        }

        public createTweetComment(tweetId:number, tweet:Models.Tweet, callback:(result:boolean)=>void) {
            this.http.post(Urls.createTweetComment(tweetId), tweet)
                .success((result:boolean)=> {
                    callback(result);
                })
                .error((error)=> {
                    console.error(error);
                });
        }
    }

}
