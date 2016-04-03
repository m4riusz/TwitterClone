package com.twitter.controller;

import com.twitter.exception.*;
import com.twitter.model.Role;
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

    @RequestMapping(value = Route.GET_USER, method = RequestMethod.HEAD)
    public User getCurrentUser(Principal principal) throws UserNotFoundException {
        return userService.getUserByUsername(principal.getName());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.GET_USER, method = RequestMethod.PUT)
    public void editUser(@RequestBody User user, Principal principal) throws UserEditException, UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.editUser(currentUser, user.getPassword());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.GET_USER, method = RequestMethod.PATCH)
    public void changeUserRole(@RequestBody User user, @RequestBody Role role, Principal principal) throws UserNotFoundException, PermisionException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.changeUserRights(currentUser, user.getId(), role);
    }

    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.PATCH)
    public void banUser(@PathVariable int userId, Principal principal) throws UserNotFoundException, PermisionException, UserAccessibilityChangeException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.banUser(currentUser, userId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = Route.GET_USER_BY_ID, method = RequestMethod.PUT)
    public void unbanUser(@PathVariable int userId, Principal principal) throws UserNotFoundException, PermisionException, UserAccessibilityChangeException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.unbanUser(currentUser, userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.CREATE_USER, method = RequestMethod.POST)
    public void createUser(@RequestBody User user) throws UserAlreadyExist, UserCreateException {
        userService.createUser(user);
    }

    @RequestMapping(value = Route.GET_FOLLOWERS, method = RequestMethod.GET)
    public List<User> getFollowers(Principal principal) throws UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        return userService.getFollowers(currentUser.getId());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.GET_FOLLOWING_USERS, method = RequestMethod.POST)
    public void followUser(@RequestBody User user, Principal principal) throws UserAlreadyFollowed, UserNotFoundException, UserFollowException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.follow(currentUser, user.getUsername());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = Route.GET_FOLLOWING_USERS, method = RequestMethod.DELETE)
    public void unfollowUser(@RequestBody User user, Principal principal) throws UserNotFoundException, UserNotFollowedException, UserFollowException {
        User currentUser = userService.getUserByUsername(principal.getName());
        userService.unfollow(currentUser, user.getUsername());
    }

    @RequestMapping(value = Route.GET_FOLLOWING_USERS, method = RequestMethod.GET)
    public List<User> getFollowingUsers(Principal principal) throws UserNotFoundException {
        User currentUser = userService.getUserByUsername(principal.getName());
        return userService.getFollowingUsers(currentUser.getId());
    }

    @RequestMapping(value = Route.GET_FOLLOWERS_BY_USERID, method = RequestMethod.GET)
    public List<User> getFollowersFromUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getFollowers(userId);
    }

    @RequestMapping(value = Route.GET_FOLLOWING_BY_USERID, method = RequestMethod.GET)
    public List<User> getFollowingUsersFromUser(@PathVariable int userId) throws UserNotFoundException {
        return userService.getFollowingUsers(userId);
    }
}
