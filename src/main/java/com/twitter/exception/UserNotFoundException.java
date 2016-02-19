package com.twitter.exception;

/**
 * Created by mariusz on 07.02.16.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super();
    }
}
