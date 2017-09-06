package com.byb.bhojan.api.mess;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.Mess;

@RestController
@RequestMapping("/api/mess/mess-code")
public class MessCodeController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMessCode() {

		Mess mess = getLoggedInMessByAppKey();

		return sendSuccessResponse(mess.getMessId());
	}
}
