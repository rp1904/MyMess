package com.byb.bhojan.validators;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.EncryptionUtils;
import com.byb.bhojan.util.ProjectConstant;

public class ForgetPasswordTokenValidator {

	Logger logger = Logger.getLogger(ForgetPasswordTokenValidator.class);

	@Autowired
	public UserServices userServices;

	public Map<String, Object> validateToken(String token) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {

			String decryptedToken = EncryptionUtils.decrypt(token);

			String[] tokenParams = decryptedToken.split(ProjectConstant.STRING_SEPERATOR);

			String userIdPk = tokenParams[0];

			User userFromDb = userServices.getUserByUserIdPk(userIdPk);

			logger.info(userFromDb);

			long validTillMillis = Long.parseLong(tokenParams[1]);

			logger.info("Valid Till" + new Date(validTillMillis));

			// Check for link expiry
			if (validTillMillis < new Date().getTime()) {

				logger.info("This password reset link is expired !");

				result.put("status", ProjectConstant.STATUS_DANGER);
				result.put("message", "This password reset link is expired !");
				return result;
			}

			result.put("status", ProjectConstant.STATUS_SUCCESS);
			result.put("user", userFromDb);
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		result.put("status", ProjectConstant.STATUS_DANGER);
		result.put("message", "Ivalid token !");
		return result;
	}

}
