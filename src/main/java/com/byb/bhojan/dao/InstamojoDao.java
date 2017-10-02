package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.InstamojoPaymentLog;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;

public interface InstamojoDao {

	public void savePaymentRequestResponse(InstamojoPaymentReqResponse instamojoPaymentReqResponse);

	public void saveInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog);

	public void updateInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog);

	public void updateInstamojoPaymentRequestStatusById(InstamojoPaymentLog instamojoPaymentLog);

	public InstamojoPaymentRequest getPaymentRequest(String id);

	public List<InstamojoPaymentLog> getPaymentHistoryForAllMesses();

	public List<InstamojoPaymentLog> getPaymentHistoryByMess(Mess mess);

	public InstamojoPaymentLog getInstamojoPaymentLogByPaymentReqId(String paymentReqId);

}
