package com.byb.bhojan.dao;

import com.byb.bhojan.model.InstamojoPaymentLogs;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;

public interface InstamojoDao {

	public void savePaymentRequestResponse(InstamojoPaymentReqResponse instamojoPaymentReqResponse);

	public void saveInstamojoPaymentLogs(InstamojoPaymentLogs instamojoPaymentLogs);

	public void updateInstamojoPaymentRequestStatusById(InstamojoPaymentLogs instamojoPaymentLogs);

	public InstamojoPaymentRequest getPaymentRequest(String id);

}
