package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.UserDao;
import com.twitter.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void createUser(User user) {
		userDao.saveOrUpdate(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user.getId());
	}

	@Override
	public User getUser(User user) {
		return userDao.get(user.getId());
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.list();
	}
	

}
