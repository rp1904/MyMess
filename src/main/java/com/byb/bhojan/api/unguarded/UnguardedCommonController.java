package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;

@RestController
@RequestMapping("/unguarded")
public class UnguardedCommonController extends BaseController {

	Logger logger = Logger.getLogger(UnguardedCommonController.class);

	@Value("${base_url}")
	private String baseUrl;

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/authenticate-password", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateSuperAdmin(@RequestBody User user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		User superAdmin = userServices.getSuperAdminDetails();

		if (encoder.matches(user.getPassword(), superAdmin.getPassword())) {
			return sendSuccessResponse("Authentication Successfull !");
		}

		return sendErrorResponse("Incorrect Password ! <br> Try again.");
	}
}
