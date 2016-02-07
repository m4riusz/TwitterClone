package com.twitter.dao;

import com.twitter.model.User;

import java.util.List;

public interface UserDao {

    public List<User> list();

    public User get(int id);

    public User getByEmail(String email);

    public User getByUsername(String username);

    public void saveOrUpdate(User user);

    public void delete(User user);

    public List<User> getFollowers(int userId);

}
