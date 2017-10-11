package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MessPaymentVoucherDao;
import com.byb.bhojan.model.MessPaymentVoucher;

@Repository
public class MessPaymentVoucherDaoImpl implements MessPaymentVoucherDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveVoucher(MessPaymentVoucher messPaymentVoucher) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(messPaymentVoucher);
	}

	@Override
	public void updateVoucher(MessPaymentVoucher messPaymentVoucher) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(messPaymentVoucher);
	}

	@Override
	public MessPaymentVoucher getVoucherById(String voucherId) {
		// TODO Auto-generated method stub
		return (MessPaymentVoucher) sessionFactory.getCurrentSession().get(MessPaymentVoucher.class, voucherId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessPaymentVoucher> getAllVouchers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MessPaymentVoucher.class);
		criteria.addOrder(Order.asc("amount"));
		return criteria.list();
	}

	@Override
	public void deleteVoucher(MessPaymentVoucher voucher) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(voucher);
	}

	@Override
	public long getTotalVoucherCount() {
		// TODO Auto-generated method stub
		return (long) sessionFactory.getCurrentSession().createCriteria(MessPaymentVoucher.class).setProjection(Projections.rowCount()).uniqueResult();
	}

}
