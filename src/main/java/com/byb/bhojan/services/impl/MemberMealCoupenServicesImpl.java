package com.byb.bhojan.services.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byb.bhojan.dao.MemberMealCoupenDao;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MemberMealCoupenServices;

@Service
@Transactional
public class MemberMealCoupenServicesImpl implements MemberMealCoupenServices {

	@Autowired
	private MemberMealCoupenDao memberMealCoupenDao;

	@Override
	public void saveMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
		// TODO Auto-generated method stub
		memberMealCoupenDao.saveMemberMealCoupen(memberMealCoupen);
	}

	@Override
	public void updateMemberMealCoupen(MemberMealCoupen memberMealCoupen) {
		// TODO Auto-generated method stub
		memberMealCoupenDao.updateMemberMealCoupen(memberMealCoupen);
	}

	@Override
	public MemberMealCoupen getActiveMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.getActiveMealCoupenByMember(member);
	}

	@Override
	public int updateExpiredMemberMealCoupen(Date updatedAt) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.updateExpiredMemberMealCoupen(updatedAt);
	}

	@Override
	public List<MemberMealCoupen> getMealCoupenHistoryByMember(User member) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.getMealCoupenHistoryByMember(member);
	}

	@Override
	public MemberMealCoupen getWaitingMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.getWaitingMealCoupenByMember(member);
	}

	@Override
	public List<MemberMealCoupen> getNonWaitingMealCoupensByMember(User member) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.getNonWaitingMealCoupensByMember(member);
	}

	@Override
	public MemberMealCoupen getLastExpiredOrConsumedMealCoupenByMember(User member) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.getLastExpiredOrConsumedMealCoupenByMember(member);
	}

	@Override
	public int updateWaitingMemberMealCoupen(Date updatedAt) {
		// TODO Auto-generated method stub
		return memberMealCoupenDao.updateWaitingMemberMealCoupen(updatedAt);
	}

}
