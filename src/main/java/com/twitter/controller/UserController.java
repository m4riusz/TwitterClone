package com.twitter.controller;

import com.twitter.exception.UserAlreadyExist;
import com.twitter.exception.UserEditException;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public User getUser(@PathVariable int userId) {
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
    public void editUser(@RequestBody User user, Principal principal) throws UserEditException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.editUser(currentUser, user.getPassword());
    }

}
