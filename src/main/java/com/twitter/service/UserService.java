package com.twitter.service;

import com.twitter.model.User;

import java.util.List;

public interface UserService {

	public void createUser(User user);

	public void deleteUser(User user);

	public User getUser(int id);

	public User getUserByUsername(String username);

	public List<User> getAllUsers();

}
