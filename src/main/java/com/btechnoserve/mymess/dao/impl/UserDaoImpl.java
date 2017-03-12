package com.btechnoserve.mymess.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.btechnoserve.mymess.dao.UserDao;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User findByUserName(String emailOrMobNo) {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion criterion = Restrictions.or(Restrictions.eq("email", emailOrMobNo),
				Restrictions.eq("mobileNumber", emailOrMobNo));
		criteria.add(criterion);
		return (User) criteria.uniqueResult();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public UserRole getUserRoleById(int roleId) {
		// TODO Auto-generated method stub
		return (UserRole) sessionFactory.getCurrentSession().get(UserRole.class, roleId);
	}

	@Override
	public User getUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", emailId.trim()));
		User m = (User) criteria.uniqueResult();
		return m;
	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("mobileNumber", mobileNumber.trim()));
		User m = (User) criteria.uniqueResult();
		return m;
	}

}