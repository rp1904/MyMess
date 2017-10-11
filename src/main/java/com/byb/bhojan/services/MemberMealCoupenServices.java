package com.byb.bhojan.services;

import java.util.Date;
import java.util.List;

import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;

public interface MemberMealCoupenServices {

	void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen);

	public MemberMealCoupen getActiveMealCoupenByMember(User member);

	public MemberMealCoupen getLastExpiredOrConsumedMealCoupenByMember(User member);

	public MemberMealCoupen getWaitingMealCoupenByMember(User member);

	public List<MemberMealCoupen> getNonWaitingMealCoupensByMember(User member);

	public List<MemberMealCoupen> getMealCoupenHistoryByMember(User member);

	public int updateExpiredMemberMealCoupen(Date updatedAt);

	public int updateWaitingMemberMealCoupen(Date updatedAt);

}
