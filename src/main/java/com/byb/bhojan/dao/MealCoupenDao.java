package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.Mess;

public interface MealCoupenDao {

	public boolean saveMealCoupen(MealCoupen mealCoupen);

	public void updateMealCoupen(MealCoupen mealCoupen);

	public List<MealCoupen> getMealCoupensByMess(Mess mess);

	public MealCoupen getMealCoupenById(String coupenId);

}
