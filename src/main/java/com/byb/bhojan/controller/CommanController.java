package com.byb.bhojan.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

		String decryptedToken = EncryptionUtils.decrypt(token);

		if (decryptedToken == null) {

			logger.info("Invalid Token !");

			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("type", "invalid_token");
			modelAndView.addObject("message", "Invalid Token !");
			return modelAndView;
		}

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

		if (user.getPassword().length() < 5) {
			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("message", "Password length should be of minimum 5 characters !");
			return modelAndView;
		}

		if (user.getPassword().contains(" ")) {
			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("message", "Password can not contain white space !");
			return modelAndView;
		}

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			modelAndView.addObject("status", ProjectConstant.STATUS_DANGER);
			modelAndView.addObject("message", "Password and confirm password does not match !");
			return modelAndView;
		}

		PasswordEncoder encoder = new BCryptPasswordEncoder();

		User userFromDb = userServices.getUserByUserIdPk(user.getUserIdPk());

		userFromDb.setPassword(encoder.encode(user.getPassword()));

		userServices.updateUser(userFromDb);

		modelAndView.addObject("status", ProjectConstant.STATUS_SUCCESS);
		modelAndView.addObject("message", "Password reset successfully !");

		return modelAndView;
	}

	@RequestMapping(value = "/web/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("superAdmin") User superAdmin, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {

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
	
	@RequestMapping(value = "/web/privacy-policy", method = RequestMethod.GET)
	public ModelAndView getPrivacyPolicyPage() {

		ModelAndView model = new ModelAndView("privacy-policy");

		return model;
	}
	
	@RequestMapping(value = "/web/terms-and-conditions", method = RequestMethod.GET)
	public ModelAndView getTermsAndContitionsPage() {

		ModelAndView model = new ModelAndView("terms-and-conditions");

		return model;
	}

}