package com.byb.bhojan.services;

import com.byb.bhojan.model.Otp;

public interface OtpServices {

	public void saveOtp(Otp otp);

	public void updateOtp(Otp otp);

	public Otp getOtpActiveOtpByEmailId(String email);

}
