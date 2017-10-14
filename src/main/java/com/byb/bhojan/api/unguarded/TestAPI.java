package com.byb.bhojan.api.unguarded;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byb.bhojan.api.comman.BaseController;
import com.byb.bhojan.model.AppMember;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.services.AppVersionServices;
import com.byb.bhojan.services.MessServices;
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
	public MessServices messServices;

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

	@RequestMapping(value = "/umn", method = RequestMethod.GET)
	public int updateMessesName() {

		List<Mess> messes = messServices.getAllMessess();
		System.out.println("Mess List Size " + messes.size());
		List<String> names = getMessNameList();
		System.out.println("Mess Name Size " + names.size());
		Collections.shuffle(names);
		int updatedCount = 0;

		for (int i = 0; i < messes.size(); i++) {
			try {
				Mess m = messes.get(i);
				m.setMessName(names.get(i));
				messServices.updateMess(m);
				System.out.println(names.get(i));
				updatedCount++;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return updatedCount;
	}

	public List<String> getMessNameList() {
		return new ArrayList<String>(Arrays.asList("Vithu Mauli Mess", "Vahini's Mess", "Andhra Mess", "Kerala Mess Service", "Badshahi Restaurant", "Satvik Mess", "Aware Maratha Khanawal", "MONTHLY MESS", "Mamta Mess", "RAI MONTHLY MESS", "Dining Hall", "Murlidhar Mess",
				"sharvari caterers", "Mahalaxmi Mess", "Kulkarni Mess", "Neelam Restaurant", "Andhra Mess", "trupti mess", "New Milan Khanawala", "Annapurna Pure", "Rasoi Mess", "Kerala Fast Food", "Anuradha Mess", "Punjabi Mess", "Reddys Andhra Meals",
				"Zakas Food", "Shri Murlidhar Mess", "Krushnai Night Kitchen", "Ruchira", "Vasant Utsav Hotel", "Monthly Mess", "Annapurna Mess", "Bedekar Mess", "Velhal Mess", "Sai Mess", "Vhatkar Mess", "Sai Mess", "HARDIK MESS", "Mugdar Mess", "Mama Mami Mess",
				"Mayur Sai Mess", "Sujit Rohit Mess", "Avanti Food Point", "Atharv Mess", "Tulasi Mess", "Naik Mess", "Shree Gurudev Mess", "My Tiffin Service", "Samarth Mess", "Delhi Mess", "Mahaveer Mess", "Charaka Mess", "Manisha Mess", "Lakshmi Mess",
				"Annapurneshwari Thali", "Padmashree Mess", "Hotel Shreya", "Laxmi Mess", "Anand Mess", "Sai Mess", "Delhi Mess", "Vijay Mess", "Shree Jagdamba Mess", "Guru Krupa Mess", "Sai Mess", "Annapurna Mess", "Sangam Mess", "Rahul Mess", "Kanchan Mess",
				"Chowgule Mess", "Samadhan Mess", "Mangal Mess", "Laxmi Mess", "OM Mess", "Annapurna Mess", "Deepa Mess", "Suruchi Mess", "Atithi Mess", "Sai Mess", "Patil`s Mess", "Shree Gajanan Mess", "Shree Banshankari Mess", "shree basaveshwar mess",
				"Shri Renuka Mess", "Majukar Mess", "Sai Bhojnalay Mess", "Hotel Shri Laxmi", "Annapurneshwari Thali Restaurant", "Renu Mess", "South Indian Mess", "Neelam Mess", "Anesha Mess", "Rajkamal Mess", "LAXMI RESTAURANT", "Royal Mess", "Paliwal Mess",
				"Bansod Mess", "PRANAY MESS CENTER", "Jagdip Mess Bhojnalay", "Maa Durga Mess", "Manjari Panhalkar Mess", "Nikhath Mess Center", "Mega Mess", "Suder Mess", "Shree Mess", "Shree Sai Samarth Mess", "Anshul Mess", "Kathole Bro's Mess", "Chetna Mess",
				"S.K MESS", "R.D. Mess", "Jai Shri Krishna Mess", "SUDHA MESS", "Tannu Mess", "Deshmukh Mess", "Suhana Mess", "Mahalaxmi Mess", "Parate's Mess", "Rohini mess", "Lucky Mess", "Soni Mess", "Gurumauli Mess", "Bhole Mess", "Andhra Mess", "Shiva Mess",
				"Ma Gayatri Mess"));
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
