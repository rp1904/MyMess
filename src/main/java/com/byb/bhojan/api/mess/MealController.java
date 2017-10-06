package com.byb.bhojan.api.mess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MemberMealServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.services.impl.AndroidPush;
import com.byb.bhojan.util.DateUtils;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/mess/meals")
public class MealController extends BaseController {

	Logger logger = Logger.getLogger(MealController.class);

	@Autowired
	private MealServices mealServices;

	@Autowired
	private MessServices messServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private MemberMealServices memberMealServices;

	@Autowired
	private AndroidPush notification;

	@Autowired
	private MemberMealCoupenServices memberMealCoupenServices;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMeal(@RequestBody Meal meal) {

		Mess mess = getLoggedInMessByAppKey();
		meal.setMess(mess);
		meal.setStatus(ProjectConstant.MEAL_STATUS_OPEN);
		meal.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

		mealServices.addMeal(meal);

		return sendSuccessResponse("Meal added successfully !");
	}

	@RequestMapping(value = "/{start}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<List<Meal>> getMealList(@PathVariable("start") int start, @PathVariable("limit") int limit) {

		Mess mess = getLoggedInMessByAppKey();

		List<Meal> meals = mealServices.getLastMealsByMessIdPk(messServices.getMessByOwnerIdPk(mess.getMessOwner().getUserIdPk()).getMessIdPk(), start, limit);

		return new ResponseEntity<List<Meal>>(meals, HttpStatus.OK);
	}

	@RequestMapping(value = "/read-meal-code/{code}", method = RequestMethod.GET)
	public ResponseEntity<?> readMealCode(@PathVariable("code") String qrCode) {

		Mess mess = getLoggedInMessByAppKey();

		String memberIdPk = qrCode.split("==")[0];
		String mealId = qrCode.split("==")[1];
		String mealType = qrCode.split("==")[2];
		String origin = qrCode.split("==")[3]; // QR or Manual

		for (int i = 0; i < 4; i++) {
			logger.info(qrCode.split("==")[i]);
		}

		User member = userServices.getUserByUserIdPk(memberIdPk);
		Meal meal = mealServices.getMealByMealId(mealId);

		if (member == null || meal == null) {
			return sendErrorResponse("Invalid QR Code !");
		}

		if (memberMealServices.isMealAlreadyConsumed(member, meal)) {

			return sendErrorResponse("Meal Already Consumed !");
		}

		//--------------------------------------------------------------------------------------------------------------		

		MemberMealCoupen activeMealCoupen = memberMealCoupenServices.getActiveMealCoupenByMember(member);

		logger.info(activeMealCoupen);

		if (activeMealCoupen == null) {
			return sendErrorResponse("No active meal coupen found !");
		}

		if (activeMealCoupen.getExpiryDate().getTime() < new Date().getTime()) {
			return sendErrorResponse("This meal coupen is expired !");
		}

		int lastMealcount = activeMealCoupen.getRemainingMealCount();

		if (lastMealcount <= 0) {
			return sendErrorResponse("No meals availabel in " + member.getUserProfile().getFullName() + " account !");
		} else {
			activeMealCoupen.setRemainingMealCount(lastMealcount - 1);
		}

		if (activeMealCoupen.getRemainingMealCount() == 0) {
			activeMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_CONSUMED);
			
			MemberMealCoupen waitingMemberMealCoupen = memberMealCoupenServices.getWaitingMealCoupenByMember(member);
			waitingMemberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
			waitingMemberMealCoupen.setCreatedUpdated(new CreatedUpdated(waitingMemberMealCoupen.getCreatedUpdated(), mess.getMessOwner().getUserIdPk()));
			memberMealCoupenServices.updateMemberMealCoupen(waitingMemberMealCoupen);
		}

		activeMealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));
		memberMealCoupenServices.updateMemberMealCoupen(activeMealCoupen);

		//--------------------------------------------------------------------------------------------------------------

		MemberMeal memberMeal = new MemberMeal();
		memberMeal.setMeal(meal);
		memberMeal.setMember(member);
		memberMeal.setMealType(mealType);
		memberMeal.setReadBy(origin);
		memberMeal.setRemainingMealCount(activeMealCoupen.getRemainingMealCount());
		memberMeal.setMealFor(ProjectConstant.MEAL_FOR_SELF);
		memberMeal.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

		memberMealServices.saveMemberMeal(memberMeal);

		//Notify member
		String title = "Your Meal Coupen Updated !";
		String msg = "You have " + memberMeal.getRemainingMealCount() + " meals left in your account.";
		notification.sendPushNotification(title, msg, member.getUserIdPk());

		String mealCoupenCountClass = "alert-status success";
		String mealCoupenExpiryClass = "alert-status success";

		long dayCount = 0;

		if (activeMealCoupen.getRemainingMealCount() < 5) {
			mealCoupenCountClass = "alert-status danger";
		}

		if (DateUtils.getDateAfterDays(new Date(), 4).getTime() > activeMealCoupen.getExpiryDate().getTime()) {
			mealCoupenExpiryClass = "alert-status danger";
		}

		dayCount = DateUtils.getDiffInDaysBetweenDates(activeMealCoupen.getExpiryDate(), new Date());

		// @formatter:off
		String responseMsg = "<strong>" + member.getUserProfile().getFullName() + "</strong>"
				+ "<span class='"+mealCoupenCountClass+"'>Meals Left: " + activeMealCoupen.getRemainingMealCount() + "</span>"
				+ "<span class='"+mealCoupenExpiryClass+"'>Days Left: " + dayCount	+ "</span>"
				+ "<span class='"+mealCoupenExpiryClass+"'>Expires on: " 
				+ DateUtils.getFormatedDate(activeMealCoupen.getExpiryDate(), ProjectConstant.DF_dd_MMM_yyyy)
				+ "</span>";
		// @formatter:on

		return sendSuccessResponse(responseMsg);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> closeMeal(@RequestParam(required = true, value = "mealId") String mealId) {

		Mess mess = getLoggedInMessByAppKey();
		Meal meal = mealServices.getMealByMealId(mealId);

		if (meal != null) {
			if (meal.getStatus().equals(ProjectConstant.MEAL_STATUS_CLOSED)) {
				return sendErrorResponse("Meal Alredy Closed !");
			} else {
				meal.setStatus(ProjectConstant.MEAL_STATUS_CLOSED);
				meal.setCreatedUpdated(new CreatedUpdated(meal.getCreatedUpdated(), mess.getMessOwner().getUserIdPk()));
				mealServices.closeMeal(meal);
				return sendSuccessResponse("Meal Status Updated Successfully !");
			}
		}

		return sendErrorResponse("Meal Not Found !");
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> getOpenMeals() {

		Mess mess = getLoggedInMessByAppKey();

		List<Meal> meals = mealServices.getOpenedMealsByMessId(mess.getMessIdPk());

		if (meals != null && meals.size() > 0) {
			return new ResponseEntity<List<Meal>>(meals, HttpStatus.OK);
		}

		return sendErrorResponse("Meal Not Found !");
	}

	@RequestMapping(value = "/{mealId}", method = RequestMethod.GET)
	public ResponseEntity<?> getMealDetails(@PathVariable("mealId") String mealId) {

		Meal meal = mealServices.getMealByMealId(mealId);

		if (meal != null) {

			logger.info(meal);

			return new ResponseEntity<Meal>(meal, HttpStatus.OK);
		}

		return sendErrorResponse("Meal Not Found !");
	}

}
