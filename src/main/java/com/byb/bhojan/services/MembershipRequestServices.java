package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.MembershipRequest;

public interface MembershipRequestServices {

	public List<MembershipRequest> getPendingMembershipRequestsByMessIdPk(String messIdPk);

	public List<MembershipRequest> getMembershipRequestsByMessId(String messId);

	public int saveMembershipRequests(Mess mess, User loggedInMember);

	public MembershipRequest getMembershipRequestByUserIdAndMessId(String userId, String messId);

	public MembershipRequest getMembershipRequestByUserId(String userIdPk);

	public MembershipRequest getPendingMembershipRequestByMessIdPkAndMemberIdPk(String messIdPk, String memberIdPk);

	public MembershipRequest updateMembershipRequest(MembershipRequest membershipRequest, String coupenId);

}
