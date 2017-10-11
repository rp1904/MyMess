package com.byb.bhojan.api.comman;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.util.DateUtils;

@RestController
@RequestMapping(value = "/cron-job")
public class CronJobController extends BaseController {

	Logger logger = Logger.getLogger(CronJobController.class);

	@Autowired
	public MemberMealCoupenServices memberMealCoupenServices;

	@Autowired
	public MessServices messServices;

	@RequestMapping(value = "/every-midnight", method = RequestMethod.GET)
	public ResponseEntity<?> everyMidNight() {

		logger.info("In Every Midnight Cronjob");

		Date updateDate = DateUtils.getStartOfDay(new Date());

		logger.info("Update Date: " + updateDate);

		StringBuilder resp = new StringBuilder();

		int updateCount = messServices.updateMessRemainingDays();
		resp.append(" Updated Mess Count: " + updateCount);

		int expiredMealCoupons = memberMealCoupenServices.updateExpiredMemberMealCoupen(updateDate);
		resp.append(". Expired Meal Coupons: " + expiredMealCoupons);

		int waitingToActiveMealCoupons = memberMealCoupenServices.updateWaitingMemberMealCoupen(updateDate);
		resp.append(". Waiting To Active Meal Coupons: " + waitingToActiveMealCoupons);

		logger.info(resp.toString());

		return sendSuccessResponse(resp.toString());
	}

}
