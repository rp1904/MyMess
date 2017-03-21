package com.btechnoserve.mymess.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class CommonMethods {
	
	public static final String ONLY_DATE_FORMAT_STR1 = "MM-dd-yyyy";
	
	public static final String ONLY_DATE_FORMAT_STR2 = "dd MMMMM, yyyy";
	
	public static final String ONLY_TIME_FORMAT_STR = "HH:mm:ss";
	
	public static final String ONLY_TIME_FORMAT_WITHOUT_SECONDS_STR = "HH:mm";
	
	public static final String ONLY_TIME_FORMAT_WITH_AMPM_STR = "hh:mm a";
	
	//private Logger logger = Logger.getLogger(CommonMethods.class);
	
	public static long getCurrentMillisecondTime(String timeZone) {

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		return calendar.getTimeInMillis();
	}
	
	public static String getStringDate(long aDate, String pattern, String timeZone) {

		Date date = new Date(aDate);
		SimpleDateFormat df2 = new SimpleDateFormat(pattern);
		df2.setTimeZone(TimeZone.getTimeZone(timeZone));
		String dateText = df2.format(date);

		return dateText;
	}
	
	public static long getDateInMilisecondsUsingTimeZone(String dateStr, String dateFormatStr, String timezone) {
		long dateInMiliseconds = 0;
		try {
			if (dateFormatStr == null || ("".equals(dateFormatStr.trim()))) {
				dateFormatStr = ONLY_DATE_FORMAT_STR1;
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
			dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
			Date date = dateFormat.parse(dateStr);
			dateInMiliseconds = date.getTime();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return dateInMiliseconds;
	}
	
	public static String getDateFromTimeInMilisecondsUsingTimezone(long miliseconds,String dateFormat, String timezone) {
		String date = "";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone(timezone));
			Date resultdate = new Date(miliseconds);
			date = sdf.format(resultdate);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return date;
	}	
	
	public static long getEndOfDay(long  date, String timezone) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		//TimeZone tz = TimeZone.getTimeZone(timezone);
		//calendar.setTimeZone(tz);
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTimeInMillis();
	}
	
	public static long getDayEndOfDate(long timestamp) {

		long currentDayStart;

		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(timestamp);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 00);

		currentDayStart = c.getTimeInMillis();

		return currentDayStart;

	}
	
	public static long getStartOfDay(long date, String timezone) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		//TimeZone tz = TimeZone.getTimeZone(timezone);
		//calendar.setTimeZone(tz);
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	public static long getDayStartOfDate(long timestamp) {

		long currentDayStart;

		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(timestamp);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 01);

		currentDayStart = c.getTimeInMillis();

		return currentDayStart;
	}
	
	public static long getDateInMilisecondsUsingTimeZoneFromMillis(long dateInMills,String timezone) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
		calendar.setTimeInMillis(dateInMills);
		
		return calendar.getTimeInMillis();
	}
	
	public static String getOnlyTimeFromDate(long dateInMillis,String timeFormat, String timezone) {
		
		String time = "00:00:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		Date date = new Date(dateInMillis);
		time = simpleDateFormat.format(date);
		return time;
	}
	
	public static void main(String[] args){
		System.out.println(getOnlyTimeFromDate(1458644756972l,ONLY_TIME_FORMAT_WITH_AMPM_STR, "GMT"));
		System.out.println(getDateFromTimeInMilisecondsUsingTimezone(1458644756972l,ONLY_DATE_FORMAT_STR2, "GMT"));
	}
}
