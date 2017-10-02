package com.byb.bhojan.api.comman;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byb.bhojan.services.MemberMealCoupenServices;

@Controller
@RequestMapping(value = "/cron-job")
public class CronJobController extends BaseController {

	Logger logger = Logger.getLogger(CronJobController.class);

	Date currentDate = new Date();

	@Autowired
	public MemberMealCoupenServices memberMealCoupenServices;

	@RequestMapping(value = "/check-coupen-expiry", method = RequestMethod.GET)
	public void checkCoupenExpiry() {

		int updatedMealCoupens = memberMealCoupenServices.updateExpiredMemberMealCoupen(currentDate);

		logger.info("Updated Meal Coupens: " + updatedMealCoupens);

	}

	@RequestMapping(value = "/make-payment-entry", method = RequestMethod.GET)
	public void makePaymentEntry() {

		int updatedMealCoupens = memberMealCoupenServices.updateExpiredMemberMealCoupen(currentDate);

		logger.info("Updated Meal Coupens: " + updatedMealCoupens);

	}

	@RequestMapping(value = "/every-midnight", method = RequestMethod.GET)
	public void everyMidNight() {

		logger.info("In Every Midenight Cronjob");

		//update mess remaining days where remaining days > 0

		//check coupen expiry

	}

}
