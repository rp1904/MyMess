package com.byb.bhojan.dao;

import java.util.List;

import com.byb.bhojan.model.MessPaymentVoucher;

public interface MessPaymentVoucherDao {

	public void saveVoucher(MessPaymentVoucher messPaymentVoucher);
	
	public void updateVoucher(MessPaymentVoucher messPaymentVoucher);

	public MessPaymentVoucher getVoucherById(String voucherId);
	
	public List<MessPaymentVoucher> getAllVouchers();
}
