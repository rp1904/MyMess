package com.byb.bhojan.api.unguarded;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AppVersionServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.services.impl.EmailService;
import com.byb.bhojan.util.EmailTemplates;

@RestController
@RequestMapping("/test")
public class TestAPI extends BaseController {

	Logger logger = Logger.getLogger(TestAPI.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	public UserServices userServices;

	@Autowired
	public AppVersionServices appVersionServices;

	@RequestMapping(value = "/am", method = RequestMethod.GET)
	public Map<String, String> getAllMembers() {
		Map<String, String> members = new HashMap<String, String>();
		for (User user : userServices.getAllMembers()) {
			members.put(user.getEmail(), user.getMobileNumber());
		}
		return members;
	}

	@RequestMapping(value = "/amo", method = RequestMethod.GET)
	public Map<String, String> getAllMessOwners() {

		Map<String, String> members = new HashMap<String, String>();
		for (User user : userServices.getAllMessOwners()) {
			members.put(user.getEmail(), user.getMobileNumber());
		}
		return members;
	}

	@RequestMapping(value = "/mm/{messId}", method = RequestMethod.GET)
	public List<AppMember> getMlist(@PathVariable("messId") String messId) {

		List<AppMember> ml = userServices.getMemberListForAppByMessIdPk(messId);

		return ml;
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public Map<String, String> testEmail(@PathVariable("email") String email) {
		Map<String, String> result = new HashMap<String, String>();

		String msgBody = EmailTemplates.createLoginCredentialsMessage("Roshan Test Email", "test_email", "123456");

		emailService.sendEmail(email, "TEST EMAIL", msgBody);
		return result;
	}

	@RequestMapping(value = "/1904", method = RequestMethod.GET)
	public User getAdminDetais() {

		User admin = userServices.getSuperAdminDetails();

		return admin;
	}

	@RequestMapping(value = "/1904/{pass}", method = RequestMethod.GET)
	public String resetAllPass(@PathVariable("pass") String pass) {

		long total_updates = userServices.updateAllPasswords(pass);

		return "Password " + pass + " set to " + total_updates + " users !";
	}

	// @RequestMapping(value = "/iv/mess", method = RequestMethod.GET)
	// public Map<String, String> insertMessVersion() {
	// Map<String, String> result = new HashMap<String, String>();
	//
	// AppVersion appVersion = new AppVersion();
	//
	// appVersion.setAppType(ProjectConstant.USER_ROLE_MESS);
	// appVersion.setVersion("0.0.1");
	// appVersion.setDeviceType(ProjectConstant.DEVICE_ANDROID);
	// appVersion.setAppLink("http://www.mybhojan.com/resources/Bhojan-Mess.apk");
	// appVersion.setReleaseNote("Mess Base App");
	// appVersion.setMandatory(Boolean.TRUE);
	// appVersion.setReleaseDate(new Date());
	// appVersion.setCreatedUpdated(new CreatedUpdated("1"));
	//
	// appVersionServices.saveMessAppVersion(appVersion);
	//
	// return result;
	// }
	//
	// @RequestMapping(value = "/iv/member", method = RequestMethod.GET)
	// public Map<String, String> insertMemberVersion() {
	// Map<String, String> result = new HashMap<String, String>();
	//
	// AppVersion appVersion = new AppVersion();
	//
	// appVersion.setAppType(ProjectConstant.USER_ROLE_MEMBER);
	// appVersion.setVersion("0.0.1");
	// appVersion.setDeviceType(ProjectConstant.DEVICE_ANDROID);
	// appVersion.setAppLink("http://www.mybhojan.com/resources/Bhojan-Member.apk");
	// appVersion.setReleaseNote("Member Base App");
	// appVersion.setMandatory(Boolean.TRUE);
	// appVersion.setReleaseDate(new Date());
	// appVersion.setCreatedUpdated(new CreatedUpdated("1"));
	//
	// appVersionServices.saveMemberAppVersion(appVersion);
	//
	// return result;
	// }

}
