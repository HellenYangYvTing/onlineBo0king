/**  
 * @Title: ValidateDataUtil.java
 * @Package com.booking.util
 * @author 姜向阳
 * @date 2018年07月04日
 * @version V1.0  
 */
package com.booking.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.booking.constants.Constants;
import com.booking.entity.Ticket;


/**
 * @ClassName: ValidateDataUtil
 * @Description:  数据验证工具类
 * @author 姜向阳
 * @date 2018年07月04日
 * @since JDK 1.8
 */
public class ValidateDataUtil {

	private static ValidateDataUtil validateDataUtil = new ValidateDataUtil();
	
	private ValidateDataUtil() {
		
	}
	
	/**
	 * @return validateDataUtil
	 */
	public static ValidateDataUtil getValidateDataUtil() {
		return validateDataUtil;
	}
	
	/**
	 * 
	 * @Title: validateIdentityNo
	 * @Description:  验证身份证号合法性
	 * @param IdentityNo 身份证号
	 * @return boolean true：合法，false：不合法
	 */ 
	public  boolean validateIdentityNo(String IdentityNo) {
		String regEx = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(IdentityNo);
		boolean result = m.matches();
		return result;
	}
	
	/**
	 * 
	 * @Title: validateOrderNo
	 * @Description:  订单编号合法性
	 * @param orderNo 订单编号
	 * @return boolean true：合法，false：不合法
	 */ 
	public  boolean validateOrderNo(String orderNo) {
		String regEx = "^[1-9]\\d{12}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(orderNo);
		boolean result = m.matches();
		return result;
	}
	
	/**
	 * 
	 * @Title: validateName
	 * @Description:  验证姓名合法性
	 * @param userName 姓名
	 * @return boolean true：合法，false：不合法
	 */ 
	public  boolean validateName(String userName) {
		String regEx="^[\u4E00-\u9FA5A-Za-z]+$"; 
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(userName);
		boolean result = m.matches();
		return result;
	}
	
	/**
	 * 
	 * @Title: validateName
	 * @Description:  验证时间格式合法性
	 * @param date 日期
	 * @return boolean true：合法，false：不合法
	 */ 
	public  boolean validateDateF(String date) {
		String regEx="([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-"
				+ "(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|"
				+ "30))|(02-(0[1-9]|[1][0-9]|2[0-8])))"; 
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(date);
		boolean result = m.matches();
		return result;
	}
	
	/**
	 * 
	 * @Title: validateIsEmpty
	 * @Description:  验证数据是否为空
	 * @param ticket 
	 * @return 提示信息
	 */ 
	public String validateIsEmpty(Ticket ticket) {
		String payWay = String.valueOf(ticket.getPayWay());
		// 判断目的地
		if ("".equals(ticket.getEndAddress()) || ticket.getEndAddress() == null) {
			return Constants.MSG_ENDADDRESS_VALIDATE;
		}
		// 判断身份证
		if ("".equals(ticket.getIdentityNo()) || ticket.getIdentityNo() == null) {
			return Constants.MSG_IDENTITY_NO_VALIDATE;
		}
		// 判断支付方式
		if("".equals(payWay) || payWay == null) {
			return Constants.MSG_PAY_VALIDATE;
		}
		// 判断始发地
		if("".equals(ticket.getStartAddress()) || ticket.getStartAddress() == null){
			return Constants.MSG_STARTADDRESS_VALIDATE;
		}
		// 判断日期
		if("".equals(ticket.getTravelDate()) || ticket.getTravelDate() == null){
			return Constants.MSG_TRAVE_DATE_VALIDATE;
		}
		// 判断姓名
		if("".equals(ticket.getUserName()) || ticket.getUserName() == null){
			return Constants.MSG_USER_NAME_VALIDATE;
		}
		return Constants.MSG_SUCCESS;
	}
}
