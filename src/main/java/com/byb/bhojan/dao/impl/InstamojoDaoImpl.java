package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.InstamojoDao;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLog;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;

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
	public void saveInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(instamojoPaymentLog);
	}

	@Override
	public void updateInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(instamojoPaymentLog);
	}

	@Override
	public void updateInstamojoPaymentRequestStatusById(InstamojoPaymentLog instamojoPaymentLog) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		InstamojoPaymentRequest instamojoPaymentRequest = getPaymentRequest(instamojoPaymentLog.getPayment_request_id());

		instamojoPaymentRequest.setStatus(instamojoPaymentLog.getStatus());
		instamojoPaymentRequest.setCreatedUpdated(new CreatedUpdated(instamojoPaymentRequest.getCreatedUpdated(), instamojoPaymentLog.getMess().getMessIdPk()));

		session.update(instamojoPaymentRequest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstamojoPaymentLog> getPaymentHistoryForAllMesses() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstamojoPaymentLog.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstamojoPaymentLog> getPaymentHistoryByMess(Mess mess) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstamojoPaymentLog.class);
		criteria.add(Restrictions.eq("mess", mess));
		return criteria.list();
	}

	@Override
	public InstamojoPaymentLog getInstamojoPaymentLogByPaymentReqId(String paymentReqId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(InstamojoPaymentLog.class);
		criteria.add(Restrictions.eq("payment_request_id", paymentReqId));
		return (InstamojoPaymentLog) criteria.uniqueResult();
	}

}
