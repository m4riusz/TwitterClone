package com.twitter.service;

import java.util.List;

import com.twitter.model.User;

public interface UserService {

	public void createUser(User user);

	public void deleteUser(User user);

	public User getUser(User user);

	public List<User> getAllUsers();

}
