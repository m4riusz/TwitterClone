package com.twitter.util;

/**
 * Created by mariusz on 02.02.16.
 */
public class TwitterUtil {
    public static final int MIN_USERNAME_LENGTH = 6;
    public static final int MAX_USERNAME_LENGTH = 16;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 16;

    public static final String DATE_FORMAT = "HH:mm dd-MM-yyyy";
    public static final int MAX_TWEET_LENGTH = 140;
    public static final int MIN_TWEET_LENGTH = 1;
    public static final int NUMBER_OF_LATEST_TWEETS = 10;

    public static final String DELETE_MESSAGE = "Tweet has been deleted!";
    public static final String PASSWORD_LENGTH_ERROR = "Password length should be between " + TwitterUtil.MIN_PASSWORD_LENGTH + " and " + TwitterUtil.MAX_PASSWORD_LENGTH;
    public static final String USERNAME_LENGTH_ERROR = "Username length should be between " + TwitterUtil.MIN_USERNAME_LENGTH + " and " + TwitterUtil.MAX_USERNAME_LENGTH;
    public static final String EMAIL_ERROR = "Wrong email format! ";
    public static final String USER_ALREADY_EXISTS_ERROR = "User with this username and e-mail already exists! ";
    public static final String USER_NOT_FOUND_ERROR = "User not found! ";
    public static final String FOLLOW_YOURSELF_ERROR = "You can not follow yourself! ";
    public static final String UNFOLLOW_YOURSELF_ERROR = "You can not unfollow yourself! ";
    public static final String ALREADY_FOLLOWED_ERROR = "You are already following user! ";
    public static final String UNFOLLOW_UNFOLLOWED_USER_ERROR = "You can not unfollow user who is not followed! ";
}
