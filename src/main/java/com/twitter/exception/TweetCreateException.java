package com.twitter.exception;

/**
 * Created by mariusz on 09.02.16.
 */
public class TweetCreateException extends Exception {
    public TweetCreateException() {
        super();
    }

    public TweetCreateException(String message) {
        super(message);
    }

}
