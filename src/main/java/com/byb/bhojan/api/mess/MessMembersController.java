package com.byb.bhojan.api.mess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
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
import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MealServices;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MemberMealServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.services.impl.AndroidPush;
import com.byb.bhojan.util.DateUtils;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/mess/members")
public class MessMembersController extends BaseController {

	Logger logger = Logger.getLogger(MessMembersController.class);

	@Autowired
	private UserServices userServices;

	@Autowired
	private MealServices mealServices;

	@Autowired
	private MemberMealServices memberMealServices;

	@Autowired
	private MealCoupenServices mealCoupenServices;

	@Autowired
	private MemberMealCoupenServices memberMealCoupenServices;

	@Autowired
	private MemberMealCoupenHistoryServices memberMealCoupenHistoryServices;

	@Autowired
	private AndroidPush notification;

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
		memberMealCoupen.setExpiryDate(DateUtils.getDateAfterDays(new Date(), mealCoupen.getValidity() - 1));
		memberMealCoupen.setNoOfMeals(mealCoupen.getNoOfMeals());
		memberMealCoupen.setRemainingMealCount(mealCoupen.getNoOfMeals());
		memberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
		memberMealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

		memberMealCoupenServices.saveMemberMealCoupen(memberMealCoupen);

		memberMealCoupenHistoryServices.saveMemberMealCoupen(savedMember.getUserIdPk(), mealCoupen.getCoupenId());

		return sendSuccessResponse("Member Registration Successful !");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> gerMemberDeatil(@RequestParam("memberId") String memberId) {

		User member = userServices.getMemberByMemberId(memberId);

		JSONObject result = new JSONObject();

		if (member != null) {

			result.put("member", member);

			MemberMealCoupen activeMealCoupen = memberMealCoupenServices.getActiveMealCoupenByMember(member);
			MemberMealCoupen waitingMealCoupen = memberMealCoupenServices.getWaitingMealCoupenByMember(member);

			result.put("activeMealCoupen", activeMealCoupen);
			result.put("waitingMealCoupen", waitingMealCoupen);

			if (activeMealCoupen == null) {
				MemberMealCoupen lastExpiredOrConsumedMealCoupen = memberMealCoupenServices.getLastExpiredOrConsumedMealCoupenByMember(member);
				result.put("lastExpiredOrConsumedMealCoupen", lastExpiredOrConsumedMealCoupen);
			}

			return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
		}
		return sendErrorResponse("Member Not Found !");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{start}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> gerMembers(@PathVariable("start") int start, @PathVariable("limit") int limit) {

		Mess mess = getLoggedInMessByAppKey();

		List<AppMember> ml = userServices.getMemberListForAppByMessIdPk(mess.getMessIdPk());

		JSONObject result = new JSONObject();

		List<Meal> meals = mealServices.getOpenedMealsByMessId(mess.getMessIdPk());

		if (meals.size() > 0) {
			result.put("isActiveMealAvailable", true);
			result.put("activeMealId", meals.get(0).getMealId());
			result.put("activeMealType", 1);
			if (meals.get(0).getIsNonVeg()) {
				result.put("activeMealType", 2);
			}

		} else {
			result.put("isActiveMealAvailable", false);
		}

		result.put("memberList", ml);

		return new ResponseEntity<JSONObject>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{start}/{limit}/{searchString}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> gerMembersWithSearchString(@PathVariable("searchString") String searchString, @PathVariable("start") int start, @PathVariable("limit") int limit) {

		Mess mess = getLoggedInMessByAppKey();
		List<User> members = userServices.getMembersByMessAndSearch(searchString, mess, start, limit);

		return new ResponseEntity<List<User>>(members, HttpStatus.OK);
	}

	@RequestMapping(value = "/consumed", method = RequestMethod.GET)
	public ResponseEntity<List<MemberMeal>> gerMembersByMealId(@RequestParam(required = true, value = "mealId") String mealId) {

		List<MemberMeal> memberMeals = memberMealServices.getMemberMealsByMealId(mealId);
		return new ResponseEntity<List<MemberMeal>>(memberMeals, HttpStatus.OK);
	}

	@RequestMapping(value = "/renew-mealcoupen", method = RequestMethod.POST)
	public ResponseEntity<?> renewMealCoupen(@RequestParam(required = true, value = "memberId") String memberId, @RequestParam(required = true, value = "coupenId") String coupenId) {

		Mess mess = getLoggedInMessByAppKey();
		User member = userServices.getMemberByMemberId(memberId);
		MealCoupen mealCoupen = mealCoupenServices.getMealCoupenById(coupenId);

		MemberMealCoupen newMemberMealCoupen = new MemberMealCoupen();
		newMemberMealCoupen.setMember(member);
		newMemberMealCoupen.setMealCoupen(mealCoupen);
		newMemberMealCoupen.setNoOfMeals(mealCoupen.getNoOfMeals());
		newMemberMealCoupen.setRemainingMealCount(mealCoupen.getNoOfMeals());
		newMemberMealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

		MemberMealCoupen activeMemberMealCoupen = memberMealCoupenServices.getActiveMealCoupenByMember(member);
		if (activeMemberMealCoupen != null) {
			newMemberMealCoupen.setExpiryDate(DateUtils.getDateAfterDays(activeMemberMealCoupen.getExpiryDate(), mealCoupen.getValidity() - 1));
			newMemberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_WAITING);
		} else {
			newMemberMealCoupen.setExpiryDate(DateUtils.getDateAfterDays(new Date(), mealCoupen.getValidity() - 1));
			newMemberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
		}

		memberMealCoupenServices.saveMemberMealCoupen(newMemberMealCoupen);
		memberMealCoupenHistoryServices.saveMemberMealCoupen(member.getUserIdPk(), mealCoupen.getCoupenId());

		String title = "New Meal Coupon Added !";
		String msg = "New meal coupon '" + newMemberMealCoupen.getMealCoupen().toShortString() + "' is added in your account.";

		notification.sendPushNotification(title, msg, member.getUserIdPk());

		return sendSuccessResponseWithData("New meal coupon added successfully !", newMemberMealCoupen);
	}
}
