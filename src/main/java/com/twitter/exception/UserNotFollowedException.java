package com.twitter.exception;

/**
 * Created by mariusz on 08.02.16.
 */
public class UserNotFollowedException extends Exception{
    public UserNotFollowedException(String message) {
        super(message);
    }

    public UserNotFollowedException() {
        super();
    }
}
