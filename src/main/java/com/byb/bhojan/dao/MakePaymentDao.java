package com.byb.bhojan.dao;

import com.byb.bhojan.model.MessPayment;

public interface MakePaymentDao {

	public void makePaymentEntry(MessPayment messPayment);

	public void updateMessPaymentEntry(MessPayment messPayment);
}
