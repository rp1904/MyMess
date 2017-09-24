package com.byb.bhojan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

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

	public static String getFormatedDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static long getDiffInDaysBetweenDates(Date fromDate, Date toDate) {

		long diff = fromDate.getTime() - toDate.getTime();

		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
