package com.twitter.service;

import com.twitter.exception.*;
import com.twitter.model.Tweet;
import com.twitter.model.User;

import java.util.List;

public interface TweetService {

    public Tweet getTweetById(int tweetId) throws TweetNotFoundException;

    public void tweet(User user, Tweet tweet) throws TweetCreateException;

    public List<Tweet> getLatestTweets(int numberOfTweets) throws TweetGetException;

    public List<Tweet> getTweetsFromUser(int userId) throws UserNotFoundException;

    public List<Tweet> getTweetComments(int tweetId) throws TweetNotFoundException;

    public void createTweetComment(User currentUser, int tweetId, Tweet tweet) throws TweetNotFoundException, TweetCreateException;

    public void deleteTweet(User owner, int tweetId) throws TweetNotFoundException, TweetDeleteException;
}
