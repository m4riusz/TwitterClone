package com.twitter.service;

import com.twitter.exception.TweetGetException;
import com.twitter.model.Tweet;
import com.twitter.model.User;

import java.util.List;

public interface TweetService {

    public void tweet(User user, Tweet tweet);

    public List<Tweet> getLatestTweets(int numberOfTweets) throws TweetGetException;

    public List<Tweet> getTweetsByUserId(int userId);

}
