package com.byb.bhojan.api.member;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.MemberMealCoupenHistory;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/api/member/meal-coupons")
public class MemberMealCouponController extends BaseController {

	Logger logger = Logger.getLogger(MemberMealCouponController.class);

	@Autowired
	public UserServices userServices;

	@Autowired
	private MemberMealCoupenHistoryServices memberMealCoupenHistoryServices;

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ResponseEntity<?> getMealCode() {

		User loggedInMember = getLoggedInUserByAppKey();

		List<MemberMealCoupenHistory> couponHistory = memberMealCoupenHistoryServices.getMemberMealCoupenHistoryByUserId(loggedInMember.getUserIdPk());

		logger.info(couponHistory);

		return sendSuccessResponseWithData("Success", couponHistory);
	}

}
