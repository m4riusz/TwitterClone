package com.twitter.exception;

/**
 * Created by mariusz on 18.02.16.
 */
public class UserCreateException extends Exception {
    public UserCreateException() {
        super();
    }

    public UserCreateException(String message) {
        super(message);
    }
}
