package com.byb.bhojan.api.mess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MemberMealServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.Dates;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/mess/members")
public class MessMembersController extends BaseController {

	Logger logger = Logger.getLogger(MessMembersController.class);

	@Autowired
	private UserServices userServices;

	@Autowired
	private MemberMealServices memberMealServices;

	@Autowired
	private MealCoupenServices mealCoupenServices;

	@Autowired
	private MemberMealCoupenServices memberMealCoupenServices;

	@Autowired
	private MemberMealCoupenHistoryServices memberMealCoupenHistoryServices;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerMember(@RequestBody User member) {

		Mess mess = getLoggedInMessByAppKey();

		if (userServices.isEmailAlreadyRegistered(member.getEmail())) {
			return sendErrorResponse("Email Already Registered With Us !");
		}

		if (userServices.isMobileNumberAlreadyRegistered(member.getMobileNumber())) {
			return sendErrorResponse("Mobile Already Registered With Us !");
		}

		member.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
		member.setMess(mess);

		MealCoupen mealCoupen = mealCoupenServices.getMealCoupenById(member.getUserIdPk());

		member.setUserIdPk(null);

		member.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));
		User savedMember = userServices.saveUser(member);

		MemberMealCoupen memberMealCoupen = new MemberMealCoupen();
		memberMealCoupen.setMember(savedMember);
		memberMealCoupen.setMealCoupen(mealCoupen);
		memberMealCoupen.setExpiryDate(Dates.getDateAfterDays(new Date(), mealCoupen.getValidity()));
		memberMealCoupen.setNoOfMeals(mealCoupen.getNoOfMeals());
		memberMealCoupen.setRemainingMealCount(mealCoupen.getNoOfMeals());
		memberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
		memberMealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

		memberMealCoupenServices.saveMemberMealCoupen(memberMealCoupen);

		memberMealCoupenHistoryServices.saveMemberMealCoupen(savedMember.getUserIdPk(), mealCoupen.getCoupenId());

		return sendSuccessResponse("Member Registration Successful !");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> gerMemberDeatil(@RequestParam("memberId") String memberId) {

		User member = userServices.getMemberByMemberId(memberId);

		if (member != null) {
			MemberMealCoupen memberMealCoupen = memberMealCoupenServices.getMealCoupenByMember(member);
			return new ResponseEntity<MemberMealCoupen>(memberMealCoupen, HttpStatus.OK);
		}

		return sendErrorResponse("Member Not Found !");
	}

	@RequestMapping(value = "/{start}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> gerMembers(@PathVariable("start") int start, @PathVariable("limit") int limit) {

		Mess mess = getLoggedInMessByAppKey();
		List<User> members = userServices.getMembersByMessAndSearch("", mess, start, limit);

		return new ResponseEntity<List<User>>(members, HttpStatus.OK);
	}

	@RequestMapping(value = "/{start}/{limit}/{searchString}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> gerMembersWithSearchString(@PathVariable("searchString") String searchString,
			@PathVariable("start") int start, @PathVariable("limit") int limit) {

		Mess mess = getLoggedInMessByAppKey();
		List<User> members = userServices.getMembersByMessAndSearch(searchString, mess, start, limit);

		return new ResponseEntity<List<User>>(members, HttpStatus.OK);
	}

	@RequestMapping(value = "/consumed", method = RequestMethod.GET)
	public ResponseEntity<List<MemberMeal>> gerMembersByMealId(
			@RequestParam(required = true, value = "mealId") String mealId) {

		List<MemberMeal> memberMeals = memberMealServices.getMemberMealsByMealId(mealId);
		return new ResponseEntity<List<MemberMeal>>(memberMeals, HttpStatus.OK);
	}

}
