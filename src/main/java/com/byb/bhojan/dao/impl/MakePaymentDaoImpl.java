package com.byb.bhojan.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MakePaymentDao;
import com.byb.bhojan.model.MessPayment;

@Repository
public class MakePaymentDaoImpl implements MakePaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void makePaymentEntry(MessPayment messPayment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(messPayment);
	}

	@Override
	public void updateMessPaymentEntry(MessPayment messPayment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(messPayment);
	}

}
