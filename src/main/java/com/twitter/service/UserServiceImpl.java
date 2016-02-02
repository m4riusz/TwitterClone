package com.twitter.service;

import com.twitter.dao.UserDao;
import com.twitter.exception.UserAlreadyExist;
import com.twitter.exception.UserEditException;
import com.twitter.model.User;
import com.twitter.util.UserUtil;
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
    public void createUser(User user) throws UserAlreadyExist {
        if (!isUserExist(user)) {
            userDao.saveOrUpdate(user);
        }
        throw new UserAlreadyExist(user.getUsername() + " " + user.getEmail());

    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void editUser(User user, String password) throws UserEditException {
        if (password.length() < UserUtil.MinPasswordLength || password.length() > UserUtil.MaxPasswordLength) {
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
    public User getUser(int id) {
        User user = userDao.get(id);
        if (user == null) {
            throw new UsernameNotFoundException("Id: " + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.list();
    }

}
