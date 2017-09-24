package com.byb.bhojan.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.byb.bhojan.model.UserProfile;

public class RandomStringGenerator {

	public static String getRandomString(int length) {

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		uuid = uuid.substring(uuid.length() - length);

		return uuid;
	}

	public static String getRandomNumber_MaxLen9(int length) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int min = 100000000;
		int max = 999999999;

		String str = String.valueOf(r.nextInt((max - min) + 1) + min);

		str = str.substring(0, Math.min(str.length(), length));

		return str;
	}

	public static String getMessCode(String messName) {

		StringBuilder messCode = new StringBuilder("MM-");
		messCode.append(messName.substring(0, 2).toUpperCase() + "-");
		Random r = new Random();
		int Low = 111;
		int High = 999;
		int newID = r.nextInt(High - Low) + Low;
		messCode.append(newID);

		return messCode.toString();
	}

	public static Set<String> getNewMobileNumbers(Set<String> existingNos, int limit) {

		Random r = new Random();
		Set<String> nos = new HashSet<String>();
		Set<String> all = new HashSet<String>();

		all.addAll(existingNos);

		int min = 71;
		int max = 99;

		int min2 = 10000000;
		int max2 = 99999999;

		while (nos.size() < limit) {

			String str = String.valueOf(r.nextInt((max - min) + 1) + min);
			String str2 = String.valueOf(r.nextInt((max2 - min2) + 1) + min2);
			String finalStr = str + str2;
			if (all.add(finalStr)) {
				nos.add(finalStr);
			}
		}

		System.out.println("Existing Nos : " + existingNos.size());
		System.out.println("New Nos : " + nos.size());
		System.out.println("All : " + all.size());

		return nos;
	}

	public static String getNewMobileNumber() {

		Random r = new Random();

		int min = 71;
		int max = 99;

		int min2 = 10000000;
		int max2 = 99999999;

		String str = String.valueOf(r.nextInt((max - min) + 1) + min);
		String str2 = String.valueOf(r.nextInt((max2 - min2) + 1) + min2);
		String finalStr = str + str2;

		return finalStr;
	}

	public static String getNewEmail(UserProfile userProfile) {

		Random r = new Random();

		int min = 71;
		int max = 99;

		if (userProfile.getFirstName() == null) {
			return null;
		}
		if (userProfile.getFirstName().trim().equals("")) {
			return null;
		}
		if (userProfile.getLastName() == null) {
			return null;
		}
		if (userProfile.getLastName().trim().equals("")) {
			return null;
		}

		String email = userProfile.getFirstName().trim().toLowerCase() + userProfile.getLastName().trim().toLowerCase() + "@gmail.com";

		return email;
	}
}
