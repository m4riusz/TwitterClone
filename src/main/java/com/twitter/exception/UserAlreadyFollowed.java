package com.twitter.exception;

/**
 * Created by mariusz on 07.02.16.
 */
public class UserAlreadyFollowed extends Exception {
    public UserAlreadyFollowed() {
        super();
    }

    public UserAlreadyFollowed(String message) {
        super(message);
    }
}
