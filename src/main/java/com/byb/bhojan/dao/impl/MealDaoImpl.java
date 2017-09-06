package com.byb.bhojan.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.MealDao;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.util.ProjectConstant;

@Repository
public class MealDaoImpl implements MealDao {

	Logger logger = Logger.getLogger(MealDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addMeal(Meal meal) {
		// TODO Auto-generated method stub
		logger.info(meal);
		Session session = sessionFactory.getCurrentSession();
		if (session.save(meal) != null) {
			return 1;
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meal> getLastMealsByMessIdPk(String messIdPk, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub

		List<Meal> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Meal.class);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.createAlias("mess", "m").add(Restrictions.eq("m.messIdPk", messIdPk.trim()));
		criteria.addOrder(Order.desc("createdUpdated.updatedAt"));
		result = criteria.list();
		return result;
	}

	@Override
	public Meal getLastAddedActiveMealByMess(Mess mess) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Meal.class);
		criteria.add(Restrictions.eq("mess", mess));
		criteria.add(Restrictions.eq("status", ProjectConstant.MEAL_STATUS_OPEN));
		// criteria.addOrder(Order.desc("createdUpdated.createdAt"));
		// criteria.setMaxResults(1);
		return (Meal) criteria.uniqueResult();
	}

	@Override
	public Meal getMealByMealId(String mealId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Meal.class);
		criteria.add(Restrictions.eq("mealId", mealId));
		// criteria.add(Restrictions.eq("status",
		// ProjectConstant.MEAL_STATUS_OPEN));

		return (Meal) criteria.uniqueResult();

	}

	@Override
	public void updateMeal(Meal meal) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(meal);
	}

	@Override
	public void closeAllOpenedMeals() { // updated_by
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(
				"UPDATE meals SET status=:STS_CLOSED, updated_by=:UB, updated_at=:UA, WHERE status=STS_OPEN");
		sqlQuery.setParameter("STS_CLOSED", ProjectConstant.MEAL_STATUS_CLOSED);
		sqlQuery.setParameter("UB", ProjectConstant.USER_ROLE_ID_SUPERADMIN);
		sqlQuery.setParameter("UA", new Date());
		sqlQuery.setParameter("STS_OPEN", ProjectConstant.MEAL_STATUS_OPEN);
		sqlQuery.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meal> getOpenedMealsByMessId(String messIdPk) {
		// TODO Auto-generated method stub
		List<Meal> result = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Meal.class);
		criteria.createAlias("mess", "m").add(Restrictions.eq("m.messIdPk", messIdPk.trim()));
		criteria.add(Restrictions.eq("status", ProjectConstant.MEAL_STATUS_OPEN));
		result = criteria.list();
		return result;
	}

}
