package com.twitter.service;

import com.twitter.exception.TweetGetException;
import com.twitter.exception.TweetNotFoundException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.model.Tweet;
import com.twitter.model.User;

import java.util.List;

public interface TweetService {

    public Tweet getTweetById(int tweetId) throws TweetGetException, TweetNotFoundException;

    public void tweet(User user, Tweet tweet);

    public List<Tweet> getLatestTweets(int numberOfTweets) throws TweetGetException;

    public List<Tweet> getTweetsFromUser(int userId) throws UserNotFoundException;

}
