package com.twitter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.service.TweetService;
import com.twitter.service.UserService;

@RestController
public class UserController {
	Logger logger =LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		logger.info("main");
		return "index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		User user = new User();
		user.setEmail("some@email.email");
		user.setUsername("username");
		user.setPassword("password");
		Tweet tweet = new Tweet();
		tweet.setContent("aaaaaa");
		Tweet tweet2 = new Tweet();
		tweet2.setContent("todelete");
		userService.createUser(user);
		tweetService.tweet(user, tweet);
		tweetService.tweet(user, tweet2);

		tweetService.delete(user, tweet2);
		return "index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return userService.getAllUsers().get(0).getEmail();
	}

}
