package com.twitter.service;

import com.twitter.model.Tweet;
import com.twitter.model.User;

public interface TweetService {
	public void tweet(User user, Tweet tweet);

	public void delete(User user, Tweet tweet);
}
