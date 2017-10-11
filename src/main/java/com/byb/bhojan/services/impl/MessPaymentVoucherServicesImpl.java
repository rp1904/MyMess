package com.byb.bhojan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.MessPaymentVoucherDao;
import com.byb.bhojan.model.MessPaymentVoucher;
import com.byb.bhojan.services.MessPaymentVoucherServices;

@Service
@Transactional
public class MessPaymentVoucherServicesImpl implements MessPaymentVoucherServices {

	@Autowired
	private MessPaymentVoucherDao messPaymentVoucherDao;

	@Override
	public void saveVoucher(MessPaymentVoucher messPaymentVoucher) {
		// TODO Auto-generated method stub
		messPaymentVoucherDao.saveVoucher(messPaymentVoucher);
	}

	@Override
	public MessPaymentVoucher updateVoucher(MessPaymentVoucher messPaymentVoucher) {
		// TODO Auto-generated method stub
		messPaymentVoucherDao.updateVoucher(messPaymentVoucher);
		return messPaymentVoucher;
	}

	@Override
	public MessPaymentVoucher getVoucherById(String voucherId) {
		// TODO Auto-generated method stub
		return messPaymentVoucherDao.getVoucherById(voucherId);
	}

	@Override
	public List<MessPaymentVoucher> getAllVouchers() {
		// TODO Auto-generated method stub
		return messPaymentVoucherDao.getAllVouchers();
	}

	@Override
	public void deleteVoucher(MessPaymentVoucher voucher) {
		// TODO Auto-generated method stub
		messPaymentVoucherDao.deleteVoucher(voucher);
	}

	@Override
	public double getTotalPaymentDone() {
		// TODO Auto-generated method stub
		return messPaymentVoucherDao.getTotalPaymentDone();
	}

}
