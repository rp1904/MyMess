package com.byb.bhojan.api.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealServices;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/api/meal-code")
public class MealCodeController extends BaseController {

	Logger logger = Logger.getLogger(MealCodeController.class);

	@Autowired
	public UserServices userServices;

	@Autowired
	private MealServices mealServices;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getMealCode() {

		User loggedInMember = getLoggedInUserByAppKey();

		Meal meal = mealServices.getLastAddedActiveMealForMemberId(loggedInMember.getUserIdPk());

		if (meal != null) {
			String gMealCode = loggedInMember.getUserIdPk() + "==" + meal.getMealId();
			return sendSuccessResponse(gMealCode);
		}

		return sendErrorResponse("Not Available !");
	}

}
