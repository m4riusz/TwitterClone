package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.twitter.dao.TweetDao;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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


}
