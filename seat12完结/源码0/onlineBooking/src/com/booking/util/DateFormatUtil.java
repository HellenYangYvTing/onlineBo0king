/**  
 * @Title: DateFormatUtil.java
 * @Package com.atm.util
 * @author 姜向阳
 * @date 2018年3月14日
 * @version V1.0  
 */
package com.booking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @ClassName: DateFormatUtil
 * @Description:  时间格式化工具类
 * @author 姜向阳
 * @date 2018年3月14日
 * @since JDK 1.8
 */
public class DateFormatUtil {

	private static DateFormatUtil dateFormatUtil = new DateFormatUtil();

	private DateFormatUtil() {

	}

	/**
	 * @return dateFormatUtil
	 */
	public static DateFormatUtil getDateFormatUtil() {
		return dateFormatUtil;
	}

	/**
	 * 
	 * @Title: formatDateByStr
	 * @Description: 日期字符串转换成date
	 * @param dateStr 日期字符串
	 * @return Date 日期
	 */
	public  Date formatDateByStr(String dateStr) {	
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * @Title: formatStrByDate
	 * @Description: date转换成日期字符串
	 * @param date 日期
	 * @return String 日期字符串
	 */
	public  String formatStrByDate(Date date) {	
		String  dateStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateStr = sdf.format(date);
		return dateStr;
	}
	
}
