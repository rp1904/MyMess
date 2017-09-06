package com.byb.bhojan.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.User;
import com.byb.bhojan.services.UserServices;

@Controller
@RequestMapping("/member")
public class MemberController {

	Logger logger = Logger.getLogger(MemberController.class);

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/verify-and-join-mess", method = RequestMethod.POST)
	public ModelAndView verifyJoinMess(Principal principal, @RequestParam("messCode") String messCode) {

		String emailIdRoMobNo = principal.getName(); // get logged in username
		User member = userServices.getUserByEmailOrMobileNo(emailIdRoMobNo);
		ModelAndView modelAndView = new ModelAndView("member/select-mess");
		String status;
		String msg;
		// get mess by mess code
		if (messCode.equals("100")) {
			status = "danger";
			msg = "Invalid Mess Code !";
		} else {
			// send new member request to mess
			status = "success";
			msg = "Your Membership request successfully sent to 'messname' !";
		}
		logger.info("Mess Code : " + messCode + " Member : " + member);
		modelAndView.addObject("status", status);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}

}
