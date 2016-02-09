package com.twitter.exception;

/**
 * Created by mariusz on 07.02.16.
 */
public class TweetNotFoundException extends Exception {
    public TweetNotFoundException(String message) {
        super(message);
    }

    public TweetNotFoundException() {
        super();
    }

    public TweetNotFoundException(int tweetId) {
        super("Tweet not found! ( id: " + tweetId + " )");
    }
}
