package com.twitter.service;

import com.twitter.exception.*;
import com.twitter.model.User;

import java.util.List;

public interface UserService {

    public void createUser(User user) throws UserAlreadyExist;

    public void deleteUser(User user);

    public void editUser(User user, String password) throws UserEditException;


    public boolean isUserExist(User user);

    public User getUser(int id) throws UserNotFoundException;

    public User getUserByUsername(String username) throws UserNotFoundException;

    public List<User> getAllUsers();

    public List<User> getFollowers(int userId) throws UserNotFoundException;

    public void follow(User user, String userToFollow) throws UserNotFoundException, UserAlreadyFollowed, UserFollowException;

    public void unfollow(User currentUser, String username) throws UserNotFoundException, UserNotFollowedException, UserFollowException;

    public List<User> getFollowingUsers(int userId) throws UserNotFoundException;
}
