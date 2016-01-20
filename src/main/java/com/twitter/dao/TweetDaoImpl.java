package com.twitter.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.twitter.model.Tweet;
import com.twitter.model.User;

@Repository
@Transactional
public class TweetDaoImpl implements TweetDao {

	private SessionFactory sessionFactory;

	@Autowired
	public TweetDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void tweet(User user, Tweet tweet) {
		user.getTweets().add(tweet);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

}
