package com.byb.bhojan.api.unguarded;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.byb.bhojan.util.Dates;
import com.byb.bhojan.util.EncryptionUtils;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/unguarded/forget-password")
public class ForgetPassword extends BaseController {

	Logger logger = Logger.getLogger(ForgetPassword.class);
	
	@Value("${fp.lifetime.hrs}")
	private String forgetPasswordLinkLifetime;

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/make-request", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestParam("emailOrMobNo") String emailOrMobNo, HttpServletRequest request) {

		logger.info("Email Or Mob No: " + emailOrMobNo);
		User user = userServices.getUserByEmailOrMobileNo(emailOrMobNo);

		if (user == null) {
			return sendErrorResponse("Email/Mobile Number not registered with us !");
		} else {
			
			String contextPath = request.getContextPath();
			String resetPassWebPage = "/web/resetpassword/";
			
			Long expiresOnMillis =  Dates.getMillisAfterHours(new Date(), Integer.parseInt(forgetPasswordLinkLifetime));
			
			String link = user.getUserIdPk() + ProjectConstant.STRING_SEPERATOR + expiresOnMillis;

			String completePath = contextPath + resetPassWebPage + EncryptionUtils.encrypt(link); 
			
			logger.info("Password Reset Link : " + completePath);			
			
			logger.info("Password Reset Email Sent To : " + user.getEmail());
		}

		return sendSuccessResponse("Password Reset Successfully !");
	}
}
