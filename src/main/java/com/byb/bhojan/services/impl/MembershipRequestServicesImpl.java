package com.byb.bhojan.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.dao.MembershipRequestDao;
import com.byb.bhojan.dao.UserDao;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.MembershipRequest;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MembershipRequestServices;
import com.byb.bhojan.util.Dates;
import com.byb.bhojan.util.ProjectConstant;

@Service
@Transactional
public class MembershipRequestServicesImpl implements MembershipRequestServices {

	@Autowired
	private MembershipRequestDao membershipRequestDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private MealCoupenServices mealCoupenServices;

	@Autowired
	private MemberMealCoupenServices memberMealCoupenServices;

	@Override
	public List<MembershipRequest> getMembershipRequestsByMessId(String messId) {
		// TODO Auto-generated method stub
		return membershipRequestDao.getMembershipRequestsByMessId(messId);
	}

	@Override
	public int saveMembershipRequests(Mess mess, User loggedInMember) {
		// TODO Auto-generated method stub
		MembershipRequest newMembershipRequest = new MembershipRequest();
		newMembershipRequest.setMember(loggedInMember);
		newMembershipRequest.setMess(mess);
		newMembershipRequest.setRequestStatus(ProjectConstant.MEMBERSHIP_REQUEST_PENDING);
		newMembershipRequest.setCreatedUpdated(new CreatedUpdated(loggedInMember.getUserIdPk()));

		return membershipRequestDao.saveMembershipRequests(newMembershipRequest);
	}

	@Override
	public MembershipRequest getMembershipRequestByUserIdAndMessId(String userId, String messId) {
		// TODO Auto-generated method stub
		return membershipRequestDao.getMembershipRequestByUserIdAndMessId(userId, messId);
	}

	@Override
	public MembershipRequest getMembershipRequestByUserId(String userIdPk) {
		// TODO Auto-generated method stub
		return membershipRequestDao.getMembershipRequestByUserId(userIdPk);
	}

	@Override
	public List<MembershipRequest> getPendingMembershipRequestsByMessIdPk(String messIdPk) {
		// TODO Auto-generated method stub
		return membershipRequestDao.getPendingMembershipRequestsByMessIdPk(messIdPk);
	}

	@Override
	public MembershipRequest getPendingMembershipRequestByMessIdPkAndMemberIdPk(String messIdPk, String memberIdPk) {
		// TODO Auto-generated method stub
		return membershipRequestDao.getPendingMembershipRequestByMessIdPkAndMemberIdPk(messIdPk, memberIdPk);
	}

	@Override
	public MembershipRequest updateMembershipRequest(MembershipRequest membershipRequest, String coupenId) {
		// TODO Auto-generated method stub
		if (membershipRequest.getRequestStatus().equals(ProjectConstant.MEMBERSHIP_REQUEST_REJECTED)) {
			MembershipRequest request = membershipRequestDao.updateMembershipRequest(membershipRequest);
			request.getMember().setMess(request.getMess());
			userDao.updateUser(request.getMember());
			return membershipRequestDao.updateMembershipRequest(membershipRequest);
		}

		if (membershipRequest.getRequestStatus().equals(ProjectConstant.MEMBERSHIP_REQUEST_ACCEPTED)) {

			MealCoupen mealCoupen = mealCoupenServices.getMealCoupenById(coupenId);

			MemberMealCoupen memberMealCoupen = new MemberMealCoupen();
			memberMealCoupen.setMember(membershipRequest.getMember());
			memberMealCoupen.setMealCoupen(mealCoupen);
			memberMealCoupen.setExpiryDate(Dates.getDateAfterDays(new Date(), mealCoupen.getValidity()));
			memberMealCoupen.setNoOfMeals(mealCoupen.getNoOfMeals());
			memberMealCoupen.setRemainingMealCount(mealCoupen.getNoOfMeals());
			memberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
			memberMealCoupen
					.setCreatedUpdated(new CreatedUpdated(membershipRequest.getMess().getMessOwner().getUserIdPk()));

			memberMealCoupenServices.saveMemberMealCoupen(memberMealCoupen);

			MembershipRequest request = membershipRequestDao.updateMembershipRequest(membershipRequest);
			request.getMember().setMess(request.getMess());
			userDao.updateUser(request.getMember());
			return membershipRequestDao.updateMembershipRequest(membershipRequest);
		}

		return null;
	}

}
