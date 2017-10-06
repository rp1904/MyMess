package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.Meal;

public interface MealServices {

	public int addMeal(Meal meal);

	public List<Meal> getLastMealsByMessIdPk(String messIdPk, int pageNumber, int pageSize);

	public Meal getLastAddedActiveMealForMemberId(String userIdPk);

	public Meal getMealByMealId(String mealId);

	public void closeMeal(Meal meal);

	public void closeAllOpenedMeals();

	public List<Meal> getOpenedMealsByMessId(String messIdPk);

	public Meal getDefaultMealByMessIdPk(String messIdPk);

}
