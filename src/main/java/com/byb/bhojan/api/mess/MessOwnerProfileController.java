package com.byb.bhojan.api.mess;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/mess/profile")
public class MessOwnerProfileController extends BaseController {

	Logger logger = Logger.getLogger(MessOwnerProfileController.class);

	@Autowired
	private UserServices userServices;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMessOwnerProfile() {

		Mess mess = getLoggedInMessByAppKey();

		return new ResponseEntity<Mess>(mess, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMessOwnerProfile(@RequestBody User messOwner) {

		userServices.updateUser(messOwner);

		return sendSuccessResponse("Profile updated successfully!");
	}

	@RequestMapping(value = "/update-password", method = RequestMethod.POST)
	public ResponseEntity<?> updateMessOwnerPassword(@RequestBody User messOwner) {

		logger.info(messOwner.getPassword());

		User loggedInUser = getLoggedInUserByAppKey();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!encoder.matches(messOwner.getPassword(), loggedInUser.getPassword())) {
			return sendErrorResponse("Invalid old password !");
		}

		loggedInUser.setPassword(encoder.encode(messOwner.getConfirmPassword()));

		loggedInUser.setCreatedUpdated(
				new CreatedUpdated(loggedInUser.getCreatedUpdated(), ProjectConstant.CREATEDBY_SELF));

		userServices.updateUser(loggedInUser);

		return sendSuccessResponse("Password updated successfully!");
	}

}
