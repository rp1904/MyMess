package com.byb.bhojan.util;

public class ProjectConstant {

	public static final String PROJECT_NAME = "BHOJAN";

	public static final String PROJECT_SUPPORT_EMAIL = "help.mybhojan@gmail.com";

	public static final String BASE_URL = "base_url";

	public static final String WELCOME_EMAIL_SUB = "Welcome to " + PROJECT_NAME;

	public static final String EMAIL_PASSWORD_RESET = "Welcome to " + PROJECT_NAME;

	public static final String SERVER_KEY = "AAAAMCyTYzA:APA91bEEGdkJ8HE7XN0UvcobqFhDDQ_wEMOPsZsfNSpZARnNjyar-wNF62Sky8HMzsr-8yRiv3T1XJ9BfVZ4FSwhqS7Au2_JZIbqRBuflaIjYu5VAFiEKx_EU8Wd8_jeuqjjnmM3qi1T";

	public static final String STRING_SEPERATOR = "RP_WORLD";

	// ---------------- USER ROLE -------------------

	public static final int USER_ROLE_ID_SUPERADMIN = 1;
	public static final int USER_ROLE_ID_MESS = 2;
	public static final int USER_ROLE_ID_MEMBER = 3;

	public static final String USER_ROLE_SUPERADMIN = "SUPERADMIN";
	public static final String USER_ROLE_MESS = "MESS";
	public static final String USER_ROLE_MEMBER = "MEMBER";

	// --------------------------------------------------------------------------------------------

	public static final String DEFAULT_COUNTRY = "INDIA";

	public static final String CREATEDBY_SELF = "SELF";

	// ----------------- MESS MEMBERSHIP REQUEST ---------------------------

	public static final String MEMBERSHIP_REQUEST_ACCEPTED = "ACCEPTED";
	public static final String MEMBERSHIP_REQUEST_PENDING = "PENDING";
	public static final String MEMBERSHIP_REQUEST_REJECTED = "REJECTED";

	// ----------------- MEAL TYPE ----------------------------------

	public static final String MEAL_TYPE_VEG = "VEG";
	public static final String MEAL_TYPE_NON_VEG = "NON-VEG";
	public static final String MEAL_TYPE_BOTH = "BOTH";

	// ----------------- MEAL COUPEN ----------------------------------

	public static final String MEAL_COUPEN_STATUS_ACTIVE = "ACTIVE";
	public static final String MEAL_COUPEN_STATUS_EXPIRED = "EXPIRED";

	// ----------------- MEAL COUPEN ----------------------------------

	public static final String MEAL_STATUS_OPEN = "OPEN";
	public static final String MEAL_STATUS_CLOSED = "CLOSED";

	// ----------------- MEAL READ BY ----------------------------------

	public static final String MEAL_READ_BY_QR = "QR";
	public static final String MEAL_READ_BY_MANUAL = "MANUAL";

	public static final String MEAL_FOR_SELF = "SELF";
	public static final String MEAL_FOR_FRIEND = "FRIEND";

	// ----------------- OTP ----------------------------------

	public static final String OTP_FOR_EMAIL_VERIFICATION = "Email Verifcation";
	public static final String OTP_FOR_MOBILE_NO_VERIFICATION = "Mobile No Verifcation";
	public static final String OTP_FOR_PASSWORD_RESET = "Password Reset";

	public static final String OTP_VERIFIED = "Verified";
	public static final String OTP_EXPIRED = "Expired";
	public static final String OTP_ACTIVE = "Active";

	// ----------------- DEVICES ----------------------------------

	public static final String DEVICE_ANDROID = "ANDROID";
	public static final String DEVICE_IOS = "iOS";

	// ----------------- MESS PAYMENT --------------------------------

	public static final String MESS_PAYMENT_PENDING = "Pending";
	public static final String MESS_PAYMENT_PAID = "Paid";

	// ----------------- RESPONSE STATUS --------------------------------

	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_DANGER = "danger";

	// --------------------------------------------------------------------------------------------

	public static String getProjectName() {
		return PROJECT_NAME;
	}

}
