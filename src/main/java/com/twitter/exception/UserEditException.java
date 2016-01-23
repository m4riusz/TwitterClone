package com.twitter.exception;

/**
 * Created by Mariusz on 2016-01-23.
 */
public class UserEditException extends Exception {
    public UserEditException(String message) {
        super(message);
    }

    public UserEditException() {
        super();
    }
}
