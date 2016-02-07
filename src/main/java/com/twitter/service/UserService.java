package com.twitter.service;

import com.twitter.exception.UserAlreadyExist;
import com.twitter.exception.UserAlreadyFollowed;
import com.twitter.exception.UserEditException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.model.User;

import java.util.List;

public interface UserService {

    public void createUser(User user) throws UserAlreadyExist;

    public void deleteUser(User user);

    public void editUser(User user, String password) throws UserEditException;

    public void follow(User user, String userToFollow) throws UserNotFoundException, UserAlreadyFollowed;

    public boolean isUserExist(User user);

    public User getUser(int id);

    public User getUserByUsername(String username);

    public List<User> getAllUsers();

    public List<User> getFollowers(int userId);


}
