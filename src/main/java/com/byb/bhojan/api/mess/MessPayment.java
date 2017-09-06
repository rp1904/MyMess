package com.byb.bhojan.api.mess;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.services.InstamojoServices;

@RestController
@RequestMapping("/api/mess/payment")
public class MessPayment extends BaseController {

	Logger logger = Logger.getLogger(MessPayment.class);

	@Autowired
	private InstamojoServices instamojoServices;

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<?> makePaymentRequest() {

		Mess mess = getLoggedInMessByAppKey();

		InstamojoPaymentRequest instamojoPaymentRequest = new InstamojoPaymentRequest();

		instamojoPaymentRequest.setBuyer_name(mess.getMessName());
		instamojoPaymentRequest.setEmail(mess.getMessOwner().getEmail());
		instamojoPaymentRequest.setPhone(mess.getMessOwner().getMobileNumber());

		InstamojoPaymentReqResponse response = instamojoServices.placePaymentRequest(instamojoPaymentRequest,
				mess.getMessIdPk());

		if (response != null) {
			return new ResponseEntity<InstamojoPaymentReqResponse>(response, HttpStatus.OK);
		}

		return sendErrorResponse("Payment Request Failed !");
	}

}
