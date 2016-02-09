package com.twitter.dao;

import com.twitter.model.Tweet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TweetDaoImpl implements TweetDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TweetDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Tweet get(int tweetId) {
        return getCurrentSession().get(Tweet.class, tweetId);
    }

    @Override
    public List<Tweet> getLatest(int maxNumberOfTweets) {
        return getCurrentSession().createQuery("FROM Tweet t ORDER BY t.tweetDate").setMaxResults(maxNumberOfTweets).list();
    }

    @Override
    public List<Tweet> getTweetsByOwnerId(int userId) {
        return getCurrentSession().createQuery("FROM Tweet t WHERE t.owner.id = :id ORDER BY t.tweetDate")
                .setParameter("id", userId).list();
    }

    @Override
    public void saveOrUpdate(Tweet tweet) {
        getCurrentSession().saveOrUpdate(tweet);
    }

    @Override
    public void delete(Tweet tweet) {
        getCurrentSession().delete(tweet);
    }

    @Override
    public List<Tweet> getTweetComments(int tweetId) {
        return getCurrentSession().createQuery("SELECT r FROM Tweet t JOIN t.comments r WHERE t.id = :id")
                .setParameter("id",tweetId).list();
    }

    private Session getCurrentSession() {
        sessionFactory.getCurrentSession().clear();
        return sessionFactory.getCurrentSession();
    }
}
