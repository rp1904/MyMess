package com.byb.bhojan.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.byb.bhojan.services.UserServices;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	public UserServices userServices;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		StringBuffer requestURL = request.getRequestURL();
		if (request.getQueryString() != null) {
			requestURL.append("?").append(request.getQueryString());
		}

		logger.info(requestURL.toString());

		logger.info(" ==== >>> " + request.getHeader("rp-app-key"));

		if (userServices.getUserByAppKey(request.getHeader("rp-app-key")) == null) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Session expired. Please login again !");
			return false;
		}

		return true;

	}

}
