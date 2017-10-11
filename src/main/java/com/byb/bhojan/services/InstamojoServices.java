package com.byb.bhojan.services;

import java.util.List;
import java.util.Map;

import com.byb.bhojan.model.InstamojoPaymentLog;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessPaymentVoucher;

public interface InstamojoServices {

	public InstamojoPaymentReqResponse placePaymentRequest(InstamojoPaymentRequest instamojoPaymentRequest, String messId, MessPaymentVoucher voucher);

	public void saveInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog);

	public void updateInstamojoPaymentLog(Map<String, String> instamojoPaymentLogMap);

	public InstamojoPaymentLog getInstamojoPaymentLogByPaymentReqId(String paymentReqId);

	public List<InstamojoPaymentLog> getPaymentHistoryForAllMesses();

	public List<InstamojoPaymentLog> getPaymentHistoryByMess(Mess mess);

	public double getTotalPaymentDone();

}
