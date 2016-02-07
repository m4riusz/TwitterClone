package com.twitter.controller;

import com.twitter.exception.UserAlreadyExist;
import com.twitter.exception.UserAlreadyFollowed;
import com.twitter.exception.UserEditException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = Route.GET_USERS, method = RequestMethod.GET)
    public List<User> getAllusers() {
        List<User> users = userService.getAllUsers();
        logger.info(users);
        return users;
    }

    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) throws UserNotFoundException {
        User user = userService.getUser(userId);
        logger.info(user.toString());
        return user;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.POST_USER, method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws UserAlreadyExist {
        userService.createUser(user);
        logger.info(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.PUT_USER, method = RequestMethod.PUT)
    public void editUser(@RequestBody User user, Principal principal) throws UserEditException, UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.editUser(currentUser, user.getPassword());
    }

    @RequestMapping(value = Route.USER_GET_FOLLOWERS, method = RequestMethod.GET)
    public List<User> getFollowers(Principal principal) throws UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        List<User> followers = userService.getFollowers(currentUser.getId());
        logger.info(followers);
        return followers;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.USER_FOLLOW, method = RequestMethod.POST)
    public void followUser(@RequestBody User user, Principal principal) throws UserAlreadyFollowed, UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.follow(currentUser, user.getUsername());
    }

}
