package com.twitter.dao;

import com.twitter.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> list() {
        List<User> listUser = getCurrentSession().createQuery("SELECT u FROM User u").list();
        return listUser;
    }


    @Override
    public void saveOrUpdate(User user) {
        getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {
        getCurrentSession().delete(user);
    }

    @Override
    public List<User> getFollowers(int userId) {
        return getCurrentSession().createQuery("SELECT r FROM User u JOIN u.followers r WHERE u.id = :id")
                .setParameter("id", userId).list();

    }

    @Override
    public User get(int id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        Query query = getCurrentSession().
                createQuery("FROM User u WHERE u.email = :userEmail");
        query.setParameter("userEmail", email);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public User getByUsername(String username) {
        Query query = getCurrentSession().
                createQuery("FROM User u WHERE u.username = :name");
        query.setParameter("name", username);
        User user = (User) query.uniqueResult();
        return user;
    }

    private Session getCurrentSession() {
        sessionFactory.getCurrentSession().clear();
        return sessionFactory.getCurrentSession();
    }

}
