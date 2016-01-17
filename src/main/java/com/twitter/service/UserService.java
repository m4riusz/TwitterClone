package com.twitter.service;

import java.util.List;

import com.twitter.model.User;

public interface UserService {

	public void createUser(User user);

	public void deleteUser(User user);

	public User getUser(int id);

	public List<User> getAllUsers();

}
