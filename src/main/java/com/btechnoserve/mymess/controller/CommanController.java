package com.btechnoserve.mymess.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.UserServices;

@Controller
public class CommanController {

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/welcome**", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("admin/index");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("member") User member,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		// UserInfo userInfo = new UserInfo();
		// userInfo.setFirstName("Roshan");
		// userInfo.setLastName("Patil");
		// userInfo.setRegistrationDate(new Date());
		//
		// User user = new User();
		// user.setEmail("roshanpatil@gmail.com");
		// user.setEnabled(true);
		// user.setMobileNumber("8308238755");
		// user.setPassword("123456");
		// user.setUserInfo(userInfo);
		//
		// user.setUserRole(userServices.getUserRoleById(1));
		//
		// userServices.saveUser(user);

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

}