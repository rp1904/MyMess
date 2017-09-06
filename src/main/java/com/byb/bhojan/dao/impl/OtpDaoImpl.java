package com.byb.bhojan.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.OtpDao;
import com.byb.bhojan.model.Otp;
import com.byb.bhojan.util.ProjectConstant;

@Repository
public class OtpDaoImpl implements OtpDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOtp(Otp otp) {

		Session session = sessionFactory.getCurrentSession();
		session.save(otp);

	}

	@Override
	public void updateOtp(Otp otp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(otp);
	}

	@Override
	public Otp getOtpActiveOtpByEmailId(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Otp.class);
		criteria.add(Restrictions.eq("status", ProjectConstant.OTP_ACTIVE));
		criteria.setMaxResults(1);
		criteria.addOrder(Order.desc("createdUpdated.createdAt"));
		return (Otp) criteria.uniqueResult();
	}

}
