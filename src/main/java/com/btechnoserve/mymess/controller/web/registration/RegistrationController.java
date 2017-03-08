package com.btechnoserve.mymess.controller.web.registration;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.btechnoserve.mymess.model.User;

@Controller
public class RegistrationController {

	Logger logger = Logger.getLogger(RegistrationController.class);

	@RequestMapping(value = "/member-registration", method = RequestMethod.GET)
	public ModelAndView getMemberRegPage(@ModelAttribute("member") User member) {
		ModelAndView modelAndView = new ModelAndView("member-registration");
		return modelAndView;
	}

	@RequestMapping(value = "/member-registration", method = RequestMethod.POST)
	public ModelAndView doMemberRegistration(@ModelAttribute("member") User member) {
		logger.info(member);
		ModelAndView modelAndView = new ModelAndView("redirect:login");
		return modelAndView;
	}
}
