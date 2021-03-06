package com.byb.bhojan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;

@Controller
public class CommanController {

	Logger logger = Logger.getLogger(CommanController.class);

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView("redirect:web/login");

		return model;

	}

	@RequestMapping(value = "/web/welcome", method = RequestMethod.GET)
	public ModelAndView afterLoginPage() {

		SimpleGrantedAuthority grantedAuthority = (SimpleGrantedAuthority) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities()).toArray()[0];

		ModelAndView model = new ModelAndView();

		String role = grantedAuthority.getAuthority();
		String emailIdRoMobNo = SecurityContextHolder.getContext().getAuthentication().getName();

		if (role.equals(ProjectConstant.USER_ROLE_SUPERADMIN)) {
			model.setViewName("super-admin/superadmin-home");
		} else if (role.equals(ProjectConstant.USER_ROLE_MESS)) {
			model.setViewName("admin/admin-home");
		} else {

			model.setViewName("member/select-mess");
			model.addObject("member", userServices.getUserByEmailOrMobileNo(emailIdRoMobNo));
		}

		return model;

	}

	@RequestMapping(value = "/web/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("member") User member,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

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

	@RequestMapping("/web/m")
	public ModelAndView loadLost() throws IOException {
		logger.info("------------------- ");

		// String message =
		// EmailTemplates.getDefaultForgotPasswordMessage("Roshan Patil",
		// "000000000");

		// new SendEmailThread("roshanpatil1904@gmail.com", message, "My Mess
		// Testing 1");

		return new ModelAndView("super-admin/list");
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid Credentials !";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid Email / Mobile Number !";
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