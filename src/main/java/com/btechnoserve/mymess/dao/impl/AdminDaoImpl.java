package com.btechnoserve.mymess.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.btechnoserve.mymess.dao.AdminDao;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.util.ProjectConstant;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllAdmins() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userRole.userRoleId", ProjectConstant.USER_ROLE_ID_ADMIN));
		return (List<User>) criteria.list();
	}

}
