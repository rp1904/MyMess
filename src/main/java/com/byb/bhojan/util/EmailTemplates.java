package com.byb.bhojan.util;

import java.io.IOException;

import com.byb.bhojan.model.User;

public class EmailTemplates {

	public static String startDefualtMessage = "<html> " + "<head>"
			+ "<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" + "</head>" + "<body>"
			+ "<div>" + "<div style='width:639px;margin:0 auto;padding:0px'>"
			+ "<div style='margin:0px;padding:0px;float:left;width:639px;background-color:#f9a92f;'>"
			+ "<div style='padding:0px;float:left;width:15px;min-height:63px'> </div>"
			+ "<div style='margin: 0px; padding: 0 0 0 5px; float: left; width: 609px; min-height: 63px; font-family: arial;color:#FFFFFF'>"
			+ "<h1 style='text-align: center;'>" + ProjectConstant.PROJECT_NAME + "</h1></div>"
			+ "<div style='padding:0px;margin:0px;float:left;width:15px;min-height:63px'>" + "</div>" + "</div>"
			+ "<div style='padding:20px 19px 20px;border-left:1px solid #ccc;border-right:1px solid #ccc;clear:both;'>"
			+ "<div style='border-bottom:2px dotted #c8c8c8;padding:0px 19px 20px;;font-family:arial;font-size:14px;'>";

	public static String endDefualtMessageTemplete = "</div><div style='border-bottom:2px dotted #c8c8c8;padding:20px 19px 20px;font-family:arial;font-size:14px;'>"
			+ "<p style='color:#141414;font-family:arial;font-size:14px;padding-top:0px'> "
			+ "If you need any help or have feedback about " + ProjectConstant.PROJECT_NAME + ", please email us at</p>"
			+ "<a style='color:#0084ff;text-decoration:none;font-size:16px;font-family:arial"
			+ "href='#13e21ab5a6f40253_13d34c9c6b6e685a_'>" + ProjectConstant.PROJECT_SUPPORT_EMAIL + "</a>" + "<p>"
			+ "The " + ProjectConstant.PROJECT_NAME + " Team.</p>" + "</div>" + "</div>"
			+ "<div style='margin:0px;padding:0px;float:left;width:639px;background-color:#f9a92f;'>"
			+ "<div style='padding:0px;float:left;width:15px;min-height:63px'> </div>"
			+ "<div style='margin:0px;padding:0px;float:left;width:609px;min-height:63px;'>"
			+ "<p style='color:#fff;text-decoration:none;font-size:14px;font-family:arial;text-align:center'>"
			+ "You are receiving this email because you are registered for a " + ProjectConstant.PROJECT_NAME
			+ " account.<br>" + "&copy; 2017 Copyright " + ProjectConstant.PROJECT_NAME + ". All rights reserved.</p>"
			+ "</div>" + "<div style='padding:0px;margin:0px;float:left;width:15px;min-height:63px'>" + "</div>"
			+ "</div>" + "</div>" + "</div>" + "</body>" + "</html>";

	public static String createLoginCredentialsMessage(String fullUserName, String username, String password) {
		// @formatter:off
		String message = startDefualtMessage + " <h1>Dear " + fullUserName + ",</h1>"
				+ " <p>Thank you for using <strong>" + ProjectConstant.PROJECT_NAME + "</strong>.<br/><br/> "
				+ " Your account has been created.<br><br>" + "Your login credentials are:<br><br>" + "Username : "
				+ username + "<br>Password : " + password + " </p>" + endDefualtMessageTemplete;
		// @formatter:on
		return message;
	}

	public static String createFacebookLoginMessageTemplate() {
		// @formatter:off
		String message = startDefualtMessage + " <h1>Dear User" + ",</h1>" + " <p>Thank you for using <strong>"
				+ ProjectConstant.PROJECT_NAME + "</strong>.<br/><br/> " + " Now you can login with "
				+ ProjectConstant.PROJECT_NAME + " using your facebook account.<br><br>" + " </p>"
				+ endDefualtMessageTemplete;
		// @formatter:on
		return message;
	}

	public static String getDefaultForgotPasswordMessage(String fullUserName, String otp) throws IOException {
		// String basePath = getWebAppProperty("server_path") +
		// request.getContextPath();
		// @formatter:off
		String message = startDefualtMessage + " <h1>Dear " + fullUserName + ",</h1>"
				+ " <p>You told us you forgot your password.<br><br>" + "Your OTP is : " + otp + "</p>"
				+ endDefualtMessageTemplete;
		// @formatter:on
		return message;
	}

	public static String createSendReferralMessage(String userFullName, String messageBody) {
		// @formatter:off
		String message = startDefualtMessage
				// + " <h1>Dear User"
				// + "</h1>"
				// + " <p>You have been invited by
				// <strong>"+userFullName+"</strong> to
				// <strong>"+ProjectConstant.PROJECT_NAME+"</strong>
				// app.<br/><br/> "
				+ " <p>" + messageBody + " </p>" + endDefualtMessageTemplete;
		// @formatter:on
		return message;
	}

