/**
 * Created by mariusz on 22.04.16.
 */
///<reference path="../typings/main.d.ts"/>
///<reference path="apiUrl.ts"/>
///<reference path="model.ts"/>

module TwitterClone.Services {

    export interface ITweetService {
        getTweets (callback:(data:TwitterClone.Models.Tweet[])=>void);
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
    }

}
