package com.byb.bhojan.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.MessServices;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

	Logger logger = Logger.getLogger(SuperAdminController.class);

	@Autowired
	private MessServices messServices;

	@RequestMapping("/list")
	public ModelAndView loadLost() {
		logger.info("------------------- List ");

		Mess mess = new Mess();
		mess.setCreatedUpdated(new CreatedUpdated("SELF"));
		mess.setMessId("test-mess-1");
		mess.setMessName("Test-Mess-1");
		mess.setMessTagLine("Test-Mess-1_tag line");
		Mess newMess = messServices.saveMess(mess);

		logger.info("New Mess :" + newMess);

		return new ModelAndView("super-admin/list");
	}

}
