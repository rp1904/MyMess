package com.byb.bhojan.services;

import java.util.Map;

import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.MessPaymentVoucher;

public interface InstamojoServices {

	public InstamojoPaymentReqResponse placePaymentRequest(InstamojoPaymentRequest instamojoPaymentRequest, String messId, MessPaymentVoucher voucher);

	public void saveInstamojoPaymentLogs(Map<String, String> body, String messIdPk);

}
