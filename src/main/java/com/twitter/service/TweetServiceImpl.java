package com.twitter.service;

import com.twitter.dao.TweetDao;
import com.twitter.dao.UserDao;
import com.twitter.exception.*;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.util.TwitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Tweet getTweetById(int tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException(tweetId);
        }
        return tweet;
    }

    @Override
    public void tweet(User user, Tweet tweet) throws TweetCreateException {
        if (tweet.getContent().length() > TwitterUtil.MAX_TWEET_LENGTH ||
                tweet.getContent().length() < TwitterUtil.MIN_TWEET_LENGTH) {
            throw new TweetCreateException(tweet.getContent().length());
        }
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
            throw new UserNotFoundException(userId);
        }
        return tweetDao.getTweetsByOwnerId(userId);
    }

    @Override
    public List<Tweet> getTweetComments(int tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException(tweetId);
        }
        return tweetDao.getTweetComments(tweetId);
    }

    @Override
    public void createTweetComment(User currentUser, int tweetId, Tweet tweet) throws TweetNotFoundException, TweetCreateException {
        Tweet tweetToComment = tweetDao.get(tweetId);
        if (tweetToComment == null) {
            throw new TweetNotFoundException(tweetId);
        } else if (tweet.getContent().length() > TwitterUtil.MAX_TWEET_LENGTH ||
                tweet.getContent().length() < TwitterUtil.MIN_TWEET_LENGTH) {
            throw new TweetCreateException(tweet.getContent().length());
        }
        tweet.setOwner(currentUser);
        tweetToComment.getComments().add(tweet);
        tweetDao.saveOrUpdate(tweetToComment);
    }

    @Override
    public void deleteTweet(User owner, int tweetId) throws TweetNotFoundException, TweetDeleteException {
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException(tweetId);
        } else if (!tweet.getOwner().equals(owner)) {
            throw new TweetDeleteException("You are not owner of tweet you want to delete!");
        }
        tweet.setContent(TwitterUtil.DELETE_MESSAGE);
        tweetDao.saveOrUpdate(tweet);
    }
}
