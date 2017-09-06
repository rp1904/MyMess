package com.byb.bhojan.util;

import java.util.Random;
import java.util.UUID;

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
}
