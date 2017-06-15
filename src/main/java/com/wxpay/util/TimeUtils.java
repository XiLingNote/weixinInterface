package com.wxpay.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	

	/**
	 * 当前月最后一天
	 */
	public static String getCurMonthLastDay(String format){
		DateFormat df = new SimpleDateFormat(format);
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = df.format(ca.getTime());
        return last;
	}
	
	
	
	
	/**
	 * 2个时间比大小
	 * @param time1
	 * @param time2
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static int compareDate(String time1,String time2,String format) throws ParseException{
		DateFormat df = new SimpleDateFormat(format);
		java.util.Date d1 = df.parse(time1);
		java.util.Date d2 = df.parse(time2);
		
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
	}
	
	/**
	 * 时间转毫秒数
	 */
	public static String getMsecFromData(String dateTime,String format) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new SimpleDateFormat(format).parse(dateTime));
			return c.getTimeInMillis()+"";
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 毫秒数转时间
	 * @param sstime
	 * @param format
	 * @return
	 */
	public static String getDataByMsec(String sstime, String format) {
		try {
			Date date = new Date(sstime);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	/**
	 * 毫秒数转时间
	 * 
	 * @return
	 */
	public static String getTimeFromMis(long mis,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		String time = df.format(new Date(mis));
		return time;
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		String time = df.format(new Date());
		return time;
	}

	/**
	 * 字符串转date
	 * 
	 * @param formal
	 * @param time
	 * @return
	 */
	public static Date getDateFromTime(String format, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);// 小写的mm表示的是分钟
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获得某个时间后几天的时间
	 * 
	 * @param format
	 *            时间格式
	 * @param time
	 *            当前时间
	 * @return
	 */
	public static String getTimeDayAfter(String format, Date date, int day) {
		SimpleDateFormat dft = new SimpleDateFormat(format);// 设置日期格式

		Calendar now =Calendar.getInstance();  
		now.setTime(date);  
		now.set(Calendar.DATE,now.get(Calendar.DATE)+day); 

		return dft.format(now.getTime());
	}
	/**
	 * 获得某个时间后几天的时间(毫秒数)
	 * 
	 * @param format
	 *            时间格式
	 * @param time
	 *            当前时间
	 * @return
	 */
	public static long getMisDayAfter(Date date, int day) {
		Calendar now =Calendar.getInstance();  
		now.setTime(date);  
		now.set(Calendar.DATE,now.get(Calendar.DATE)+day); 

		return now.getTime().getTime();
	}
	
	
	/**
	 * 获得某个时间后几个小时的时间
	 * 
	 * @param format
	 *            时间格式
	 * @param time
	 *            当前时间
	 * @return
	 */
	public static String getTimeHoursAfter(String format, Date date, int hour) {
		SimpleDateFormat dft = new SimpleDateFormat(format);// 设置日期格式

		Calendar dar = Calendar.getInstance();
		dar.setTime(date);
		dar.add(java.util.Calendar.HOUR_OF_DAY, hour);

		return dft.format(dar.getTime());
	}

	/**
	 * 获得某个时间后几分钟的时间
	 * 
	 * @param format
	 *            时间格式
	 * @param time
	 *            当前时间
	 * @return
	 */
	public static String getTimeMinuteAfter(String format, Date date, int minute) {
		SimpleDateFormat dft = new SimpleDateFormat(format);// 设置日期格式

		Calendar dar = Calendar.getInstance();
		dar.setTime(date);
		dar.add(java.util.Calendar.MINUTE, minute);

		return dft.format(dar.getTime());
	}
}
