package com.byb.bhojan.api.mess;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLog;
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<JSONArray> getMessPaymentHistory() {

		Mess mess = getLoggedInMessByAppKey();

		JSONArray result = new JSONArray();
		List<InstamojoPaymentLog> instamojoPaymentLogs = instamojoServices.getPaymentHistoryByMess(mess);

		for (InstamojoPaymentLog paymentLog : instamojoPaymentLogs) {

			JSONObject innerObj = new JSONObject();
			innerObj.put("transactionId", paymentLog.getPayment_id());
			innerObj.put("name", paymentLog.getVoucherName());
			innerObj.put("amount", paymentLog.getVoucherAmount());
			innerObj.put("days", paymentLog.getVoucherDays());
			innerObj.put("discount", paymentLog.getVoucherDiscount());
			innerObj.put("purchasedDateTime", paymentLog.getCreatedUpdated().getUpdatedAt());

			result.add(innerObj);
		}

		return new ResponseEntity<JSONArray>(result, HttpStatus.OK);
	}

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

			InstamojoPaymentLog instamojoPaymentLog = new InstamojoPaymentLog();
			instamojoPaymentLog.setMess(mess);
			instamojoPaymentLog.setVoucherName(voucher.getName());
			instamojoPaymentLog.setVoucherAmount(voucher.getAmount());
			instamojoPaymentLog.setVoucherDays(voucher.getDays());
			instamojoPaymentLog.setVoucherDiscount(voucher.getDiscount());
			instamojoPaymentLog.setCreatedUpdated(new CreatedUpdated(mess.getMessIdPk()));
			instamojoServices.saveInstamojoPaymentLog(instamojoPaymentLog);
			logger.info(instamojoPaymentLog);
			return new ResponseEntity<InstamojoPaymentReqResponse>(response, HttpStatus.OK);
		}

		return sendErrorResponse("Payment Request Failed !");
	}

	@RequestMapping(value = "/vouchers", method = RequestMethod.GET)
	public ResponseEntity<List<MessPaymentVoucher>> getAllMessPaymentVouchers() {

		List<MessPaymentVoucher> vouchers = messPaymentVoucherServices.getAllVouchers();
		vouchers.remove(0);

		return new ResponseEntity<List<MessPaymentVoucher>>(vouchers, HttpStatus.OK);
	}

}
