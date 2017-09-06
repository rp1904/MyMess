package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MealCoupen;

public interface MealCoupenServices {

	public boolean saveMealCoupen(MealCoupen mealCoupen);

	public void updateMealCoupen(MealCoupen mealCoupen);

	public List<MealCoupen> getMealCoupensByMess(Mess mess);

	public MealCoupen getMealCoupenById(String coupenId);

}
