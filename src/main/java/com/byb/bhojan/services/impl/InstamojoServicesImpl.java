package com.byb.bhojan.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byb.bhojan.dao.InstamojoDao;
import com.byb.bhojan.model.AdminSetting;
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLog;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.MessPaymentVoucher;
import com.byb.bhojan.services.AdminSettingServices;
import com.byb.bhojan.services.InstamojoServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.util.ProjectConstant;

@Service
@Transactional
public class InstamojoServicesImpl implements InstamojoServices {

	Logger logger = Logger.getLogger(InstamojoServicesImpl.class);

	@Autowired
	public MessServices messServices;

	@Autowired
	private AdminSettingServices adminSettingServices;

	@Autowired
	private AndroidPush notification;

	@Autowired
	private InstamojoDao instamojoDao;

	@Value("${instamojo_base_path}")
	private String instamojo_base_path;

	@Value("${instamojo_x_api_key}")
	private String instamojo_x_api_key;

	@Value("${instamojo_x_auth_token}")
	private String instamojo_x_auth_token;

	@Value("${instamojo_redirect_url}")
	private String PAYMENT_ORDER_REDIRECT_URL;

	@Value("${instamojo_webhook}")
	private String PAYMENT_ORDER_WEBHOOK_URL;

	private String PAYMENT_ORDER_PURPOSE = "Service Charge";

	@Override
	public InstamojoPaymentReqResponse placePaymentRequest(InstamojoPaymentRequest instamojoPaymentRequest, String messId, MessPaymentVoucher voucher) {

		instamojoPaymentRequest.setAmount(voucher.getAmount());
		instamojoPaymentRequest.setRedirect_url(PAYMENT_ORDER_REDIRECT_URL);
		instamojoPaymentRequest.setWebhook(PAYMENT_ORDER_WEBHOOK_URL);
		instamojoPaymentRequest.setPurpose(PAYMENT_ORDER_PURPOSE);

		String url = instamojo_base_path + "/payment-requests/";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("X-Api-Key", instamojo_x_api_key);
		post.setHeader("X-Auth-Token", instamojo_x_auth_token);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		urlParameters.add(new BasicNameValuePair("buyer_name", instamojoPaymentRequest.getBuyer_name()));
		urlParameters.add(new BasicNameValuePair("email", instamojoPaymentRequest.getEmail()));
		urlParameters.add(new BasicNameValuePair("phone", instamojoPaymentRequest.getPhone()));
		urlParameters.add(new BasicNameValuePair("amount", String.valueOf(instamojoPaymentRequest.getAmount())));
		urlParameters.add(new BasicNameValuePair("redirect_url", instamojoPaymentRequest.getRedirect_url()));
		urlParameters.add(new BasicNameValuePair("webhook", instamojoPaymentRequest.getWebhook()));
		urlParameters.add(new BasicNameValuePair("purpose", instamojoPaymentRequest.getPurpose()));

		try {

			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);

			logger.info("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			logger.info(result.toString());

			if (response.getStatusLine().getStatusCode() == 201) {

				InstamojoPaymentReqResponse paymentReqResponse = new ObjectMapper().readValue(result.toString(), InstamojoPaymentReqResponse.class);

				paymentReqResponse.setCreatedUpdated(new CreatedUpdated(messId));
				paymentReqResponse.setMessId(messId);

				paymentReqResponse.getPayment_request().setCreatedUpdated(new CreatedUpdated(messId));

				instamojoDao.savePaymentRequestResponse(paymentReqResponse);

				//				logger.info(paymentReqResponse);

				return paymentReqResponse;
			}

			return null;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}

	}

	@Override
	public void updateInstamojoPaymentLog(Map<String, String> instamojoPaymentLogMap) {
		// TODO Auto-generated method stub

		InstamojoPaymentLog instamojoPaymentLog = getInstamojoPaymentLogByPaymentReqId(instamojoPaymentLogMap.get("payment_request_id"));

		instamojoPaymentLog.setPayment_id(instamojoPaymentLogMap.get("payment_id"));
		instamojoPaymentLog.setAmount(instamojoPaymentLogMap.get("amount"));
		instamojoPaymentLog.setBuyer(instamojoPaymentLogMap.get("buyer"));
		instamojoPaymentLog.setBuyer_name(instamojoPaymentLogMap.get("buyer_name"));
		instamojoPaymentLog.setBuyer_phone(instamojoPaymentLogMap.get("buyer_phone"));
		instamojoPaymentLog.setCurrency(instamojoPaymentLogMap.get("currency"));
		instamojoPaymentLog.setFees(instamojoPaymentLogMap.get("fees"));
		instamojoPaymentLog.setLongurl(instamojoPaymentLogMap.get("longurl"));
		instamojoPaymentLog.setMac(instamojoPaymentLogMap.get("mac"));
		instamojoPaymentLog.setPayment_request_id(instamojoPaymentLogMap.get("payment_request_id"));
		instamojoPaymentLog.setPurpose(instamojoPaymentLogMap.get("purpose"));
		instamojoPaymentLog.setShorturl(instamojoPaymentLogMap.get("shorturl"));
		instamojoPaymentLog.setStatus(instamojoPaymentLogMap.get("status"));

		instamojoPaymentLog.setCreatedUpdated(new CreatedUpdated(instamojoPaymentLog.getMess().getMessIdPk()));

		instamojoDao.updateInstamojoPaymentLog(instamojoPaymentLog);

		instamojoDao.updateInstamojoPaymentRequestStatusById(instamojoPaymentLog);

		AdminSetting adminSetting = adminSettingServices.getAdminSettings();

		Mess mess = instamojoPaymentLog.getMess();
		mess.setDaysRemaining(mess.getDaysRemaining() + adminSetting.getFreeTrialDays());

		messServices.updateMess(mess);

		String title = ProjectConstant.PROJECT_NAME + " Payment Success!";
		// @formatter:off
		String msg = "Congratulations ! You have just purchased a voucher of Rs. " 
			   + instamojoPaymentLog.getAmount() 
			   + ". Enjoy our servise for next " + mess.getDaysRemaining()
			   + " days.";
		// @formatter:on
		notification.sendPushNotification(title, msg, mess.getMessIdPk());
	}

	@Override
	public List<InstamojoPaymentLog> getPaymentHistoryForAllMesses() {
		// TODO Auto-generated method stub
		return instamojoDao.getPaymentHistoryForAllMesses();
	}

	@Override
	public List<InstamojoPaymentLog> getPaymentHistoryByMess(Mess mess) {
		// TODO Auto-generated method stub
		return instamojoDao.getPaymentHistoryByMess(mess);
	}

	@Override
	public void saveInstamojoPaymentLog(InstamojoPaymentLog instamojoPaymentLog) {
		// TODO Auto-generated method stub
		instamojoDao.saveInstamojoPaymentLog(instamojoPaymentLog);
	}

	@Override
	public InstamojoPaymentLog getInstamojoPaymentLogByPaymentReqId(String paymentReqId) {
		// TODO Auto-generated method stub
		return instamojoDao.getInstamojoPaymentLogByPaymentReqId(paymentReqId);
	}

}
