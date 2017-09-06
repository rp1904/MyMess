package com.byb.bhojan.api.comman;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.ResponseObject;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AppSessionServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;

public class BaseController {

	Logger logger = Logger.getLogger(BaseController.class);

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	public UserServices userServices;

	@Autowired
	private MessServices messServices;

	@Autowired
	private AppSessionServices appSessionServices;

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getAppKeyFromRequest() {
		getAllHeaders();
		return getRequest().getHeader("rp-app-key");
	}

	// @SuppressWarnings("unchecked")
	// public <T> T getLoggedInUserByAppKey() {
	// User u = userServices.getUserByAppKey(getAppKeyFromRequest());
	// if (u == null) {
	// Map<String, Object> result = new HashMap<String, Object>();
	// result.put("responseType", "Error");
	// result.put("message", "Session Out !");
	//
	// return (T) new ModelAndView("redirect:/myURL");
	//
	// // return (T) new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	// }
	// return (T) userServices.getUserByAppKey(getAppKeyFromRequest());
	// }

	public User getLoggedInUserByAppKey() {
		return userServices.getUserByAppKey(getAppKeyFromRequest());
	}

	public Mess getLoggedInMessByAppKey() {

		User loggedInUser = userServices.getUserByAppKey(getAppKeyFromRequest());
		return messServices.getMessByOwnerIdPk(loggedInUser.getUserIdPk());

	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public User getLoggedInUser(String emailIdOrMobNo) {

		return userServices.getUserByEmailOrMobileNo(emailIdOrMobNo);

	}

	public Mess getLoggedInMess(String emailIdOrMobNo) {

		User loggedInUser = userServices.getUserByEmailOrMobileNo(emailIdOrMobNo);
		return messServices.getMessByOwnerIdPk(loggedInUser.getUserIdPk());

	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity sendSuccessResponse(String message) {

		return new ResponseObject().getSuccessResponse(message);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity sendErrorResponse(String message) {

		return new ResponseObject().getErrorResponse(message);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity sendAlertResponse(String message) {

		return new ResponseObject().getAlertResponse(message);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity unauthorizedUser() {

		return new ResponseEntity<>("Session expired !", HttpStatus.UNAUTHORIZED);
	}

	@SuppressWarnings("rawtypes")
	protected Map<String, String> getAllHeaders() {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = getRequest().getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = getRequest().getHeader(key);
			map.put(key, value);
			logger.info(key + " : " + value);
		}

		return map;
	}

	protected void distroyMemberSession() {
		appSessionServices.deleteAppSessionByApiKey(getAppKeyFromRequest());
	}
}
