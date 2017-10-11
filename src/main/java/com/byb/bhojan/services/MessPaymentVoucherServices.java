package com.byb.bhojan.services;

import java.util.List;

import com.byb.bhojan.model.MessPaymentVoucher;

public interface MessPaymentVoucherServices {

	public void saveVoucher(MessPaymentVoucher messPaymentVoucher);

	public MessPaymentVoucher updateVoucher(MessPaymentVoucher messPaymentVoucher);

	public MessPaymentVoucher getVoucherById(String voucherId);

	public List<MessPaymentVoucher> getAllVouchers();

	public void deleteVoucher(MessPaymentVoucher voucher);

	public long getTotalVoucherCount();

}
