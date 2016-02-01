package com.twitter.service;

import com.twitter.dao.TweetDao;
import com.twitter.dao.UserDao;
import com.twitter.exception.TweetGetException;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class TweetServiceImpl implements TweetService {

    private TweetDao tweetDao;
    private UserDao userDao;

    @Autowired
    public TweetServiceImpl(UserDao userDao, TweetDao tweetDao) {
        this.tweetDao = tweetDao;
        this.userDao = userDao;
    }


    @Override
    public void tweet(User user, Tweet tweet) {
        tweet.setTweetDate(Calendar.getInstance().getTime());
        tweet.setOwner(user);
        tweetDao.saveOrUpdate(tweet);
    }

    @Override
    public List<Tweet> getLatestTweets(int numberOfTweets) throws TweetGetException {
        if (numberOfTweets < 0) {
            throw new TweetGetException("Cant fetch latest: " + numberOfTweets);
        }
        return tweetDao.getLatest(numberOfTweets);
    }

    @Override
    public List<Tweet> getTweetsByUserId(int userId) {
        User user = userDao.get(userId);
        if (user == null) {
            throw new UsernameNotFoundException("UserId: " + userId);
        }
        return tweetDao.getTweetsByOwnerId(userId);
    }
}
