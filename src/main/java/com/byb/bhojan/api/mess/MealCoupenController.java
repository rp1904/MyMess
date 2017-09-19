package com.byb.bhojan.api.mess;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.MealCoupenServices;

@RestController
@RequestMapping("/api/mess/meal-coupens")
public class MealCoupenController extends BaseController {

  Logger logger = Logger.getLogger(MealCoupenController.class);

  @Autowired
  private MealCoupenServices mealCoupenServices;

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addMealCoupen(@RequestBody MealCoupen mealCoupen) {

    Mess mess = getLoggedInMessByAppKey();

    mealCoupen.setMess(mess);
    mealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));
    mealCoupenServices.saveMealCoupen(mealCoupen);

    List<MealCoupen> coupens = mealCoupenServices.getMealCoupensByMess(mess);

    return sendSuccessResponseWithData("Meal Coupen added successfully !", coupens);
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<MealCoupen>> getAllMealCoupens() {

    Mess mess = getLoggedInMessByAppKey();
    List<MealCoupen> coupens = mealCoupenServices.getMealCoupensByMess(mess);

    return new ResponseEntity<List<MealCoupen>>(coupens, HttpStatus.OK);
  }

}
