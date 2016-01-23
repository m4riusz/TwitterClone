package com.twitter.service;

import com.twitter.dao.UserDao;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {

        userDao.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User getUser(int id) {
        User user = userDao.get(id);
        if (user == null) {
            throw new UsernameNotFoundException("Id: " + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.list();
    }

    @Override
    public void tweet(User user, Tweet tweet) {
        user.getTweets().add(tweet);
        userDao.saveOrUpdate(user);
    }

    @Override
    public List<Tweet> getTweets(int userId) {
        User user = userDao.get(userId);
        return user.getTweets();
    }

}
