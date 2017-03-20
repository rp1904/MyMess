package com.btechnoserve.mymess.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.btechnoserve.mymess.dao.UserDao;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserRole;
import com.btechnoserve.mymess.util.ProjectConstant;

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
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(Boolean.TRUE);
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

	@Override
	public User getUserByEmailOrMobileNo(String emailIdRoMobNo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		Criterion mobNo = Restrictions.eq("mobileNumber", emailIdRoMobNo.trim());
		Criterion email = Restrictions.eq("email", emailIdRoMobNo.trim());
		criteria.add(Restrictions.or(mobNo, email));
		User m = (User) criteria.uniqueResult();
		return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllMembers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userRole.userRoleId", ProjectConstant.USER_ROLE_ID_MEMBER));
		return (List<User>) criteria.list();
	}

}