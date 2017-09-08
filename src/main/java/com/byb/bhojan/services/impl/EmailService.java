package com.byb.bhojan.services.impl;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service("emailService")
public class EmailService extends Thread {

	Logger logger = Logger.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("${isEmailsEnabled}")
	private String isEmailsEnabled;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendEmail(String to, String subject, String msgBody) {

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(msgBody, "text/html");
			helper.setTo(to);
			helper.setSubject(subject);

			if (this.isEmailsEnabled.equals("yes")) {
				mailSender.send(mimeMessage);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}