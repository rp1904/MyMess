package com.byb.bhojan.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.InstamojoDao;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLogs;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;

@Repository
public class InstamojoDaoImpl implements InstamojoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savePaymentRequestResponse(InstamojoPaymentReqResponse instamojoPaymentReqResponse) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(instamojoPaymentReqResponse);
	}

	@Override
	public InstamojoPaymentRequest getPaymentRequest(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return (InstamojoPaymentRequest) session.get(InstamojoPaymentRequest.class, id);
	}

	@Override
	public void saveInstamojoPaymentLogs(InstamojoPaymentLogs instamojoPaymentLogs) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(instamojoPaymentLogs);
	}

	@Override
	public void updateInstamojoPaymentRequestStatusById(InstamojoPaymentLogs instamojoPaymentLogs) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		InstamojoPaymentRequest instamojoPaymentRequest = getPaymentRequest(
				instamojoPaymentLogs.getPayment_request_id());

		instamojoPaymentRequest.setStatus(instamojoPaymentLogs.getStatus());
		instamojoPaymentRequest.setCreatedUpdated(
				new CreatedUpdated(instamojoPaymentRequest.getCreatedUpdated(), instamojoPaymentLogs.getMessId()));

		session.update(instamojoPaymentRequest);
	}

}
