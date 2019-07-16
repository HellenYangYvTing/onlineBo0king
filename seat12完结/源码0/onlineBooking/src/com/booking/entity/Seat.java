/**  
 * @Title: Seat.java
 * @Package com.booking.entity
 * @author 姜向阳
 * @date 2018年07月04日
 * @version V1.0  
 */
package com.booking.entity;

import java.io.Serializable;
/**
 * @ClassName: Seat
 * @Description: 座位信息实体
 * @author 姜向阳
 * @date 2018年07月04日
 * @since JDK 1.8
 */
public class Seat implements Serializable {
	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	private static final long serialVersionUID = 7530298768597347416L;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 座位号
	 */
	private String seatNo;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the seatNo
	 */
	public String getSeatNo() {
		return seatNo;
	}

	/**
	 * @param seatNo the seatNo to set
	 */
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
}
