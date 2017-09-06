package com.byb.bhojan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.dao.MealCoupenDao;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.services.MealCoupenServices;

@Service
@Transactional
public class MealCoupenServicesImpl implements MealCoupenServices {

	@Autowired
	private MealCoupenDao mealCoupenDao;

	@Override
	public boolean saveMealCoupen(MealCoupen mealCoupen) {
		// TODO Auto-generated method stub
		return mealCoupenDao.saveMealCoupen(mealCoupen);
	}

	@Override
	public void updateMealCoupen(MealCoupen mealCoupen) {
		// TODO Auto-generated method stub
		mealCoupenDao.updateMealCoupen(mealCoupen);
	}

	@Override
	public List<MealCoupen> getMealCoupensByMess(Mess mess) {
		// TODO Auto-generated method stub
		return mealCoupenDao.getMealCoupensByMess(mess);
	}

	@Override
	public MealCoupen getMealCoupenById(String coupenId) {
		// TODO Auto-generated method stub
		return mealCoupenDao.getMealCoupenById(coupenId);
	}

}
