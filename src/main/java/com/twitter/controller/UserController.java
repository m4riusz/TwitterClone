package com.twitter.controller;

import com.twitter.model.User;
import com.twitter.route.UserRoute;
import com.twitter.service.TweetService;
import com.twitter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = UserRoute.GET_USERS, method = RequestMethod.GET)
    public List<User> getAllusers() {
        List<User> users = userService.getAllUsers();
        logger.info(users);
        return users;
    }

    @RequestMapping(value = UserRoute.GET_USER_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) {
        User user = userService.getUser(userId);
        logger.info(user.toString());
        return user;
    }

    @RequestMapping(value = UserRoute.POST_USER, method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
        logger.info(user);
    }

}
