package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.MemberMealCoupenHistory;

public interface MemberMealCoupenHistoryDao {

	public void saveMemberMealCoupen(MemberMealCoupenHistory memberMealCoupenHistory);

	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByUserId(String userId);

	public List<MemberMealCoupenHistory> getMemberMealCoupenHistoryByMessId(String messId);
}
