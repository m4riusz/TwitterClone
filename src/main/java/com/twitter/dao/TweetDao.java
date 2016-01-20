package com.twitter.dao;

import com.twitter.model.Tweet;
import com.twitter.model.User;

public interface TweetDao {
	
	public void tweet(User user, Tweet tweet);

}
