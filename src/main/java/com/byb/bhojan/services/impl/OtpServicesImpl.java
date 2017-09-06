package com.byb.bhojan.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.byb.bhojan.dao.OtpDao;
import com.byb.bhojan.model.Otp;
import com.byb.bhojan.services.OtpServices;

@Transactional
@Repository
public class OtpServicesImpl implements OtpServices {

	@Autowired
	private OtpDao otpDao;

	@Override
	public void saveOtp(Otp otp) {
		// TODO Auto-generated method stub
		otpDao.saveOtp(otp);
	}

	@Override
	public void updateOtp(Otp otp) {
		// TODO Auto-generated method stub
		otpDao.updateOtp(otp);
	}

	@Override
	public Otp getOtpActiveOtpByEmailId(String email) {
		// TODO Auto-generated method stub
		return otpDao.getOtpActiveOtpByEmailId(email);
	}

}
