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
import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.InstamojoPaymentLogs;
import com.byb.bhojan.model.InstamojoPaymentReqResponse;
import com.byb.bhojan.model.InstamojoPaymentRequest;
import com.byb.bhojan.model.MessPaymentVoucher;
import com.byb.bhojan.services.InstamojoServices;

@Service
@Transactional
public class InstamojoServicesImpl implements InstamojoServices {

	Logger logger = Logger.getLogger(InstamojoServicesImpl.class);

	@Autowired
	private InstamojoDao instamojoDao;

	@Value("${instamojo_base_path}")
	private String instamojo_base_path;

	@Value("${instamojo_x_api_key}")
	private String instamojo_x_api_key;

	@Value("${instamojo_x_auth_token}")
	private String instamojo_x_auth_token;

	//	private Double PAYMENT_ORDER_AMOUNT = 100D;

	@Value("${instamojo_redirect_url}")
	private String PAYMENT_ORDER_REDIRECT_URL;

	@Value("${instamojo_webhook}")
	private String PAYMENT_ORDER_WEBHOOK_URL;

	private String PAYMENT_ORDER_PURPOSE = "Monthely Service Charge";

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

				logger.info(paymentReqResponse);

				return paymentReqResponse;
			}

			return null;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}

	}

	@Override
	public void saveInstamojoPaymentLogs(Map<String, String> instamojoPaymentLogMap, String messIdPk) {
		// TODO Auto-generated method stub

		InstamojoPaymentLogs instamojoPaymentLogs = new InstamojoPaymentLogs();

		instamojoPaymentLogs.setMessId(messIdPk);
		instamojoPaymentLogs.setPayment_id(instamojoPaymentLogMap.get("payment_id"));
		instamojoPaymentLogs.setAmount(instamojoPaymentLogMap.get("amount"));
		instamojoPaymentLogs.setBuyer(instamojoPaymentLogMap.get("buyer"));
		instamojoPaymentLogs.setBuyer_name(instamojoPaymentLogMap.get("buyer_name"));
		instamojoPaymentLogs.setBuyer_phone(instamojoPaymentLogMap.get("buyer_phone"));
		instamojoPaymentLogs.setCurrency(instamojoPaymentLogMap.get("currency"));
		instamojoPaymentLogs.setFees(instamojoPaymentLogMap.get("fees"));
		instamojoPaymentLogs.setLongurl(instamojoPaymentLogMap.get("longurl"));
		instamojoPaymentLogs.setMac(instamojoPaymentLogMap.get("mac"));
		instamojoPaymentLogs.setPayment_request_id(instamojoPaymentLogMap.get("payment_request_id"));
		instamojoPaymentLogs.setPurpose(instamojoPaymentLogMap.get("purpose"));
		instamojoPaymentLogs.setShorturl(instamojoPaymentLogMap.get("shorturl"));
		instamojoPaymentLogs.setStatus(instamojoPaymentLogMap.get("status"));

		instamojoPaymentLogs.setCreatedUpdated(new CreatedUpdated(messIdPk));

		logger.info(instamojoPaymentLogs);

		instamojoDao.saveInstamojoPaymentLogs(instamojoPaymentLogs);

		instamojoDao.updateInstamojoPaymentRequestStatusById(instamojoPaymentLogs);
	}

}
