package com.twitter.controller;

import com.twitter.exception.*;
import com.twitter.model.User;
import com.twitter.route.Route;
import com.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = Route.GET_USER, method = RequestMethod.GET)
    public List<User> getAllusers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = Route.GET_USER_CURRENT, method = RequestMethod.GET)
    public User getCurrentUser(Principal principal) throws UserNotFoundException {
        return (User) userService.loadUserByUsername(principal.getName());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_USER, method = RequestMethod.PUT)
    public void editUser(@RequestBody User user, Principal principal) throws UserEditException, UserNotFoundException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.editUser(currentUser, user.getPassword());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_USER, method = RequestMethod.POST)
    public void changeUserRole(@RequestBody User user, Principal principal) throws UserNotFoundException, PermisionException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.changeUserRights(currentUser, user.getId(), user.getRole());
    }

    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.POST)
    public void banUser(@PathVariable int userId, Principal principal) throws UserNotFoundException, PermisionException, UserAccessibilityChangeException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.banUser(currentUser, userId);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.PUT)
    public void unbanUser(@PathVariable int userId, Principal principal) throws UserNotFoundException, PermisionException, UserAccessibilityChangeException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.unbanUser(currentUser, userId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = Route.CREATE_USER, method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws UserAlreadyExist, UserCreateException {
        userService.createUser(user);
    }

    @RequestMapping(value = Route.GET_FOLLOWERS, method = RequestMethod.GET)
    public List<User> getFollowers(Principal principal) throws UserNotFoundException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        return userService.getFollowers(currentUser.getId());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_FOLLOWING_BY_USERID, method = RequestMethod.POST)
    public void followUser(@PathVariable int userId, Principal principal) throws UserAlreadyFollowed, UserNotFoundException, UserFollowException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.follow(currentUser, userId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = Route.GET_FOLLOWING_BY_USERID, method = RequestMethod.DELETE)
    public void unfollowUser(@PathVariable int userId, Principal principal) throws UserNotFoundException, UserNotFollowedException, UserFollowException {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        userService.unfollow(currentUser, userId);
    }

    @RequestMapping(value = Route.GET_FOLLOWING_BY_USERID, method = RequestMethod.GET)
    public List<User> getFollowingUsersFromUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getFollowingUsers(userId);
    }

    @RequestMapping(value = Route.GET_FOLLOWERS_BY_USERID, method = RequestMethod.GET)
    public List<User> getFollowersFromUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getFollowers(userId);
    }


}
