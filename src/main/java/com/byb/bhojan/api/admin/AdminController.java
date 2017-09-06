package com.byb.bhojan.api.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;

@RestController
@RequestMapping("/api")
public class AdminController extends BaseController {

	@RequestMapping(value = "/header-test", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getAllAdmins() {

		Map<String, String> result = new HashMap<String, String>();
		result.put("old-key", "");

		return result;
	}
}
