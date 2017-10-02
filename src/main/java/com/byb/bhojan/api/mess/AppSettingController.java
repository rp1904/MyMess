package com.byb.bhojan.api.mess;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.AdminSettingServices;

@RestController
@RequestMapping("/api/mess")
public class AppSettingController extends BaseController {

	Logger logger = Logger.getLogger(AppSettingController.class);

	@Autowired
	private AdminSettingServices adminSettingServices;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/app-settings", method = RequestMethod.GET)
	public ResponseEntity<?> getMessAppSettings() {

		Mess mess = getLoggedInMessByAppKey();

		AdminSetting adminSetting = adminSettingServices.getAdminSettings();

		JSONObject resultData = new JSONObject();

		int remainingDays = mess.getDaysRemaining();

		logger.info("Remaining Days: " + remainingDays);

		if (remainingDays < 1) {
			resultData.put("showVouchers", true);
			resultData.put("showSkip", false);
			resultData.put("showMessage", "Please make payment to continue with our servise.");

			return sendSuccessResponseWithData("Success", resultData);
		}

		if (remainingDays <= adminSetting.getNotifyBeforeDays()) {
			resultData.put("showVouchers", true);
			resultData.put("showSkip", true);
			String day_days = " days ";
			if (remainingDays <= 1) {
				day_days = " day ";
			}

			resultData.put("showMessage", "You have " + remainingDays + day_days + "left in your account. Please make payment to avoid discouninuation of servise.");
		} else {
			resultData.put("showVouchers", false);
			resultData.put("showSkip", true);
			resultData.put("showMessage", "-");
		}

		return sendSuccessResponseWithData("Success", resultData);
	}

}
