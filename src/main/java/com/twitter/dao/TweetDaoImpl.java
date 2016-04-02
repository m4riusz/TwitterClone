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
        return getCurrentSession().createQuery("FROM Tweet t ORDER BY t.tweetDate DESC").setMaxResults(maxNumberOfTweets).list();
    }

    @Override
    public List<Tweet> getTweetsByOwnerId(int userId) {
        return getCurrentSession().createQuery("FROM Tweet t WHERE t.owner.id = :id ORDER BY t.tweetDate")
                .setParameter("id", userId).list();
    }

    @Override
    public void saveOrUpdate(Tweet tweet) {
        getCurrentSession().merge(tweet);
    }

    @Override
    public void delete(Tweet tweet) {
        getCurrentSession().delete(tweet);
    }

    @Override
    public List<Tweet> getTweetComments(int tweetId) {
        return getCurrentSession().createQuery("SELECT r FROM Tweet t JOIN t.comments r WHERE t.id = :id")
                .setParameter("id", tweetId).list();
    }

    @Override
    public List<Tweet> getTweetsFromFollowingUsers(int userId) {

        return getCurrentSession().createQuery("" +
                "SELECT t FROM Tweet t WHERE t.owner.id IN (SELECT r.id FROM User u JOIN u.followers r WHERE u.id = :id)")
                .setParameter("id", userId).list();
    }

    private Session getCurrentSession() {
        sessionFactory.getCurrentSession().clear();
        return sessionFactory.getCurrentSession();
    }
}
