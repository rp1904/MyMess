package com.byb.bhojan.api.mess;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;

@RestController
@RequestMapping("/api/mess")
public class AppSettingController extends BaseController {

	//	Logger logger = Logger.getLogger(AppSettingController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/app-settings", method = RequestMethod.GET)
	public ResponseEntity<?> getMessAppSettings() {

		//		Mess mess = getLoggedInMessByAppKey();

		JSONObject resultData = new JSONObject();

		resultData.put("isVoucherExpired", true);

		return sendSuccessResponseWithData("Success", resultData);
	}

}
