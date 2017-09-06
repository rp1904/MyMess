package com.byb.bhojan.api.unguarded;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.member.MealCodeController;
import com.byb.bhojan.model.LoginModel;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AppSessionServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;

@RestController
@RequestMapping("/unguarded/login")
public class LoginController {

	Logger logger = Logger.getLogger(MealCodeController.class);

	@Autowired
	private AppSessionServices appSessionServices;

	@Autowired
	private UserServices userServices;

	@Autowired
	private MessServices messServices;

	@RequestMapping(value = "/member", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> doMemberLogin(@RequestBody LoginModel loginModel) {

		Map<String, Object> result = new HashMap<String, Object>();

		User user = userServices.getUserByEmailOrMobileNo(loginModel.getEmailOrMobileNumber());

		if (user == null) {
			result.put("responseType", "Error");
			result.put("message", "Invalid Email Or Mobile Number !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		if (user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MEMBER) {
			result.put("responseType", "Error");
			result.put("message", "Invalid User !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!encoder.matches(loginModel.getPassword(), user.getPassword())) {
			result.put("responseType", "Error");
			result.put("message", "Invalid Password !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		String appKey = appSessionServices.createNewAppSessionForUserId(user.getUserIdPk());

		result.put("responseType", "Success");
		result.put("rp_app_key", appKey);
		result.put("user", user);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/mess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> doMessLogin(@RequestBody LoginModel loginModel) {

		Map<String, Object> result = new HashMap<String, Object>();

		User user = userServices.getUserByEmailOrMobileNo(loginModel.getEmailOrMobileNumber());

		if (user == null) {
			result.put("responseType", "Error");
			result.put("message", "Invalid Email Or Mobile Number !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		if (user.getUserRole().getUserRoleId() != ProjectConstant.USER_ROLE_ID_MESS) {
			result.put("responseType", "Error");
			result.put("message", "Invalid User !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (!encoder.matches(loginModel.getPassword(), user.getPassword())) {
			result.put("responseType", "Error");
			result.put("message", "Invalid Password !");
			return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
		}

		String appKey = appSessionServices.createNewAppSessionForUserId(user.getUserIdPk());

		Mess mess = messServices.getMessByOwnerIdPk(user.getUserIdPk());

		result.put("responseType", "Success");
		result.put("rp_app_key", appKey);
		result.put("user", user);
		result.put("mess", mess);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
