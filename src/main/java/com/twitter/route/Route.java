package com.twitter.route;

/**
 * Created by Mariusz on 2016-01-22.
 */
public class Route {

    public static final String VIEW_MAIN = "/";
    public static final String VIEW_USERS = "/users";
    public static final String VIEW_FOLLOWERS = "/followers";
    public static final String VIEW_FOLLOWING = "/following";
    public static final String VIEW_LATEST_TWEETS = "/tweets";
    public static final String VIEW_TWEET = "/tweets/{tweetId}";
    public static final String LOGOUT = "/logout";

    public static final String GET_USERS = "/rest/user";
    public static final String GET_USER_BY_ID = "/rest/user/{userId}";
    public static final String CREATE_USER = "/register";
    public static final String EDIT_USER = "/rest/user";

    public static final String GET_FOLLOWERS = "/rest/user/followers";
    public static final String GET_FOLLOWERS_BY_USERID = "/rest/user/followers/{userId}";
    public static final String GET_FOLLOWING_USERS = "/rest/user/following";
    public static final String GET_FOLLOWING_BY_USERID = "/rest/user/following/{userId}";
    public static final String FOLLOW_USER = "/rest/user/following";
    public static final String UNFOLLOW_USER = "/rest/user/following";

    public static final String GET_TWEETS = "/rest/user/tweet";
    public static final String GET_TWEETS_BY_USERID = "/rest/user/{userId}/tweet";
    public static final String GET_TWEET_BY_ID = "/rest/user/tweet/{tweetId}";
    public static final String CREATE_TWEET = "/rest/user/tweet";
    public static final String DELETE_TWEET = "/rest/user/tweet/{tweetId}";

    public static final String GET_TWEET_COMMENTS = "/rest/user/tweet/{tweetId}/comment";
    public static final String CREATE_TWEET_COMMENT = "/rest/user/tweet/{tweetId}/comment";
}
