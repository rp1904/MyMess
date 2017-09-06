package com.byb.bhojan.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MealCoupenDao;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.Mess;

@Repository
public class MealCoupenDaoImpl implements MealCoupenDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveMealCoupen(MealCoupen mealCoupen) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		if (session.save(mealCoupen) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateMealCoupen(MealCoupen mealCoupen) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(mealCoupen);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MealCoupen> getMealCoupensByMess(Mess mess) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MealCoupen.class);
		criteria.add(Restrictions.eq("mess", mess));
		return criteria.list();
	}

	@Override
	public MealCoupen getMealCoupenById(String coupenId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MealCoupen.class);
		criteria.add(Restrictions.eq("coupenId", coupenId.trim()));
		return (MealCoupen) criteria.uniqueResult();
	}

}
