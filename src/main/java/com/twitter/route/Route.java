package com.twitter.route;

/**
 * Created by Mariusz on 2016-01-22.
 */
public class Route {

    public static final String GET_USERS = "/user";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String CREATE_USER = "/register";
    public static final String EDIT_USER = "/user";

    public static final String GET_FOLLOWERS = "/user/follow";
    public static final String FOLLOW_USER = "/user/follow";
    public static final String UNFOLLOW_USER = "/user/follow";

    public static final String GET_TWEETS = "/user/tweet";
    public static final String GET_TWEETS_BY_USERID = "/user/{userId}/tweet";
    public static final String GET_TWEET_BY_ID = "/user/tweet/{tweetId}";
    public static final String CREATE_TWEET = "/user/tweet";


}
