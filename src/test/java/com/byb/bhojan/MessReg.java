package com.byb.bhojan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLog;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessPaymentVoucher;
import com.byb.bhojan.services.InstamojoServices;
import com.byb.bhojan.services.MessPaymentVoucherServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.RandomStringGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/mvc-dispatcher-servlet.xml", "classpath:/spring-email.xml" })
@ComponentScan(basePackages = { "com.byb.bhojan" })
@PropertySource("classpath:application.properties ")
@WebAppConfiguration
public class MessReg {

	@Autowired
	public UserServices userServices;

	@Autowired
	public MessServices messServices;

	@Autowired
	private InstamojoServices instamojoServices;

	@Autowired
	private MessPaymentVoucherServices messPaymentVoucherServices;

	@Test
	public void test() {

		List<Mess> messes = messServices.getAllMessess();

		System.out.println("Total: " + messes.size());

		for (int i = 1; i < messes.size(); i++) {

			MessPaymentVoucher voucher = messPaymentVoucherServices.getVoucherById(getVoucherNo());

			//			InstamojoPaymentRequest instamojoPaymentRequest = new InstamojoPaymentRequest();
			//
			//			instamojoPaymentRequest.setBuyer_name(messes.get(i).getMessName());
			//			instamojoPaymentRequest.setEmail(messes.get(i).getMessOwner().getEmail());
			//			instamojoPaymentRequest.setPhone(messes.get(i).getMessOwner().getMobileNumber());
			//
			//			InstamojoPaymentReqResponse response = instamojoServices.placePaymentRequest(instamojoPaymentRequest, messes.get(i).getMessIdPk(), voucher);

			InstamojoPaymentLog instamojoPaymentLog = new InstamojoPaymentLog();
			instamojoPaymentLog.setMess(messes.get(i));
			//			instamojoPaymentLog.setPayment_request_id(response.getPayment_request().getId());
			instamojoPaymentLog.setVoucherName(voucher.getName());
			instamojoPaymentLog.setVoucherAmount(voucher.getAmount());
			instamojoPaymentLog.setVoucherDays(voucher.getDays());
			instamojoPaymentLog.setVoucherDiscount(voucher.getDiscount());
			instamojoPaymentLog.setCreatedUpdated(new CreatedUpdated(getDate(), messes.get(i).getMessIdPk()));
			instamojoPaymentLog.setPayment_id("MOJO" + RandomStringGenerator.getRandomString(16));
			instamojoPaymentLog.setAmount(voucher.getAmount() + "");
			instamojoPaymentLog.setBuyer(messes.get(i).getMessOwner().getEmail());
			instamojoPaymentLog.setBuyer_name(messes.get(i).getMessName());
			instamojoPaymentLog.setBuyer_phone(messes.get(i).getMessOwner().getMobileNumber());
			instamojoPaymentLog.setCurrency("INR");
			double fees = (voucher.getAmount() * 0.02) + 3.09;
			instamojoPaymentLog.setFees(fees + "");
			instamojoPaymentLog.setLongurl("https://instamojo.com/@iamroshanpatil05/d2a5206cf2264938a2716902ac47d7e7");
			instamojoPaymentLog.setStatus("Credit");

			instamojoServices.saveInstamojoPaymentLog(instamojoPaymentLog);

			System.out.println(instamojoPaymentLog);
			System.out.println(i);
		}

	}

	public Date getDate() {
		try {
			String inputString = "01-10-2017";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = dateFormat.parse(inputString);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Random r = new Random();
			int Low = 1;
			int High = 14;
			c.add(Calendar.DATE, r.nextInt(High - Low) + Low);
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getVoucherNo() {
		Random r = new Random();
		int min = 2;
		int max = 4;

		String str = String.valueOf(r.nextInt((max - min) + 1) + min);
		return str;
	}

}
