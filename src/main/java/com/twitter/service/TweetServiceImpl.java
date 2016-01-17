package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitter.dao.TweetDao;
import com.twitter.model.Tweet;
import com.twitter.model.User;

public class TweetServiceImpl implements TweetService {

	private TweetDao tweetDao;

	@Autowired
	public TweetServiceImpl(TweetDao tweetDao) {
		this.tweetDao = tweetDao;
	}

	@Override
	public void tweet(User user, Tweet tweet) {
		tweetDao.tweet(user, tweet);
	}

	@Override
	public void delete(User user, Tweet tweet) {
		tweetDao.delete(user, tweet);

	}

}
