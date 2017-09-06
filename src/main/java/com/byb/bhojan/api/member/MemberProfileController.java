package com.byb.bhojan.api.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/api/member/profile")
public class MemberProfileController extends BaseController {

	Logger logger = Logger.getLogger(MemberProfileController.class);

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMemberProfile(@RequestBody User member) {

		userServices.updateUser(member);

		return sendSuccessResponse("Profile updated successfully!");
	}

	@RequestMapping(value = "/update-password", method = RequestMethod.POST)
	public ResponseEntity<?> updateMemberPassword(@RequestBody User user) {

		logger.info(user.getPassword());

		User loggedInUser = getLoggedInUserByAppKey();

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!encoder.matches(user.getPassword(), loggedInUser.getPassword())) {
			return sendErrorResponse("Invalid old password !");
		}

		loggedInUser.setPassword(encoder.encode(user.getConfirmPassword()));

		loggedInUser.setCreatedUpdated(
				new CreatedUpdated(loggedInUser.getCreatedUpdated(), ProjectConstant.CREATEDBY_SELF));

		userServices.updateUser(loggedInUser);

		return sendSuccessResponse("Password updated successfully!");
	}

}
