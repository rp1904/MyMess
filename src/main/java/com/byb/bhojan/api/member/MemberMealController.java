package com.byb.bhojan.api.member;

import java.security.Principal;
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
import com.byb.bhojan.model.Meal;
import com.byb.bhojan.model.MemberMeal;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.MealServices;
import com.byb.bhojan.services.MemberMealServices;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/api/member/meals")
public class MemberMealController extends BaseController {

	Logger logger = Logger.getLogger(MemberMealController.class);

	@Autowired
	private MealServices mealServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private MemberMealServices memberMealServices;

	@RequestMapping(value = "/{start}/{limit}", method = RequestMethod.GET)
	public ResponseEntity<?> getMealList(Principal principal, @PathVariable("start") int start, @PathVariable("limit") int limit) {

		User member = getLoggedInUserByAppKey();

		List<MemberMeal> result = memberMealServices.getMemberMealsByMember(member, start, limit);

		return new ResponseEntity<List<MemberMeal>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> getActiveMeals() {

		Mess mess = userServices.getMessByMember(getLoggedInUserByAppKey());

		List<Meal> meals = mealServices.getOpenedMealsByMessId(mess.getMessIdPk());

		logger.info(meals);

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
