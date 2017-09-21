package com.byb.bhojan.api.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MemberMealCoupenServices;

@RestController
@RequestMapping("/api/member/my-details")
public class MemberDetailsController extends BaseController {

  Logger logger = Logger.getLogger(MemberDetailsController.class);

  @Autowired
  private MemberMealCoupenServices memberMealCoupenServices;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<?> gerMemberDeatil() {

    User member = getLoggedInUserByAppKey();

    if (member != null) {
      MemberMealCoupen memberMealCoupen = memberMealCoupenServices.getActiveMealCoupenByMember(member);
      return new ResponseEntity<MemberMealCoupen>(memberMealCoupen, HttpStatus.OK);
    }

    return sendErrorResponse("Details Not Found !");
  }

}
