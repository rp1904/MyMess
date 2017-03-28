package com.btechnoserve.mymess.api.comman;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseController {

	Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;

	
	public HttpServletRequest getRequest() {
		return request;
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
	
	public String getHeaderMyAPIKey() {
		return getRequest().getHeader("x-api-mykey");
	}
		
}
