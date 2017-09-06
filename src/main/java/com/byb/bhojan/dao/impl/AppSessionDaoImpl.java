package com.byb.bhojan.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.AppSessionDao;
import com.byb.bhojan.model.AppSession;

@Repository
@Transactional
public class AppSessionDaoImpl implements AppSessionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveOrUpdateAppSession(AppSession appSession) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(appSession);
	}

	@Override
	public String getUserIdPkByAPIkey(String apiKey) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppSession.class);
		criteria.setProjection(Projections.property("userId"));
		criteria.add(Restrictions.eq("apiKey", apiKey));
		return (String) criteria.uniqueResult();
	}

	@Override
	public void deleteAppSession(AppSession appSession) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(appSession);
	}

	@Override
	public String getAPIkeyForUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppSession.class);
		criteria.setProjection(Projections.property("apiKey"));
		criteria.add(Restrictions.eq("userId", userId));
		return (String) criteria.uniqueResult();
	}

	@Override
	public AppSession getAppSessionByAPIKey(String apiKey) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppSession.class);
		criteria.add(Restrictions.eq("apiKey", apiKey));
		return (AppSession) criteria.uniqueResult();
	}

	@Override
	public AppSession getAppSessionByUserId(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppSession.class);
		criteria.add(Restrictions.eq("userId", userId));
		return (AppSession) criteria.uniqueResult();
	}

}
