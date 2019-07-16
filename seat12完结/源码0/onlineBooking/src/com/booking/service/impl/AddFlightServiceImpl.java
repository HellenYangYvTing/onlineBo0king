package com.booking.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.booking.constants.Constants;
import com.booking.entity.Flight;
import com.booking.entity.Ticket;
import com.booking.util.DataOperateUtil;
import com.booking.util.ValidateDataUtil;

public class AddFlightServiceImpl {
	public void createBookingInfo(Flight flight) {
	
		DataOperateUtil dataOperateUtil = DataOperateUtil.getDataOperateUtil();
		// 数据结果集
		List<Flight> tic =  new ArrayList<>();
	
		// 调用写入数据方法
		
		// 读取本地磁盘数据
		List<Flight> ticketList = dataOperateUtil.readFlightData(Constants.FILE_FLIGHTS_NAME);
		if (ticketList == null){
			tic.add(flight);         //
			dataOperateUtil.writeFlightData(tic);
		}else {
			ticketList.add(flight);   //
			dataOperateUtil.writeFlightData(ticketList);
		}
	
	}

}
