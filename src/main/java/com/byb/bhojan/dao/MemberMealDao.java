package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.User;

public interface MemberMealDao {

	public void saveMemberMeal(MemberMeal memberMeal);

	public boolean isMealAlreadyConsumed(User member, Meal meal);

	public int getMealCountForMember(User member);

	public List<MemberMeal> getMemberMealsByMealId(String mealId);

	public List<MemberMeal> getMemberMealsByMember(User member, int pageNumber, int pageSize);
}
