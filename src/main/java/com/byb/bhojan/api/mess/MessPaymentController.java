package com.byb.bhojan.api.mess;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessPaymentVoucher;
import com.byb.bhojan.services.InstamojoServices;
import com.byb.bhojan.services.MessPaymentVoucherServices;

@RestController
@RequestMapping("/api/mess/payment")
public class MessPaymentController extends BaseController {

	Logger logger = Logger.getLogger(MessPaymentController.class);

	@Autowired
	private InstamojoServices instamojoServices;

	@Autowired
	private MessPaymentVoucherServices messPaymentVoucherServices;

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ResponseEntity<?> makePaymentRequest(@RequestParam(value = "voucherId", required = true) String voucherId) {

		Mess mess = getLoggedInMessByAppKey();

		MessPaymentVoucher voucher = messPaymentVoucherServices.getVoucherById(voucherId);
		if (voucher == null) {
			return sendErrorResponse("Voucher Not Found !");
		}

		InstamojoPaymentRequest instamojoPaymentRequest = new InstamojoPaymentRequest();

		instamojoPaymentRequest.setBuyer_name(mess.getMessName());
		instamojoPaymentRequest.setEmail(mess.getMessOwner().getEmail());
		instamojoPaymentRequest.setPhone(mess.getMessOwner().getMobileNumber());

		InstamojoPaymentReqResponse response = instamojoServices.placePaymentRequest(instamojoPaymentRequest, mess.getMessIdPk(), voucher);

		if (response != null) {
			return new ResponseEntity<InstamojoPaymentReqResponse>(response, HttpStatus.OK);
		}

		return sendErrorResponse("Payment Request Failed !");
	}

	@RequestMapping(value = "/vouchers", method = RequestMethod.GET)
	public ResponseEntity<List<MessPaymentVoucher>> getAllMessPaymentVouchers() {

		return new ResponseEntity<List<MessPaymentVoucher>>(messPaymentVoucherServices.getAllVouchers(), HttpStatus.OK);
	}

}
