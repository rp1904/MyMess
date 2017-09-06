package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/unguarded/forget-password")
public class ForgetPassword extends BaseController {

	Logger logger = Logger.getLogger(ForgetPassword.class);

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/make-request", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestParam("emailOrMobNo") String emailOrMobNo) {

		logger.info("Email Or Mob No: " + emailOrMobNo);
		User user = userServices.getUserByEmailOrMobileNo(emailOrMobNo);

		if (user == null) {
			return sendErrorResponse("Email/Mobile Number not registered with us !");
		} else {
			logger.info("Password Reset Email Sent To : " + user.getEmail());
		}

		return sendSuccessResponse("Password Reset Successfully !");
	}
}
