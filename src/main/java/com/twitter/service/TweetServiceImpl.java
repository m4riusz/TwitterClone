package com.twitter.service;

import com.twitter.dao.TweetDao;
import com.twitter.dao.UserDao;
import com.twitter.exception.*;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.util.MessageUtil;
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
            throw new TweetNotFoundException(MessageUtil.TWEET_NOT_FOUND_ERROR + tweetId);
        }
        return tweet;
    }

    @Override
    public void tweet(User user, Tweet tweet) throws TweetCreateException {
        if (!isTweetLengthInBounds(tweet.getContent().length())) {
            throw new TweetCreateException(MessageUtil.TWEET_LENGTH_ERROR);
        }
        tweet.setOwner(user);
        tweetDao.saveOrUpdate(tweet);
    }

    @Override
    public List<Tweet> getLatestTweets(int numberOfTweets) throws TweetGetException {
        if (numberOfTweets < 0) {
            throw new TweetGetException(MessageUtil.TWEET_FETCH_NUMBER_ERROR);
        }
        return tweetDao.getLatest(numberOfTweets);
    }

    @Override
    public List<Tweet> getTweetsFromUser(int userId) throws UserNotFoundException {
        User user = userDao.get(userId);
        if (user == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR);
        }
        return tweetDao.getTweetsByOwnerId(userId);
    }

    @Override
    public List<Tweet> getTweetComments(int tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException(MessageUtil.TWEET_NOT_FOUND_ERROR + tweetId);
        }
        return tweetDao.getTweetComments(tweetId);
    }

    @Override
    public void createTweetComment(User currentUser, int tweetId, Tweet tweet) throws TweetNotFoundException, TweetCreateException {
        Tweet tweetToComment = tweetDao.get(tweetId);
        if (tweetToComment == null) {
            throw new TweetNotFoundException(MessageUtil.TWEET_NOT_FOUND_ERROR + tweetId);
        } else if (!isTweetLengthInBounds(tweet.getContent().length())) {
            throw new TweetCreateException(MessageUtil.TWEET_LENGTH_ERROR);
        }
        tweet.setOwner(currentUser);
        tweetToComment.getComments().add(tweet);
        tweetDao.saveOrUpdate(tweetToComment);
    }

    @Override
    public void deleteTweet(User owner, int tweetId) throws TweetNotFoundException, TweetDeleteException {
        Tweet tweet = tweetDao.get(tweetId);
        if (tweet == null) {
            throw new TweetNotFoundException(MessageUtil.TWEET_NOT_FOUND_ERROR + tweetId);
        } else if (!tweet.getOwner().equals(owner)) {
            throw new TweetDeleteException(MessageUtil.TWEET_DELETE_ERROR);
        }
        tweet.setContent(MessageUtil.TWEET_DELETE_MESSAGE);
        tweetDao.saveOrUpdate(tweet);
    }

    private boolean isTweetLengthInBounds(int tweetLength) {
        return tweetLength >= TwitterUtil.MIN_TWEET_LENGTH && tweetLength <= TwitterUtil.MAX_TWEET_LENGTH;
    }
}
