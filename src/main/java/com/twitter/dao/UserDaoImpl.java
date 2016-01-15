package com.twitter.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.twitter.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<User> list() {

		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
		return listUser;
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void delete(int id) {

		User result = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.idEq(id)).uniqueResult();

		if (result != null) {
			sessionFactory.getCurrentSession().delete(result);
		}

	}

	@Override
	@Transactional
	public User get(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		User result = (User) currentSession.createCriteria(User.class).add(Restrictions.idEq(id)).uniqueResult();
		return result;
	}

}
