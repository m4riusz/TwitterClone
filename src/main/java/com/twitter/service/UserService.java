package com.twitter.service;

import com.twitter.exception.*;
import com.twitter.model.Role;
import com.twitter.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService{

    public void createUser(User user) throws UserAlreadyExist, UserCreateException;

    public void deleteUser(User user);

    public void editUser(User user, String password) throws UserEditException;

    public boolean isUserExist(User user);

    public User getUser(int id) throws UserNotFoundException;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public List<User> getAllUsers();

    public List<User> getFollowers(int userId) throws UserNotFoundException;

    public void follow(User user, int userId) throws UserNotFoundException, UserAlreadyFollowed, UserFollowException;

    public void unfollow(User currentUser, int userId) throws UserNotFoundException, UserNotFollowedException, UserFollowException;

    public List<User> getFollowingUsers(int userId) throws UserNotFoundException;

    public void banUser(User invokeUser, int userId) throws UserNotFoundException, UserAccessibilityChangeException, PermisionException;

    public void unbanUser(User invokeUser, int userId) throws UserNotFoundException, UserAccessibilityChangeException, PermisionException;

    public void changeUserRights(User invokeUser, int userId, Role role) throws UserNotFoundException, PermisionException;
}
