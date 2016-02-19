package com.twitter.util;

/**
 * Created by mariusz on 19.02.16.
 */
public class MessageUtil {

    public static final String PASSWORD_LENGTH_ERROR = "Password length should be between " + TwitterUtil.MIN_PASSWORD_LENGTH + " and " + TwitterUtil.MAX_PASSWORD_LENGTH;
    public static final String USERNAME_LENGTH_ERROR = "Username length should be between " + TwitterUtil.MIN_USERNAME_LENGTH + " and " + TwitterUtil.MAX_USERNAME_LENGTH;
    public static final String EMAIL_ERROR = "Wrong email format! ";
    public static final String USER_ALREADY_EXISTS_ERROR = "User with this username and e-mail already exists! ";
    public static final String USER_NOT_FOUND_ERROR = "User not found! ";
    public static final String FOLLOW_YOURSELF_ERROR = "You can not follow yourself! ";
    public static final String UNFOLLOW_YOURSELF_ERROR = "You can not unfollow yourself! ";
    public static final String ALREADY_FOLLOWED_ERROR = "You are already following user! ";
    public static final String UNFOLLOW_UNFOLLOWED_USER_ERROR = "You can not unfollow user who is not followed! ";

    public static final String TWEET_LENGTH_ERROR = "Tweet length should be between " + TwitterUtil.MIN_TWEET_LENGTH + " and " + TwitterUtil.MAX_TWEET_LENGTH;
    public static final String TWEET_FETCH_NUMBER_ERROR = "Number of tweets to fetch should be positive! ";
    public static final String TWEET_DELETE_MESSAGE = "Tweet has been deleted!";
    public static final String TWEET_NOT_FOUND_ERROR = "Tweet not found! ";
    public static final String TWEET_DELETE_ERROR = "You are not owner of tweet you want to delete! ";

    private MessageUtil() {
    }
}
