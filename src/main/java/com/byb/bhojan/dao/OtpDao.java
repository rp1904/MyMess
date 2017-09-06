package com.byb.bhojan.dao;

import org.springframework.stereotype.Repository;

import com.byb.bhojan.model.Otp;

@Repository
public interface OtpDao {

	public void saveOtp(Otp otp);

	public void updateOtp(Otp otp);

	public Otp getOtpActiveOtpByEmailId(String email);

}
