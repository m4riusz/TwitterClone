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
        List<User> listUser = getCurrentSession().createCriteria(User.class).list();
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
    public User get(int id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByUserName(String username) {
        Query query = getCurrentSession().
                createQuery("FROM User u WHERE u.username = :name");
        query.setParameter("name", username);
        User user = (User) query.list().get(0);
        return user;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
