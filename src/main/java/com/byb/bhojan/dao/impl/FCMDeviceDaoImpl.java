package com.byb.bhojan.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.FCMDeviceDao;
import com.byb.bhojan.model.FCMDevice;

@Repository
public class FCMDeviceDaoImpl implements FCMDeviceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdateFCMDevice(FCMDevice fcmDevice) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(fcmDevice);
	}

	@Override
	public FCMDevice getDeviceDetailsByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(FCMDevice.class);
		criteria.add(Restrictions.eq("userId", userId));
		return (FCMDevice) criteria.uniqueResult();
	}

	@Override
	public String getFCMtokenByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(FCMDevice.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.setProjection(Projections.property("fcmToken"));
		return (String) criteria.uniqueResult();
	}

	@Override
	public String getDevideIdByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(FCMDevice.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.setProjection(Projections.property("deviceId"));
		return (String) criteria.uniqueResult();
	}

}
