package com.byb.bhojan.services;

import java.util.Date;

import com.byb.bhojan.model.User;
import com.byb.bhojan.model.MemberMealCoupen;

public interface MemberMealCoupenServices {

	void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	public MemberMealCoupen getMealCoupenByMember(User member);

	int updateExpiredMemberMealCoupen(Date currentDate);

}
