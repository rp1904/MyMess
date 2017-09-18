package com.byb.bhojan.api.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.MembershipRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MembershipRequestServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.impl.AndroidPush;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/membershiprequest")
public class MembershipRequestController extends BaseController {

  Logger logger = Logger.getLogger(MembershipRequestController.class);

  @Autowired
  private AndroidPush notification;

  @Autowired
  private MessServices messServices;

  @Autowired
  private MembershipRequestServices membershipRequestServices;

  @RequestMapping(value = "/make", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> makeMembershipRequest(@RequestBody String messCode) {

    User loggedInMember = getLoggedInUserByAppKey();

    MembershipRequest membershipRequest = membershipRequestServices
        .getMembershipRequestByUserIdAndMessId(loggedInMember.getUserIdPk(), messCode);

    if (membershipRequest == null) {

      Mess mess = messServices.getActiveMessByMessId(messCode);
      if (mess == null) {
        return sendErrorResponse("Invalid Mess Code !");
      } else {
        membershipRequestServices.saveMembershipRequests(mess, loggedInMember);
        MembershipRequest mr = membershipRequestServices
            .getMembershipRequestByUserIdAndMessId(loggedInMember.getUserIdPk(), messCode);

        try {

          String title = "New Membership Request !";
          String msg = "You have received new membership request.";

          notification.sendPushNotification(title, msg, mess.getMessOwner().getUserIdPk());

        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        return new ResponseEntity<>(mr, HttpStatus.OK);
      }
      // return sendSuccessResponse("Request Sent Successfully To Mess: "
      // + mess.getMessName());

    } else {

      if (membershipRequest.getRequestStatus().equals(ProjectConstant.MEMBERSHIP_REQUEST_PENDING)) {

        return sendAlertResponse(
            "Your Request Is Pending For Mess: " + membershipRequest.getMess().getMessName());
      }

    }

    return sendErrorResponse("Request Sending Failed !");
  }

  @RequestMapping(value = "/check", method = RequestMethod.GET)
  public ResponseEntity<?> checkMembershipRequest() {

    User loggedInMember = getLoggedInUserByAppKey();

    // MembershipRequest membershipRequest = membershipRequestServices
    // .getMembershipRequestByUserId(loggedInMember.getUserIdPk());

    MembershipRequest membershipRequest =
        membershipRequestServices.getMembershipRequestByUserId(loggedInMember.getUserIdPk());

    if (membershipRequest != null) {
      return new ResponseEntity<>(membershipRequest, HttpStatus.OK);
    }

    return sendErrorResponse("Not Found !");

  }
}
