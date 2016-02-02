package com.twitter.service;

import com.twitter.exception.UserAlreadyExist;
import com.twitter.exception.UserEditException;
import com.twitter.model.User;

import java.util.List;

public interface UserService {

    public void createUser(User user) throws UserAlreadyExist;

    public void deleteUser(User user);

    public void editUser(User user, String password) throws UserEditException;

    public boolean isUserExist(User user);

    public User getUser(int id);

    public User getUserByUsername(String username);

    public List<User> getAllUsers();

}
