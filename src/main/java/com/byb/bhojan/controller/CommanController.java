package com.byb.bhojan.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.EncryptionUtils;
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

		ModelAndView model = new ModelAndView("redirect:admin/home");

		return model;
	}

	@RequestMapping(value = "/web/resetpassword", method = RequestMethod.GET)
	public ModelAndView webResetPasswordGet(@RequestParam("token") String token, @ModelAttribute("user") User user) {

		ModelAndView modelAndView = new ModelAndView("password-reset");

		modelAndView.addObject("test", ProjectConstant.STATUS_DANGER);

		String decryptedToken = EncryptionUtils.decrypt(token);

		String[] tokenParams = decryptedToken.split(ProjectConstant.STRING_SEPERATOR);

		String userIdPk = tokenParams[0];

		User userFromDb = userServices.getUserByUserIdPk(userIdPk);

		logger.info(userFromDb);

		long validTillMillis = Long.parseLong(tokenParams[1]);

		logger.info("Valid Till" + new Date(validTillMillis));

		// Check for link expiry
		if (validTillMillis < new Date().getTime()) {

			logger.info("This password reset link is expired !");

			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("message", "This password reset link is expired !");
			return modelAndView;
		}

		user.setUserIdPk(userFromDb.getUserIdPk());
		user.setEmail(userFromDb.getEmail());

		logger.info(user);

		return modelAndView;
	}

	@RequestMapping(value = "/web/resetpassword", method = RequestMethod.POST)
	public ModelAndView webResetPasswordPost(@ModelAttribute("user") User user) {

		ModelAndView modelAndView = new ModelAndView("password-reset");

		logger.info(user);

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("message", "Password and confirm password does not match !");
		}

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		userServices.updateUser(user);

		modelAndView.addObject("status", ProjectConstant.STATUS_SUCCESS);
		modelAndView.addObject("message", "Password reset successfully !");

		return modelAndView;
	}

	@RequestMapping(value = "/web/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("superAdmin") User superAdmin,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials!");
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