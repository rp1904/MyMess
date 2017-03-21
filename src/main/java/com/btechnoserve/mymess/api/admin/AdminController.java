package com.btechnoserve.mymess.api.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.btechnoserve.mymess.api.commancontroller.BaseController;
import com.btechnoserve.mymess.services.AdminServices;

@RestController
@RequestMapping("/api")
public class AdminController extends BaseController{

	@Autowired
	private AdminServices adminServices;

	@RequestMapping(value = "/header-test", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getAllAdmins() {

		Map<String,String> result = new HashMap<String, String>();
		result.put("old-key", getHeaderMyAPIKey());
		
		return result;
	}
}
