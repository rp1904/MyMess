package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/unguarded/reset-password")
public class ResetPassword extends BaseController {

	Logger logger = Logger.getLogger(ResetPassword.class);

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestParam("emailOrMobNo") String emailOrMobNo,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {

		logger.info("Email Or Mob No: " + emailOrMobNo);
		logger.info("Old Password: " + oldPassword);
		logger.info("New Password: " + newPassword);

		return sendSuccessResponse("Password Reset Successfully !");
	}
}
