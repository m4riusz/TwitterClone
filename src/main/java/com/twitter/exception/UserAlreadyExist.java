package com.twitter.exception;

/**
 * Created by mariusz on 02.02.16.
 */
public class UserAlreadyExist extends Exception {
    public UserAlreadyExist() {
        super();
    }

    public UserAlreadyExist(String message) {
        super(message);
    }
}
