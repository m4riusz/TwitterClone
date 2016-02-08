package com.twitter.service;

import com.twitter.dao.UserDao;
import com.twitter.exception.*;
import com.twitter.model.User;
import com.twitter.util.TwitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser(User user) throws UserAlreadyExist {
        if (!isUserExist(user)) {
            userDao.saveOrUpdate(user);
            return;
        }
        throw new UserAlreadyExist(user.getUsername() + " " + user.getEmail());

    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void editUser(User user, String password) throws UserEditException {
        if (password.length() < TwitterUtil.MinPasswordLength || password.length() > TwitterUtil.MaxPasswordLength) {
            throw new UserEditException("Wrong password length!");
        }
        user.setPassword(password);
        userDao.saveOrUpdate(user);
    }

    @Override
    public boolean isUserExist(User user) {
        if (userDao.getByEmail(user.getEmail()) != null || userDao.getByUsername(user.getUsername()) != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        User user = userDao.get(id);
        if (user == null) {
            throw new UserNotFoundException("Id: " + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.list();
    }

    @Override
    public List<User> getFollowers(int userId) throws UserNotFoundException {
        User user = userDao.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " does not exists!");
        }
        return userDao.getFollowers(userId);
    }

    @Override
    public void follow(User user, String username) throws UserNotFoundException, UserAlreadyFollowed, UserFollowException {
        User userToFollow = userDao.getByUsername(username);
        if (user.equals(userToFollow)) {
            throw new UserFollowException("You can not follow yourself!");
        } else if (userToFollow == null) {
            throw new UserNotFoundException("Username: " + username);
        } else if (userDao.getFollowers(user.getId()).contains(userToFollow)) {
            throw new UserAlreadyFollowed("You are already following user: " + username);
        }
        user.getFollowers().add(userToFollow);
        userDao.saveOrUpdate(user);
    }

    @Override
    public void unfollow(User currentUser, String username) throws UserNotFoundException, UserNotFollowedException, UserFollowException {
        User userToUnfollow = userDao.getByUsername(username);
        if (currentUser.equals(userToUnfollow)) {
            throw new UserFollowException("You can not unfollow yourself!");
        } else if (userToUnfollow == null) {
            throw new UserNotFoundException(username);
        } else if (!userDao.getFollowers(currentUser.getId()).contains(userToUnfollow)) {
            throw new UserNotFollowedException("You can not unfollow user who is not followed! " + username);
        }
        currentUser.getFollowers().remove(userToUnfollow);
        userDao.saveOrUpdate(currentUser);
    }

}
