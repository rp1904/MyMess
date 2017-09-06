package com.byb.bhojan.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.dao.MealDao;
import com.byb.bhojan.dao.MessDao;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.services.MealServices;

@Service
@Transactional
public class MealServicesImpl implements MealServices {

	Logger logger = Logger.getLogger(MealServicesImpl.class);

	@Autowired
	private MealDao mealDao;

	@Autowired
	private MessDao messDao;

	@Override
	public int addMeal(Meal meal) {
		// TODO Auto-generated method stub
		return mealDao.addMeal(meal);
	}

	@Override
	public List<Meal> getLastMealsByMessIdPk(String messIdPk, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return mealDao.getLastMealsByMessIdPk(messIdPk, pageNumber, pageSize);
	}

	@Override
	public Meal getLastAddedActiveMealForMemberId(String userIdPk) {
		// TODO Auto-generated method stub
		Mess mess = messDao.getMessByMemberIdPk(userIdPk);
		return mealDao.getLastAddedActiveMealByMess(mess);
	}

	@Override
	public Meal getMealByMealId(String mealId) {
		// TODO Auto-generated method stub
		return mealDao.getMealByMealId(mealId);
	}

	@Override
	public void closeMeal(Meal meal) {
		// TODO Auto-generated method stub
		mealDao.updateMeal(meal);
	}

	@Override
	public void closeAllOpenedMeals() {
		// TODO Auto-generated method stub
		mealDao.closeAllOpenedMeals();
	}

	@Override
	public List<Meal> getOpenedMealsByMessId(String messIdPk) {
		// TODO Auto-generated method stub
		return mealDao.getOpenedMealsByMessId(messIdPk);
	}

}
