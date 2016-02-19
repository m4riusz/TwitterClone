package com.twitter.service;

import com.twitter.dao.UserDao;
import com.twitter.exception.*;
import com.twitter.model.User;
import com.twitter.util.MessageUtil;
import com.twitter.util.TwitterUtil;
import org.apache.commons.validator.routines.EmailValidator;
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
    public void createUser(User user) throws UserAlreadyExist, UserCreateException {
        if (!isPasswordLengthInBounds(user.getPassword().length())) {
            throw new UserCreateException(MessageUtil.PASSWORD_LENGTH_ERROR);
        } else if (!isUsernameLengthInBounds(user.getUsername().length())) {
            throw new UserCreateException(MessageUtil.USERNAME_LENGTH_ERROR);
        } else if (!isValidEmailAddress(user.getEmail())) {
            throw new UserCreateException(MessageUtil.EMAIL_ERROR);
        } else if (isUserExist(user)) {
            throw new UserAlreadyExist(MessageUtil.USER_ALREADY_EXISTS_ERROR);
        }
        userDao.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void editUser(User user, String password) throws UserEditException {
        if (!isPasswordLengthInBounds(password.length())) {
            throw new UserEditException(MessageUtil.PASSWORD_LENGTH_ERROR);
        }
        user.setPassword(password);
        userDao.saveOrUpdate(user);
    }

    @Override
    public boolean isUserExist(User user) {
        return (userDao.getByEmail(user.getEmail()) != null || userDao.getByUsername(user.getUsername()) != null);
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        User user = userDao.get(id);
        if (user == null) {
            throw new UserNotFoundException(MessageUtil.TWEET_NOT_FOUND_ERROR + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + username);
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
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + userId);
        }
        return userDao.getFollowers(userId);
    }

    @Override
    public void follow(User user, String username) throws UserNotFoundException, UserAlreadyFollowed, UserFollowException {
        User userToFollow = userDao.getByUsername(username);
        if (user.equals(userToFollow)) {
            throw new UserFollowException(MessageUtil.FOLLOW_YOURSELF_ERROR);
        } else if (userToFollow == null) {
            throw new UserNotFoundException(username);
        } else if (userDao.getFollowingUsers(user.getId()).contains(userToFollow)) {
            throw new UserAlreadyFollowed(MessageUtil.ALREADY_FOLLOWED_ERROR + username);
        }
        user.getFollowers().add(userToFollow);
        userDao.saveOrUpdate(user);
    }

    @Override
    public void unfollow(User currentUser, String username) throws UserNotFoundException, UserNotFollowedException, UserFollowException {
        User userToUnfollow = userDao.getByUsername(username);
        if (currentUser.equals(userToUnfollow)) {
            throw new UserFollowException(MessageUtil.UNFOLLOW_YOURSELF_ERROR);
        } else if (userToUnfollow == null) {
            throw new UserNotFoundException(username);
        } else if (!userDao.getFollowingUsers(currentUser.getId()).contains(userToUnfollow)) {
            throw new UserNotFollowedException(MessageUtil.UNFOLLOW_UNFOLLOWED_USER_ERROR);
        }
        currentUser.getFollowers().remove(userToUnfollow);
        userDao.saveOrUpdate(currentUser);
    }

    @Override
    public List<User> getFollowingUsers(int userId) throws UserNotFoundException {
        User user = userDao.get(userId);
        if (user == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + userId);
        }
        return userDao.getFollowingUsers(userId);
    }

    private boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isUsernameLengthInBounds(int usernameLength) {
        return usernameLength >= TwitterUtil.MIN_PASSWORD_LENGTH && usernameLength <= TwitterUtil.MAX_PASSWORD_LENGTH;
    }

    private boolean isPasswordLengthInBounds(int passwordLength) {
        return passwordLength >= TwitterUtil.MIN_PASSWORD_LENGTH && passwordLength <= TwitterUtil.MAX_PASSWORD_LENGTH;
    }
}
