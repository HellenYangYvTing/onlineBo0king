/**  
 * @Title: OrderUtil.java
 * @Package com.booking.util
 * @author 刘继欣
 * @date 2018年07月03日
 * @version V1.0  
 */
package com.booking.util;

import java.util.Calendar;

/**
 * @ClassName: OrderUtil
 * @Description: 订单编码工具类
 * @author 刘继欣
 * @date 2018年07月03日
 * @since JDK 1.8
 */
public class OrderUtil {
	
	private static OrderUtil orderUtil = new OrderUtil();	
	private OrderUtil() {
		
	}
	
	/**
	 * @return orderUtil
	 */
	public static OrderUtil getOrderUtil() {
		return orderUtil;
	}

	/**
	 * 
	 * @Title: generateOrderNo
	 * @Description: 生成订单编号
	 * @return String 订单编号   固定值（"T"）+时间戳
	 */
	public String generateOrderNo() {
		// 获取时间戳
		Calendar calendar = Calendar.getInstance();
		// 猫毫秒值
		long date = calendar.getTimeInMillis();
		
		return ""+date;
	}
}
