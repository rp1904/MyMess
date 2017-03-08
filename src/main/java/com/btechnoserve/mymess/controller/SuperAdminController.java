package com.btechnoserve.mymess.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

	Logger logger = Logger.getLogger(SuperAdminController.class);

	@RequestMapping("/list")
	public ModelAndView loadLost() {
		logger.info("------------------- List ");
		return new ModelAndView("super-admin/list");
	}

}
