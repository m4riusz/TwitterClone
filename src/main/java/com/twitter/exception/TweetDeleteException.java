package com.twitter.exception;

/**
 * Created by mariusz on 09.02.16.
 */
public class TweetDeleteException extends Exception
{
    public TweetDeleteException() {
        super();
    }

    public TweetDeleteException(String message) {
        super(message);
    }
}
