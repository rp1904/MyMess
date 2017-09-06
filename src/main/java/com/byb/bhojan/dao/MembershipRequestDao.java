package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.MembershipRequest;

public interface MembershipRequestDao {

	public List<MembershipRequest> getMembershipRequestsByMessId(String messId);

	public MembershipRequest getMembershipRequestByUserIdAndMessId(String userId, String messId);

	public int saveMembershipRequests(MembershipRequest membershipRequest);

	public MembershipRequest getMembershipRequestByUserId(String userIdPk);

	public List<MembershipRequest> getPendingMembershipRequestsByMessIdPk(String messIdPk);

	public MembershipRequest getPendingMembershipRequestByMessIdPkAndMemberIdPk(String messIdPk, String memberIdPk);

	public MembershipRequest updateMembershipRequest(MembershipRequest membershipRequest);

}