	public static String getPasswordMailTemplate(String tempPassword) {
		String PASSWORD_MAIL_TEMPLETE = "";
		// @formatter:off
		PASSWORD_MAIL_TEMPLETE = "<!DOCTYPE html>" + "<html lang='en-US'>" + "<head>" + "<meta charset='utf-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" + "<title>"
				+ ProjectConstant.PROJECT_NAME + "</title>" + "</head>"
				+ "<body style=' color: #000; font-size: 17px; font-family: arial;'>"
				+ "<div style='max-width: 600px; margin: 0 auto; border:1px solid #ebebeb; background-color: #f9f9f9;'>"
				+ "<table  style='width: 100%;' cellpadding='0' cellspacing='0'>" + "<tr><td>"
				+ "<table  style='width: 100%;' cellpadding='0' cellspacing='0'>" + " <tr>"
				+ "<td style='text-align: left;padding: 25px 50px; border-bottom: 1px solid #ebebeb; background-color: #fff;'>"
				+ "<img src='" + ProjectConstant.BASE_URL
				+ "/resources/assets/images/' style='max-width: 195px; width: 100%;'/>" + "</td>" + "</tr>" + "</table>"
				+ "</td></tr>" + "<tr>" + "<td style='padding: 33px 50px;'>" + "<table>" + "<tr>"
				+ "<td style='padding: 10px 0;font-size: 18px;color: #434850;font-weight: bold;line-height: 28px;'>"
				+ "You told us you forgot your password. Your temporary password is :" + tempPassword + "</td>"
				+ "</tr>"

				+ "</table>" + "</td>" + "</tr>" + "</table>" + "</div>" + "</body>" + "</html>";

		return PASSWORD_MAIL_TEMPLETE;
	}

	public static String getWelcomeMailTemplate(String password) {
		String WELCOME_MAIL_TEMPLETE = "";
		// @formatter:off
		WELCOME_MAIL_TEMPLETE = "<!DOCTYPE html>" + "<html lang='en-US'>" + "<head>" + "<meta charset='utf-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" + "<title>"
				+ ProjectConstant.PROJECT_NAME + "</title>" + "</head>"
				+ "<body style=' color: #000; font-size: 17px; font-family: arial;'>"
				+ "<div style='max-width: 600px; margin: 0 auto; border:1px solid #ebebeb; padding: 20px 35px'>"
				+ "<table  style='width: 100%;' cellpadding='0' cellspacing='0'>" + "<tr>"
				+ "<td style='font-size: 20px;color: #434850;font-weight: bold;line-height: 50px;'>" + "Welcome to "
				+ ProjectConstant.PROJECT_NAME + ", Your Account is Active<br/>"
				+ "<span style='color: #2ab27b;'>Welcome to " + ProjectConstant.PROJECT_NAME + "!</span>" + "</td>"
				+ "</tr>" + "<tr>"
				+ "<td style='font-size: 15px;color: #434850;vertical-align: top;line-height: 28px;padding-top: 20px;'>"
				+ "Your <strong>account is now active</strong> and ready to use!" + "</td>" + "</tr>";

		if (password != "") {
			WELCOME_MAIL_TEMPLETE += "<tr>"
					+ "<td style='font-size: 15px;color: #434850;vertical-align: top;line-height: 28px;padding-top: 20px;'>"
					+ "Your Password is <strong>" + password + "</strong>" + "</td>" + "</tr>";
		}

		WELCOME_MAIL_TEMPLETE += "<tr>"
				+ "<td style='font-size: 15px;color: #434850;vertical-align: top;line-height: 28px;padding-top: 20px;'>"
				+ "Remember that now you can invite other people involved in your transactions (i.e. Loan Officers, Real Estate Agents, Builders, other borrowers, etc) so everyone is in the loop!"
				+ "</td>" + " </tr>" + "<tr>"
				+ "<td style='font-size: 15px;color: #434850;vertical-align: top;line-height: 28px;padding-top: 20px;'>"
				+ "<span style='font-size: 11px; color: #aaaaaa;'>Text if activated from a Browser (which would require a download from the customer).</span><br/>"
				+ "Take a moment to download our App and start your first transaction." + "</td>" + "</tr>" + "<tr>"
				+ "<td style='padding: 20px 0; text-align: center;'>"
				+ "<a href='' style='background-color: #4a86e8;color: #fff;width: 173px;text-align: center;padding: 18px 0;"
				+ "text-decoration: none;font-size: 20px;border-radius: 0;display: inline-block;font-weight: 100;'>"
				+ "App Store" + "</a> "
				+ "<a href='' style='background-color: #4a86e8;color: #fff;width: 173px;text-align: center;padding: 18px 0;margin-left: 60px;"
				+ "text-decoration: none;font-size: 20px;border-radius: 0;display: inline-block;font-weight: 100;'>"
				+ "Google Play" + "</a>" + "</td>" + "</tr>" + "<tr>"
				+ "<td style='font-size: 13px;color: #aaaaaa;vertical-align: top;line-height: 10px;text-align: center;'>"
				+ "" + ProjectConstant.PROJECT_NAME
				+ " is always free for an unlimited period of time and unlimited number of people" + "</td>" + "</tr>"
				+ "<tr>"
				+ "<td style='font-size: 15px;color: #434850;vertical-align: top;line-height: 44px;padding-top: 10px;'>"
				+ "Good luck with " + ProjectConstant.PROJECT_NAME + "!<br/>" + "-- " + ProjectConstant.PROJECT_NAME
				+ "! Team" + "</td>" + "</tr>" + "</table>" + "</div> " + "</body>" + "</html>";

		// @formatter:on
		return WELCOME_MAIL_TEMPLETE;
	}

	public static String passwordResetText(User user, String link, String linkLifeTime) {
		// @formatter:off
		String message = startDefualtMessage + " <h3>Dear " + user.getUserProfile().getFullName() + ",</h3>"
				+ " <p>We received a request for password change.<br/><br/> " + " <a href='" + link
				+ "' target='_blank'>Click here</a> to set your new password.<br/><br/>"
				// + "<tbody><tr><td style='border-radius:5px' width='10%'
				// bgcolor='#f9a92f' align='center'> <a href='"
				// + link + "'> Reset My Password </a> </td></tr></tbody>"
				+ "The link will be active for " + linkLifeTime + " hour.<br><br>" + "</p>" + endDefualtMessageTemplete;
		// @formatter:on
		return message;
	}

}
