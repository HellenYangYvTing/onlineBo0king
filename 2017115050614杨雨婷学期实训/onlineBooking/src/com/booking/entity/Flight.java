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
public class Flight implements Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	private static final long serialVersionUID = 7530298768597347483L;
	
	/**
	 * 航班号
	 */
	private String flightNo;
	
	/**
	 * 始发地
	 */
	private String startAddress;
	
	/**
	 * 目的地
	 */
	private String endAddress;
	
	/**
	 * 起飞时间
	 */
	private String startTime;
	
	/**
	 * 到达时间
	 */
	private String arrivedTime;
	
	/**
	 * 航空公司
	 */
	private String airLineName;
	
	/**
	 * 机型
	 */
	private String planeType;
	
	/**
	 * 票价
	 */
	private String price;
	
	/**
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}

	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	public String getAirLineName() {
		return airLineName;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
