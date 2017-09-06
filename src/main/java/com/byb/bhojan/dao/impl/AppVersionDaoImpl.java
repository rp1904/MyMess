package com.byb.bhojan.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.AppVersionDao;
import com.byb.bhojan.model.AppVersion;
import com.byb.bhojan.util.ProjectConstant;

@Repository
@Transactional
public class AppVersionDaoImpl implements AppVersionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMessAppVersion(AppVersion appVersion) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(appVersion);
	}

	@Override
	public void saveMemberAppVersion(AppVersion appVersion) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(appVersion);
	}

	@Override
	public AppVersion getLatestMessAppVersion() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppVersion.class);
		criteria.add(Restrictions.eq("appType", ProjectConstant.USER_ROLE_MESS));
		criteria.addOrder(Order.desc("releaseDate"));
		criteria.setMaxResults(1);
		return (AppVersion) criteria.uniqueResult();
	}

	@Override
	public AppVersion getLatestMemberAppVersion() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppVersion.class);
		criteria.add(Restrictions.eq("appType",
				ProjectConstant.USER_ROLE_MEMBER));
		criteria.addOrder(Order.desc("releaseDate"));
		criteria.setMaxResults(1);
		return (AppVersion) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppVersion> getAllMessAppVersions() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppVersion.class);
		criteria.add(Restrictions.eq("appType", ProjectConstant.USER_ROLE_MESS));
		criteria.addOrder(Order.desc("releaseDate"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppVersion> getAllMemberAppVersions() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AppVersion.class);
		criteria.add(Restrictions.eq("appType",
				ProjectConstant.USER_ROLE_MEMBER));
		criteria.addOrder(Order.desc("releaseDate"));
		return criteria.list();
	}

}
