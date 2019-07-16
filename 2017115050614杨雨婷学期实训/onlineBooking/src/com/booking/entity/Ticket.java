/**  
 * @Title: Ticket.java
 * @Package com.booking.entity
 * @author 姜向阳
 * @date 2018年07月04日
 * @version V1.0  
 */
package com.booking.entity;

import java.io.Serializable;


/**
 * @ClassName: Ticket
 * @Description: 购票信息实体
 * @author 姜向阳
 * @date 2018年07月04日
 * @since JDK 1.8
 */
public class Ticket implements Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	private static final long serialVersionUID = 7530298768597347416L;
	
	/**
	 * 始发地
	 */
	private String startAddress;
	
	/**
	 * 目的地
	 */
	private String endAddress;
	
	/**
	 * 出行时间
	 */
	private String travelDate;
	
	/**
	 * 付款方式
	 */
	private int payWay;
	
	/**
	 * 订单编号
	 */
	private String orderNo;
	
	
	/**
	 * 身份证
	 */
	private String identityNo;
	
	/**
	 * 姓名
	 */
	private String userName;
	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	/**
	 *座位
	 */
	private String seat;
	
	/**
	 * @return the identityNo
	 */
	public String getIdentityNo() {
		return identityNo;
	}

	/**
	 * @param identityNo the identityNo to set
	 */
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the startAddress
	 */
	public String getStartAddress() {
		return startAddress;
	}

	/**
	 * @param startAddress the startAddress to set
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	/**
	 * @return the endAddress
	 */
	public String getEndAddress() {
		return endAddress;
	}

	/**
	 * @param endAddress the endAddress to set
	 */
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	/**
	 * @return the travelDate
	 */
	public String getTravelDate() {
		return travelDate;
	}

	/**
	 * @param travelDate the travelDate to set
	 */
	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	/**
	 * @return the payWay
	 */
	public int getPayWay() {
		return payWay;
	}

	/**
	 * @param payWay the payWay to set
	 */
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
