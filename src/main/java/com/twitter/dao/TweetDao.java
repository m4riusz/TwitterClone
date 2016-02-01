package com.twitter.dao;

import com.twitter.model.Tweet;

import java.util.List;

public interface TweetDao {

    public List<Tweet> getLatest(int maxNumberOfTweets);

    public List<Tweet> getTweetsByOwnerId(int id);

    public void saveOrUpdate(Tweet tweet);

    public void delete(Tweet tweet);

}
