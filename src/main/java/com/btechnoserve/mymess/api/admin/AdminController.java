package com.btechnoserve.mymess.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.AdminServices;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	@Qualifier("adminServices")
	private AdminServices adminServices;

	@RequestMapping(value = "/admin-all", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllAdmins() {

		return adminServices.getAllAdmins();
	}
}
