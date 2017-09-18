package com.byb.bhojan.api.comman;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.byb.bhojan.services.MemberMealCoupenServices;

@RestController
@RequestMapping(value = "/cron-job")
public class CronJobController extends BaseController {

  Logger logger = Logger.getLogger(CronJobController.class);

  @Autowired
  public MemberMealCoupenServices memberMealCoupenServices;

  @RequestMapping(value = "/check-coupen-expiry", method = RequestMethod.GET)
  public ResponseEntity<?> checkCoupenExpiry() {

    Date currentDate = new Date();
    int updatedMealCoupens = memberMealCoupenServices.updateExpiredMemberMealCoupen(currentDate);

    logger.info("Updated Meal Coupens: " + updatedMealCoupens);

    return sendSuccessResponse("Updated Meal Coupens: " + updatedMealCoupens);

  }

  @RequestMapping(value = "/make-payment-entry", method = RequestMethod.GET)
  public ResponseEntity<?> makePaymentEntry() {

    Date currentDate = new Date();
    int updatedMealCoupens = memberMealCoupenServices.updateExpiredMemberMealCoupen(currentDate);

    logger.info("Updated Meal Coupens: " + updatedMealCoupens);

    return sendSuccessResponse("Updated Meal Coupens: " + updatedMealCoupens);

  }
}
