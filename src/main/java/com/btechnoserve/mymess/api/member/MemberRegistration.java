package com.btechnoserve.mymess.api.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.UserServices;

@RestController
@RequestMapping("/api")
public class MemberRegistration {

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public @ResponseBody User testAPI(@RequestBody User member) {

		return member;
	}

	@RequestMapping(value = "/member-all", method = RequestMethod.GET)
	public @ResponseBody List<User> testAPI() {

		return userServices.getAllMembers();
	}
}
