package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MemberMealDao;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.User;

@Repository
public class MemberMealDaoImpl implements MemberMealDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMemberMeal(MemberMeal memberMeal) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(memberMeal);
	}

	@Override
	public boolean isMealAlreadyConsumed(User member, Meal meal) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMeal.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.add(Restrictions.eq("meal", meal));

		if (criteria.uniqueResult() != null) {
			return true;
		}

		return false;

	}

	@Override
	public int getMealCountForMember(User member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMeal.class);
		criteria.add(Restrictions.eq("member", member));
		criteria.setMaxResults(1);
		criteria.setProjection(Projections.property("remainingMealCount"));
		if (criteria.uniqueResult() != null) {
			return (int) criteria.uniqueResult();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMeal> getMemberMealsByMealId(String mealId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMeal.class);
		criteria.createAlias("meal", "m").add(Restrictions.eq("m.mealId", mealId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberMeal> getMemberMealsByMember(User member, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		List<MemberMeal> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MemberMeal.class);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(Restrictions.eq("member", member));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		result = criteria.list();
		return result;
	}

}
