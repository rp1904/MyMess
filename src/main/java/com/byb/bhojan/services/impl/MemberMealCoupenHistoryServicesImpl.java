package com.byb.bhojan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.MemberMealCoupenHistoryDao;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.MemberMealCoupenHistory;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.UserServices;

@Service
@Transactional
public class MemberMealCoupenHistoryServicesImpl implements MemberMealCoupenHistoryServices {

	@Autowired
	private MemberMealCoupenHistoryDao memberMealCoupenHistoryDao;

	@Autowired
	private UserServices userServices;

	@Autowired
	private MealCoupenServices mealCoupenServices;

	@Override
	public void saveMemberMealCoupen(String memberId, String coupenId) {
		// TODO Auto-generated method stub

		User member = userServices.getMemberByMemberId(memberId);
		MealCoupen mealCoupen = mealCoupenServices.getMealCoupenById(coupenId);

		MemberMealCoupenHistory memberMealCoupenHistory = new MemberMealCoupenHistory();
		memberMealCoupenHistory.setMember(member);
		memberMealCoupenHistory.setMealCoupen(mealCoupen);
		memberMealCoupenHistory.setCreatedUpdated(new CreatedUpdated(mealCoupen.getCreatedUpdated().getCreatedBy()));

		memberMealCoupenHistoryDao.saveMemberMealCoupen(memberMealCoupenHistory);
	}

	@Override
	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByUserId(String userId) {
		// TODO Auto-generated method stub
		return memberMealCoupenHistoryDao.getMemberMealCoupenHistoryByUserId(userId);
	}

	@Override
	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByMessId(String messId) {
		// TODO Auto-generated method stub
		return memberMealCoupenHistoryDao.getMemberMealCoupenHistoryByMessId(messId);
	}

}
