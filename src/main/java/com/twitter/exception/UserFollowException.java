package com.twitter.exception;

/**
 * Created by mariusz on 08.02.16.
 */
public class UserFollowException extends Exception{
    public UserFollowException() {
        super();
    }

    public UserFollowException(String message) {
        super(message);
    }
}
