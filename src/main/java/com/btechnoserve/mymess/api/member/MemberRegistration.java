package com.btechnoserve.mymess.api.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.model.UserProfile;
import com.btechnoserve.mymess.services.UserServices;
import com.btechnoserve.mymess.util.ProjectConstant;

@RestController
@RequestMapping("/api/registration")
public class MemberRegistration {

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public @ResponseBody User registerMember() {

		// @RequestBody User member
		User member = new User();
		member.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
		member.setEmail("member2@mail.com");
		member.setMobileNumber("1111111112");
		member.setPassword("000");
		UserProfile profile = new UserProfile();
		profile.setFirstName("MEMER_2");

		userServices.saveUser(member);

		return userServices.getUserByEmail(member.getEmail());
	}

	@RequestMapping(value = "/member-all", method = RequestMethod.GET)
	public @ResponseBody List<User> testAPI() {

		return userServices.getAllMembers();
	}
}
