package com.btechnoserve.mymess.api.unguarded;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.btechnoserve.mymess.model.CreatedUpdated;
import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.UserServices;
import com.btechnoserve.mymess.util.ProjectConstant;

@RestController
@RequestMapping("/unguarded/registration")
public class Registration {

	@Autowired
	public UserServices userServices;

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public @ResponseBody User registerMember(@RequestBody User member) {

		member.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
		CreatedUpdated createdUpdated = new CreatedUpdated();
		createdUpdated.setCreatedAt(new Date());
		createdUpdated.setCreatedBy(ProjectConstant.CREATEDBY_SELF);
		member.setCreatedUpdated(createdUpdated);

		return userServices.saveUser(member);
	}
}
