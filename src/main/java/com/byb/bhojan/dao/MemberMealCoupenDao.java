package com.byb.bhojan.dao;

import java.util.Date;

import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;

public interface MemberMealCoupenDao {

	void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	public MemberMealCoupen getMealCoupenByMember(User member);

	int updateExpiredMemberMelaCoupen(Date currentDate);

}
