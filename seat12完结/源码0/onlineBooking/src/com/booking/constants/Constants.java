/**  
 * @Title: Constants.java
 * @Package com.booking.constants
 * @author 刘继欣
 * @date 2018年07月03日
 * @version V1.0  
 */
package com.booking.constants;

/**
 * @ClassName: Constants
 * @Description: 标识常量类
 * @author 刘继欣
 * @date 2018年07月03日
 * @since JDK 1.8
 */
public class Constants {

	/**
	 * @Fields RESULT_KEY_CODE : 返回code的key
	 */
	public static final String RESULT_KEY_CODE = "resultCode";
	
	/**
	 * @Fields RESULT_KEY_DATA : 返回数据的key
	 */
	public static final String RESULT_KEY_DATA = "resultData";
	
	/**
	 * @Fields MESSAGE : 返回提示信息
	 */
	public static final String MESSAGE = "message";
	
	/**
	 * @Fields MSG_SUCCESS : 操作成功
	 */
	public static final String MSG_SUCCESS = "10000";
	
	/**
	 * @Fields MSG_ERR_IDENTITYNO : 身份证错误
	 */
	public static final String MSG_ERR_IDENTITYNO = "-10001";
	
	/**
	 * @Fields MSG_ERR_USERNAME : 姓名错误
	 */
	public static final String MSG_ERR_USERNAME = "-10002";
	
	/**
	 * @Fields MSG_ERR_ORDERNO : 订单编号错误
	 */
	public static final String MSG_ERR_ORDERNO = "-10003";
	
	/**
	 * @Fields MSG_ON_DUTY : 值机失败
	 */
	public static final String MSG_ON_DUTY = "-10004";
	
	/**
	 * @Fields MSG_ERR_SAVE_DATA :  数据保存失败
	 */
	public static final String MSG_ERR_SAVE_DATA = "-10009";
	
	/**
	 * @Fields MSG_ERR_GET_DATA :  数据获取失败
	 */
	public static final String MSG_ERR_GET_DATA = "-10010";
	
	/**
	 * @Fields MSG_ENDADDRESS_VALIDATE :  目的地为空时提示
	 */
	public static final String MSG_ENDADDRESS_VALIDATE = "-2000";
	
	/**
	 * @Fields MSG_IDENTITY_NO_VALIDATE :  身份证号为空时提示
	 */
	public static final String MSG_IDENTITY_NO_VALIDATE = "-2001";
	
	
	/**
	 * @Fields MSG_PAY_VALIDATE :  支付方式为空时提示
	 */
	public static final String MSG_PAY_VALIDATE = "-2002";
	
	/**
	 * @Fields MSG_STARTADDRESS_VALIDATE :  始发地为空时提示
	 */
	public static final String MSG_STARTADDRESS_VALIDATE = "-2003";
	
	/**
	 * @Fields MSG_TRAVE_DATE_VALIDATE :  出行时间为空时提示
	 */
	public static final String MSG_TRAVE_DATE_VALIDATE = "-2004";
	
	/**
	 * @Fields MSG_USER_NAME_VALIDATE :  姓名为空时提示
	 */
	public static final String MSG_USER_NAME_VALIDATE = "-2005";
	
	/**
	 * @Fields PAY_SUCCESS : 返回提示信息
	 */
	public static final String PAY_SUCCESS = "支付成功，请牢记订单号";
	
	/**
	 * @Fields FILE_NAME :  文件名
	 */
	public static final String FILE_NAME = "ticket";
	
	/**
	 * @Fields FILE_NAME :  文件名
	 */
	public static final String FILE_FLIGHTS_NAME = "flights";
	
	/**
	 * @Fields FILE_SEAT_NAME :  文件名
	 */
	public static final String FILE_SEAT_NAME = "seat";
}
