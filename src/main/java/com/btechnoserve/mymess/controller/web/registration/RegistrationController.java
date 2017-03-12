package com.btechnoserve.mymess.controller.web.registration;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.btechnoserve.mymess.model.User;
import com.btechnoserve.mymess.services.UserServices;
import com.btechnoserve.mymess.util.ProjectConstant;
import com.btechnoserve.mymess.validators.UserValidator;

@Controller
public class RegistrationController {

	Logger logger = Logger.getLogger(RegistrationController.class);

	@Autowired
	private UserServices userServices;

	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "/member-registration", method = RequestMethod.GET)
	public ModelAndView getMemberRegPage(@ModelAttribute("member") User member) {
		ModelAndView modelAndView = new ModelAndView("member-registration");
		return modelAndView;
	}

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public ModelAndView doMemberRegistration(@Validated @ModelAttribute("member") User member, BindingResult result,
			Model model) {
		logger.info(member);
		if (result.hasErrors()) {
			return new ModelAndView("member-registration");
		} else {
			member.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
			member.getUserInfo().setRegistrationDate(new Date());
			userServices.saveUser(member);
			ModelAndView modelAndView = new ModelAndView("redirect:login");
			return modelAndView;
		}
	}
}
