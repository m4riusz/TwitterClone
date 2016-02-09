package com.twitter.controller;

import com.twitter.exception.*;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.TweetService;
import com.twitter.service.UserService;
import com.twitter.util.TwitterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by mariusz on 01.02.16.
 */

@RestController
public class TweetController {
    Logger logger = LogManager.getLogger(TweetController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = Route.GET_TWEETS, method = RequestMethod.GET)
    public List<Tweet> getLatestTweets() throws TweetGetException {
        List<Tweet> tweets = tweetService.getLatestTweets(TwitterUtil.NUMBER_OF_LATEST_TWEETS);
        logger.info(tweets);
        return tweets;
    }


    @RequestMapping(value = Route.GET_TWEETS_BY_USERID, method = RequestMethod.GET)
    public List<Tweet> getTweetsFromUser(@PathVariable int userId) throws UserNotFoundException {
        List<Tweet> tweets = tweetService.getTweetsFromUser(userId);
        logger.info(userId + " " + tweets);
        return tweets;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.CREATE_TWEET, method = RequestMethod.POST)
    public void tweet(@RequestBody Tweet tweet, Principal principal) throws UserNotFoundException, TweetCreateException {
        User user = userService.getUserByUsername(principal.getName());
        tweetService.tweet(user, tweet);
        logger.info(user + " " + tweet);
    }

    @RequestMapping(value = Route.GET_TWEET_BY_ID, method = RequestMethod.GET)
    public Tweet getTweetById(@PathVariable int tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetService.getTweetById(tweetId);
        logger.info(tweet);
        return tweet;
    }

    @RequestMapping(value = Route.GET_TWEET_COMMENTS, method = RequestMethod.GET)
    public List<Tweet> getTweetComments(@PathVariable int tweetId) throws TweetNotFoundException {
        return tweetService.getTweetComments(tweetId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = Route.CREATE_TWEET_COMMENT, method = RequestMethod.POST)
    public void createTweetComment(@PathVariable int tweetId, @RequestBody Tweet tweet, Principal principal)
            throws UserNotFoundException, TweetCreateException, TweetNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        tweetService.createTweetComment(currentUser, tweetId, tweet.getContent());
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = Route.DELETE_TWEET, method = RequestMethod.DELETE)
    public void deleteTweet(@PathVariable int tweetId, Principal principal) throws UserNotFoundException, TweetDeleteException, TweetNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        tweetService.deleteTweet(currentUser, tweetId);
    }

}
