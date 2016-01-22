package com.twitter.dao;

import com.twitter.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> list() {
        List<User> listUser = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        return listUser;
    }

    @Override
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User get(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByUserName(String username) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from User where username=:username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        return user;
    }

}
