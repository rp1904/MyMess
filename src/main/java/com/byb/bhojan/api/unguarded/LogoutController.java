package com.byb.bhojan.api.unguarded;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;

@RestController
@RequestMapping("/unguarded/logout")
public class LogoutController extends BaseController {

	Logger logger = Logger.getLogger(LogoutController.class);

	@RequestMapping(value = "/mess", method = RequestMethod.GET)
	public ResponseEntity<?> doMessLogout() {

		distroyMemberSession();

		return sendSuccessResponse("Sesssion distroyed successfully !");
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ResponseEntity<?> doMemberLogout() {

		distroyMemberSession();

		return sendSuccessResponse("Sesssion distroyed successfully !");
	}
}
