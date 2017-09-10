package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MessDao;
import com.byb.bhojan.model.Mess;

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
		return criteria.list();
	}

}
