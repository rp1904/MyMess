package com.byb.bhojan.util;

import java.util.Calendar;
import java.util.Date;

public class Dates {

	public static Date getEndOfDay(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getDateAfterDays(Date date, int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date getTimeStampAfterHours(Date date, int hrs) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hrs);

		return calendar.getTime();
	}
	
	public static Long getMillisAfterHours(Date date, int hrs) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hrs);

		return calendar.getTimeInMillis();
	}

}
