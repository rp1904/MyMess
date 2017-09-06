package com.byb.bhojan.api.comman;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Otp;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.OtpServices;
import com.byb.bhojan.util.Dates;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;

@RestController
@RequestMapping("/unguarded/api/user")
public class UserBasicAPIController extends BaseController {

	Logger logger = Logger.getLogger(UserBasicAPIController.class);

	@Value("${otp.lifetime}")
	private String otpLifeTime;

	@Autowired
	private OtpServices otpServices;

	@RequestMapping(value = "/forgot-password/{email}", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) {

		if (email.trim().equals("")) {
			return sendErrorResponse("Email is required !");
		}

		User user = getLoggedInUser(email);

		if (user == null) {
			return sendErrorResponse("Account does not exist for this email !");
		}

		if (user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MESS
				&& user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MEMBER) {
			return sendErrorResponse("Invalid user !");
		}

		Otp otp = new Otp();
		otp.setUserId(user.getUserIdPk());
		otp.setOtpValue(RandomStringGenerator.getRandomNumber_MaxLen9(5));
		otp.setOtpFor(ProjectConstant.OTP_FOR_PASSWORD_RESET);
		otp.setStatus(ProjectConstant.OTP_ACTIVE);
		otp.setExpiresOn(Dates.getTimeStampAfterHours(new Date(), Integer.parseInt(otpLifeTime)));
		otp.setCreatedUpdated(new CreatedUpdated(user.getUserIdPk()));

		otpServices.saveOtp(otp);

		// Send Email

		return sendSuccessResponse("OTP for password reset has been sent to registered email address !");
	}

	@RequestMapping(value = "/change-password/{email}/{password}", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {

		if (email.trim().equals("")) {
			return sendErrorResponse("Email is required !");
		}

		if (password.trim().equals("")) {
			return sendErrorResponse("Password is required !");
		}

		User user = getLoggedInUser(email);

		if (user == null) {
			return sendErrorResponse("Account does not exist for this email !");
		}

		if (user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MESS
				&& user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MEMBER) {
			return sendErrorResponse("Invalid user !");
		}

		Otp otp = otpServices.getOtpActiveOtpByEmailId(email);

		if (otp == null) {
			return sendErrorResponse("Invalid OTP !");
		}

		otp.setStatus(ProjectConstant.OTP_VERIFIED);
		otp.setCreatedUpdated(new CreatedUpdated(otp.getCreatedUpdated(), user.getUserIdPk()));
		otpServices.updateOtp(otp);

		return sendSuccessResponse("Password updated successfully !");
	}
}
