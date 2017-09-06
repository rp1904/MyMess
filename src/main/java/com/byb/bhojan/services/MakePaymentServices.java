package com.byb.bhojan.services;

import com.byb.bhojan.model.MessPayment;

public interface MakePaymentServices {

	public void makePaymentEntry(MessPayment messPayment);

	public void updateMessPaymentEntry(MessPayment messPayment);

	// public void updateAllMessPayments();
}
