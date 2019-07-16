/**  
 * @Title: BookingService.java
 * @Package com.booking.service
 * @author 姜向阳
 * @date 2018年07月03日
 * @version V1.0  
 */
package com.booking.service;

import java.util.List;
import java.util.Map;

import com.booking.entity.Seat;
import com.booking.entity.Ticket;

/**
 * @ClassName: BookingService
 * @Description: 机票在线销售服务接口
 * @author 姜向阳
 * @date 2018年07月03日
 * @since JDK 1.8
 */
public interface BookingService {
	
	/**
	 * 
	 * @Title: createBookingInfo
	 * @Description: 创建购票信息
	 * @param ticket 购票信息
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> createBookingInfo(Ticket ticket);
	
	/**
	 * 
	 * @Title: readData
	 * @Description: 获取购票信息
	 * @param orderNoOrIdentityNo 订单编号或身份证号
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> readData(String orderNoOrIdentityNo);
	
	/**
	 * 
	 * @Title: readFlightData
	 * @Description: 根据始发地、目的地获取航班信息
	 * @param startAddress 始发地
	 * @param endAddress 目的地
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> readFlightData(String startAddress, String endAddress);
	
	/**
	 * 
	 * @Title: readSeatData
	 * @Description: 根据订单获取订单信息
	 * @param order 订单编号
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> readSeatData(String order);
	
	/**
	 * 
	 * @Title: createSeatInfo
	 * @Description: 存储座位信息和订单信息
	 * @param seat 座位信息，orderList 订单信息
	 * @return  Map<String,Object> 
	 */
	public Map<String, Object> createSeatInfo(Seat seat, List<String> orderList);
	
	/**
	 * 
	 * @Title: updateTravelDateInfo
	 * @Description: 修改出行时间（改签）
	 * @param orderNo 订单编号,travelDate 出行时间
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> updateTravelDateInfo(String orderNo,String travelDate);
	
	/**
	 * 
	 * @Title: deleteOrderInfo
	 * @Description: 退票删除订单编码
	 * @param orderNo 订单编号
	 * @return  Map<String,Object> 
	 */
	public Map<String,Object> deleteOrderInfo(String orderNo);

	
	

}
