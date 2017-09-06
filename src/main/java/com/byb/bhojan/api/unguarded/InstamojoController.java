package com.byb.bhojan.api.unguarded;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.InstamojoServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;

@Controller
@RequestMapping("/unguarded/instamojo")
public class InstamojoController {

	Logger logger = Logger.getLogger(InstamojoController.class);

	@Autowired
	private InstamojoServices instamojoServices;

	@Autowired
	private MessServices messServices;

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void instamojoWebhook(@RequestParam Map<String, String> instamojoPaymentLogMap) {

		logger.info("webhook resp: " + instamojoPaymentLogMap);

		User messOwner = userServices.getUserByEmail(instamojoPaymentLogMap.get("buyer"));

		logger.info(messOwner);

		Mess mess = messServices.getMessByOwnerIdPk(messOwner.getUserIdPk());

		logger.info(mess);

		instamojoServices.saveInstamojoPaymentLogs(instamojoPaymentLogMap, mess.getMessIdPk());
	}

	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView instamojoRedirect(@RequestParam Map<String, String> body) {

		logger.info("redirect resp: " + body);

		ModelAndView modelAndView = new ModelAndView("instamojo/redirect");

		modelAndView.addObject("paymentId", body.get("payment_id"));

		return modelAndView;
	}
}
