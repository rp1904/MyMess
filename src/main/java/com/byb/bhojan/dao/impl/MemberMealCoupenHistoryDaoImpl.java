package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MemberMealCoupenHistoryDao;
import com.byb.bhojan.model.MemberMealCoupenHistory;

@Repository
public class MemberMealCoupenHistoryDaoImpl implements MemberMealCoupenHistoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMemberMealCoupen(MemberMealCoupenHistory memberMealCoupenHistory) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(memberMealCoupenHistory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByUserId(String userId) {
		// TODO Auto-generated method stub
		List<MemberMealCoupenHistory> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupenHistory.class);
		criteria.createAlias("member", "m").add(Restrictions.eq("m.userIdPk", userId));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		result = criteria.list();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByMessId(String messId) {
		// TODO Auto-generated method stub
		List<MemberMealCoupenHistory> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMealCoupenHistory.class);
		criteria.createAlias("mealCoupen", "mc").add(Restrictions.eq("mc.userIdPk", messId));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		result = criteria.list();

		return result;
	}

}
