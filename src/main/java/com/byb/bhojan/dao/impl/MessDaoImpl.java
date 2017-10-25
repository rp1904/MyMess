package com.byb.bhojan.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MessDao;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessSetting;

@Repository
public class MessDaoImpl implements MessDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Mess getActiveMessByMessIdPk(String messIdPk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.add(Restrictions.eq("messIdPk", messIdPk));
		return (Mess) criteria.uniqueResult();
	}

	@Override
	public long getTotalActiveMessCount() {
		// TODO Auto-generated method stub
		return (long) sessionFactory.getCurrentSession().createCriteria(Mess.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Mess getActiveMessByMessId(String messId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.add(Restrictions.eq("messId", messId));
		return (Mess) criteria.uniqueResult();
	}

	@Override
	public Mess saveMess(Mess mess) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		mess.getMessOwner().setPassword(encoder.encode(mess.getMessOwner().getPassword()));
		session.save(mess);
		return mess;

	}

	@Override
	public Mess getMessByOwnerIdPk(String userIdPk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.createAlias("messOwner", "mo").add(Restrictions.eq("mo.userIdPk", userIdPk.trim()));
		return (Mess) criteria.uniqueResult();
	}

	@Override
	public boolean isMessNameAlreadyRegistered(String messName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.add(Restrictions.eq("messName", messName.trim()));
		if (criteria.uniqueResult() != null) {
			return true;
		}

		return false;
	}

	@Override
	public Mess getMessByMemberIdPk(String userIdPk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.createAlias("messMembers", "mm").add(Restrictions.eq("mm.userIdPk", userIdPk.trim()));
		return (Mess) criteria.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mess> getAllMessess() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Mess.class);
		criteria.createAlias("messOwner", "mo");
		criteria.addOrder(Order.asc("mo.mobileNumber"));
		return criteria.list();
	}

	@Override
	public void updateMess(Mess mess) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(mess);
	}

	@Override
	public int updateMessRemainingDays() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		//@formatter:off
		String hqlUpdate = "UPDATE Mess m SET m.daysRemaining = m.daysRemaining - 1, " 
				+ "m.createdUpdated.updatedBy = :UPDATED_BY, m.createdUpdated.updatedAt = :UPDATED_AT  " 
				+ "WHERE m.daysRemaining > 0";
		int updatedEntities = session.createQuery(hqlUpdate).setParameter("UPDATED_BY", "1")
				.setParameter("UPDATED_AT", new Date()).executeUpdate();
		//@formatter:on
		return updatedEntities;
	}

	@Override
	public void saveMessSetting(MessSetting messSetting) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(messSetting);
	}

	@Override
	public void updateMessSetting(MessSetting messSetting) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(messSetting);
	}

	@Override
	public MessSetting getMessSetting(String messIdFk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MessSetting.class);
		criteria.createAlias("mess", "m").add(Restrictions.eq("m.messIdPk", messIdFk));
		return (MessSetting) criteria.uniqueResult();
	}

}
