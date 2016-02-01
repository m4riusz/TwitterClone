package com.twitter.controller;

import com.twitter.exception.TweetGetException;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.TweetService;
import com.twitter.service.UserService;
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
    public List<Tweet> getTweets() throws TweetGetException {
        List<Tweet> tweets = tweetService.getLatestTweets(10);
        logger.info(tweets);
        return tweets;
    }


    @RequestMapping(value = Route.GET_TWEETS_BY_USERID, method = RequestMethod.GET)
    public List<Tweet> getTweetsFromUser(@PathVariable int userId) {
        List<Tweet> tweets = tweetService.getTweetsByUserId(userId);
        logger.info(userId + " " + tweets);
        return tweets;
    }

    @RequestMapping(value = Route.POST_TWEET, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void tweet(@RequestBody Tweet tweet, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        tweetService.tweet(user, tweet);
        logger.info(user + " " + tweet);
    }
}
