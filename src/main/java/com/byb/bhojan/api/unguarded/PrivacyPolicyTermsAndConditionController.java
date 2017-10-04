package com.byb.bhojan.api.unguarded;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/unguarded")
public class PrivacyPolicyTermsAndConditionController {

	@RequestMapping(value = "/privacy-policy", method = RequestMethod.GET)
	public ModelAndView getPrivacyPolicyPage() {
		return new ModelAndView("privacy-policy");
	}

	@RequestMapping(value = "/terms-and-conditions", method = RequestMethod.GET)
	public ModelAndView getTermsAndConditionsPage() {
		return new ModelAndView("terms-and-conditions");
	}
}
