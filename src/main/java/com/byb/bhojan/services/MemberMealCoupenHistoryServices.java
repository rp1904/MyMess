package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.MemberMealCoupenHistory;

public interface MemberMealCoupenHistoryServices {

	public void saveMemberMealCoupen(String memberId, String coupenId);

	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByUserId(String userId);

	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByMessId(String messId);

}
