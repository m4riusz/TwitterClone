package com.twitter.service;

import com.twitter.dao.UserDao;
import com.twitter.exception.*;
import com.twitter.model.Role;
import com.twitter.model.User;
import com.twitter.util.MessageUtil;
import com.twitter.util.TwitterUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        user.setPassword(passwordEncoder.encode(password));
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
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + id);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + username);
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
    public void follow(User user, int userId) throws UserNotFoundException, UserAlreadyFollowed, UserFollowException {
        User userToFollow = userDao.get(userId);
        if (user.equals(userToFollow)) {
            throw new UserFollowException(MessageUtil.FOLLOW_YOURSELF_ERROR);
        } else if (userToFollow == null) {
            throw new UserNotFoundException(String.valueOf(userId));
        } else if (userDao.getFollowingUsers(user.getId()).contains(userToFollow)) {
            throw new UserAlreadyFollowed(MessageUtil.ALREADY_FOLLOWED_ERROR + userId);
        }
        user.getFollowers().add(userToFollow);
        userDao.saveOrUpdate(user);
    }

    @Override
    public void unfollow(User currentUser, int userId) throws UserNotFoundException, UserNotFollowedException, UserFollowException {
        User userToUnfollow = userDao.get(userId);
        if (currentUser.equals(userToUnfollow)) {
            throw new UserFollowException(MessageUtil.UNFOLLOW_YOURSELF_ERROR);
        } else if (userToUnfollow == null) {
            throw new UserNotFoundException(String.valueOf(userId));
        } else if (!userDao.getFollowingUsers(currentUser.getId()).contains(userToUnfollow)) {
            throw new UserNotFollowedException(MessageUtil.UNFOLLOW_UNFOLLOWED_USER_ERROR + userId);
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

    @Override
    public void banUser(User invokeUser, int userId) throws UserNotFoundException, UserAccessibilityChangeException, PermisionException {
        User userToBlock = userDao.get(userId);
        checkUsersBlockOrUnblockPermission(invokeUser, userToBlock);
        userToBlock.setEnable(false);
        userDao.saveOrUpdate(userToBlock);
    }

    @Override
    public void unbanUser(User invokeUser, int userId) throws UserNotFoundException, UserAccessibilityChangeException, PermisionException {
        User userToUnlock = userDao.get(userId);
        checkUsersBlockOrUnblockPermission(invokeUser, userToUnlock);
        userToUnlock.setEnable(true);
        userDao.saveOrUpdate(userToUnlock);
    }

    @Override
    public void changeUserRights(User invokeUser, int userId, Role role) throws UserNotFoundException, PermisionException {
        User userToChange = userDao.get(userId);
        if (userToChange == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + userId);
        } else if (invokeUser.getRole() == Role.USER) {
            throw new PermisionException(MessageUtil.PERMISSION_ERROR);
        } else if (invokeUser.equals(userToChange)) {
            throw new PermisionException(MessageUtil.PERMISSION_ERROR);
        }
        userToChange.setRole(role);
        userDao.saveOrUpdate(userToChange);
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

    private void checkUsersBlockOrUnblockPermission(User invokeUser, User user) throws UserNotFoundException, UserAccessibilityChangeException, PermisionException {
        if (user == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + user.getId());
        } else if (invokeUser == null) {
            throw new UserNotFoundException(MessageUtil.USER_NOT_FOUND_ERROR + invokeUser.getId());
        } else if (user.equals(invokeUser)) {
            throw new UserAccessibilityChangeException(MessageUtil.ACCESSIBILITY_ERROR);
        } else if (invokeUser.getRole() == Role.USER) {
            throw new PermisionException(MessageUtil.PERMISSION_ERROR);
        } else if (user.getRole() == Role.ADMIN && invokeUser.getRole() == Role.ADMIN) {
            throw new UserAccessibilityChangeException(MessageUtil.ACCESSIBILITY_ADMIN_ERROR);
        }
    }
}
