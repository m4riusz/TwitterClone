package com.twitter.controller;

import com.twitter.exception.*;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.TweetService;
import com.twitter.service.UserService;
import com.twitter.util.TwitterUtil;
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

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = Route.GET_TWEETS, method = RequestMethod.GET)
    public List<Tweet> getLatestTweets() throws TweetGetException {
        return tweetService.getLatestTweets(TwitterUtil.NUMBER_OF_LATEST_TWEETS);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = Route.GET_TWEETS, method = RequestMethod.POST)
    public void tweet(@RequestBody Tweet tweet, Principal principal) throws UserNotFoundException, TweetCreateException {
        User user = (User) userService.loadUserByUsername(principal.getName());
        tweetService.tweet(user, tweet);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_TWEET_BY_ID, method = RequestMethod.DELETE)
    public void deleteTweet(@PathVariable int tweetId, Principal principal) throws UserNotFoundException, TweetDeleteException, TweetNotFoundException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        tweetService.deleteTweet(currentUser, tweetId);
    }

    @RequestMapping(value = Route.GET_TWEETS_BY_USERID, method = RequestMethod.GET)
    public List<Tweet> getTweetsFromUser(@PathVariable int userId) throws UserNotFoundException {
        return tweetService.getTweetsFromUser(userId);
    }

    @RequestMapping(value = Route.GET_TWEET_BY_ID, method = RequestMethod.GET)
    public Tweet getTweetById(@PathVariable int tweetId) throws TweetNotFoundException {
        return tweetService.getTweetById(tweetId);

    }

    @RequestMapping(value = Route.GET_TWEET_COMMENTS, method = RequestMethod.GET)
    public List<Tweet> getTweetComments(@PathVariable int tweetId) throws TweetNotFoundException {
        return tweetService.getTweetComments(tweetId);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = Route.GET_TWEET_COMMENTS, method = RequestMethod.POST)
    public void createTweetComment(@PathVariable int tweetId, @RequestBody Tweet tweet, Principal principal)
            throws UserNotFoundException, TweetCreateException, TweetNotFoundException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        tweetService.createTweetComment(currentUser, tweetId, tweet);
    }

    @RequestMapping(value = Route.GET_TWEETS_FROM_FOLLOWING_USERS, method = RequestMethod.GET)
    public List<Tweet> getTweetsFromFollowers(@PathVariable int userId) throws UserNotFoundException {
        return tweetService.getTweetsFromFollowingUsers(userId);
    }

}
