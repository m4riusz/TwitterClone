package com.twitter.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.twitter.model.User;

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

}
