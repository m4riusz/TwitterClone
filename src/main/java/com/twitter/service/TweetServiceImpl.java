package com.twitter.service;

import com.twitter.dao.TweetDao;
import com.twitter.dao.UserDao;
import com.twitter.exception.TweetGetException;
import com.twitter.exception.TweetNotFoundException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Tweet getTweetById(int tweetId) throws TweetGetException, TweetNotFoundException {
        if (tweetId < 0){
            throw new TweetGetException("Wrong tweet id! "+tweetId);
        }
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null){
            throw new TweetNotFoundException("Tweet with id not found! "+tweetId);
        }
        return tweet;
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
    public List<Tweet> getTweetsFromUser(int userId) throws UserNotFoundException {
        User user = userDao.get(userId);
        if (user == null) {
            throw new UserNotFoundException("UserId: " + userId);
        }
        return tweetDao.getTweetsByOwnerId(userId);
    }
}
