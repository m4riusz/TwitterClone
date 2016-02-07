package com.twitter.route;

/**
 * Created by Mariusz on 2016-01-22.
 */
public class Route {

    public static final String GET_USERS = "/user";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String POST_USER = "/register";
    public static final String PUT_USER = "/user";

    public static final String USER_GET_FOLLOWERS = "/user/follow";
    public static final String USER_FOLLOW = "/user/follow";
    public static final String USER_UNFOLLOW = "/user/follow";

    public static final String GET_TWEETS = "/user/tweet";
    public static final String GET_TWEETS_BY_USERID = "/user/{userId}/tweet";
    public static final String GET_TWEET_BY_ID = "/user/tweet/{tweetId}";
    public static final String POST_TWEET = "/user/tweet";


}
