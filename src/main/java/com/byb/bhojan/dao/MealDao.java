package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.Mess;

public interface MealDao {

	public int addMeal(Meal meal);

	public List<Meal> getLastMealsByMessIdPk(String messIdPk, int pageNumber, int pageSize);

	public Meal getLastAddedActiveMealByMess(Mess mess);

	public Meal getMealByMealId(String mealId);

	public void updateMeal(Meal meal);

	public void closeAllOpenedMeals();

	public List<Meal> getOpenedMealsByMessId(String messIdPk);
}
