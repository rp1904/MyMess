package com.byb.bhojan.api.mess;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MembershipRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.MembershipRequestServices;
import com.byb.bhojan.services.impl.AndroidPush;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/mess/new-membership-requests")
public class MessMembershipRequestController extends BaseController {

	Logger logger = Logger.getLogger(MessMembershipRequestController.class);

	@Autowired
	private MemberMealCoupenHistoryServices memberMealCoupenHistoryServices;

	@Autowired
	private MembershipRequestServices membershipRequestServices;

	@Autowired
	private AndroidPush notification;

	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public ResponseEntity<List<MembershipRequest>> makeMembershipRequest() {

		List<MembershipRequest> allRequests = null;
		Mess mess = getLoggedInMessByAppKey();
		allRequests = membershipRequestServices.getPendingMembershipRequestsByMessIdPk(mess.getMessIdPk());
		return new ResponseEntity<List<MembershipRequest>>(allRequests, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{status}/{member_id}/{coupen_id}", method = RequestMethod.GET)
	public ResponseEntity<?> updateMembershipRequest(@PathVariable("status") String status, @PathVariable("member_id") String memberIdPk, @PathVariable("coupen_id") String coupenId) {

		if (status.equals("A")) {
			status = ProjectConstant.MEMBERSHIP_REQUEST_ACCEPTED;

		} else {
			status = ProjectConstant.MEMBERSHIP_REQUEST_REJECTED;
		}

		Mess mess = getLoggedInMessByAppKey();
		MembershipRequest membershipRequest = membershipRequestServices.getPendingMembershipRequestByMessIdPkAndMemberIdPk(mess.getMessIdPk(), memberIdPk);

		if (membershipRequest == null) {
			return sendErrorResponse("Already Accepted !");
		}

		membershipRequest.setRequestStatus(status);
		membershipRequest.setCreatedUpdated(new CreatedUpdated(membershipRequest.getCreatedUpdated(), mess.getMessOwner().getUserIdPk()));

		MembershipRequest updatedMembershipRequest = membershipRequestServices.updateMembershipRequest(membershipRequest, coupenId);

		if (updatedMembershipRequest.getRequestStatus().equals(ProjectConstant.MEMBERSHIP_REQUEST_REJECTED)) {

			return sendErrorResponse("Request Rejected !");
		}

		memberMealCoupenHistoryServices.saveMemberMealCoupen(memberIdPk, coupenId);

		String title = "Membership Request Status !";
		String msg = updatedMembershipRequest.getMess().getMessName() + " has " + updatedMembershipRequest.getRequestStatus() + " your membership request.";

		notification.sendPushNotification(title, msg, memberIdPk);

		return sendSuccessResponse("Request Accepted Successfully !");

	}
}
