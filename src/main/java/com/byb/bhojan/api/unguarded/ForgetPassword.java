package com.byb.bhojan.api.unguarded;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.services.impl.EmailService;
import com.byb.bhojan.util.DateUtils;
import com.byb.bhojan.util.EmailTemplates;
import com.byb.bhojan.util.EncryptionUtils;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/unguarded/forget-password")
public class ForgetPassword extends BaseController {

	Logger logger = Logger.getLogger(ForgetPassword.class);

	@Value("${base_url}")
	private String baseUrl;

	@Value("${fp.lifetime.hrs}")
	private String forgetPasswordLinkLifetime;

	@Autowired
	private EmailService emailService;

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/make-request", method = RequestMethod.GET)
	public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {

		logger.info("Email Req For Pass Reset: " + email);
		User user = userServices.getUserByEmailOrMobileNo(email);

		if (user == null) {
			return sendErrorResponse("Email is not registered with us !");
		} else {

			String resetPassWebPage = "/web/resetpassword?token=";

			Long expiresOnMillis = DateUtils.getMillisAfterHours(new Date(), Integer.parseInt(forgetPasswordLinkLifetime));

			String token = user.getUserIdPk() + ProjectConstant.STRING_SEPERATOR + expiresOnMillis;

			logger.info("Token Before Encrypt: " + token);

			String completePath = baseUrl + resetPassWebPage + EncryptionUtils.encrypt(token);

			logger.info("Password Reset Link : " + completePath);

			String msgBody = EmailTemplates.passwordResetText(user, completePath, forgetPasswordLinkLifetime);

			emailService.sendEmail(email, ProjectConstant.EMAIL_PASSWORD_RESET, msgBody);

			logger.info("Password Reset Email Sent To : " + user.getEmail());
		}

		return sendSuccessResponse("Password reset link sent on your registered email successfully !");
	}
}
