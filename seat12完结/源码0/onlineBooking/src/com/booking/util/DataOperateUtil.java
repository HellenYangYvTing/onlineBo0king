/**  
 * @Title: DataOperateUtil.java
 * @Package com.booking.util
 * @author 姜向阳
 * @date 2018年07月03日
 * @version V1.0  
 */
package com.booking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.booking.constants.Constants;
import com.booking.entity.Flight;
import com.booking.entity.Ticket;

/**
 * @ClassName: DataOperateUtil
 * @Description: 购票信息数据操作工具类
 * @author 姜向阳
 * @date 2018年07月03日
 * @since JDK 1.8
 */
public class DataOperateUtil {

	private static DataOperateUtil dataOperateUtil = new DataOperateUtil();

	private DataOperateUtil() {

	}

	/**
	 * @return dataOperateUtil
	 */
	public static DataOperateUtil getDataOperateUtil() {
		return dataOperateUtil;
	}

	/**
	 * 
	 * @Title: readCardData
	 * @Description: 读取数据方法
	 * @param ticketParam 文件名称
	 * @return List 购票信息
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> readTicketData(String ticketParam) {
		FileInputStream  fis = null;
		ObjectInputStream ois = null;
		List<Ticket> ticket = null;
		try {
			// 文件
			File file = new File(ticketParam + ".data"); 
			// 判断如果文件不存在，则创建文件
			if(!file.exists()) {    
			    try {    
			        file.createNewFile();    
			    } catch (IOException e) {    
			        e.printStackTrace();    
			    }    
			} else{
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				ticket =  (List<Ticket>) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois !=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return ticket;
	}
	/**
	 * 
	 * @Title: readCardData
	 * @Description: 读取数据方法
	 * @param ticketParam 文件名称
	 * @return List 购票信息
	 */
	@SuppressWarnings("unchecked")
	public List<Flight> readFlightData(String flightParam) {
		FileInputStream  fis = null;
		ObjectInputStream ois = null;
		List<Flight> flights = null;
		try {
			// 文件
			File file = new File(flightParam + ".data"); 
			// 判断如果文件不存在，则创建文件
			if(!file.exists()) {    
				try {    
					file.createNewFile();    
				} catch (IOException e) {    
					e.printStackTrace();    
				}    
			} else{
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				flights =  (List<Flight>) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois !=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return flights;
	}
	public void writeFlightData(List<Flight> flight) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(Constants.FILE_FLIGHTS_NAME+".data");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(flight);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally {
			if(oos !=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
	
	}
	/**
	 * 
	 * @Title: writeTicketData
	 * @Description: 写入数据方法
	 * @param ticket 购票信息集合
	 * @return String 成功：success，失败：error
	 */
	public String writeTicketData(List<Ticket> ticket) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(Constants.FILE_NAME+".data");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ticket);
			return Constants.MSG_SUCCESS;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Constants.MSG_ERR_SAVE_DATA;
		} catch (IOException e) {
			e.printStackTrace();
			return Constants.MSG_ERR_SAVE_DATA;
		}finally {
			if(oos !=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> readSeatData(String seatFileName) {
		FileInputStream  fis = null;
		ObjectInputStream ois = null;
		List<Map<String,Object>> seat = null;
		try {
			// 文件
			File file = new File(seatFileName + ".data"); 
			// 判断如果文件不存在，则创建文件
			if(!file.exists()) {    
			    try {    
			        file.createNewFile();    
			    } catch (IOException e) {    
			        e.printStackTrace();    
			    }    
			} else{
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				seat =  (List<Map<String,Object>>) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois !=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		return seat;
	}
	/**
	 * 
	 * @Title: writeSeatData
	 * @Description: 写入数据方法
	 * @param ticket 购票信息集合
	 * @return String 成功：success，失败：error
	 */
	public String writeSeatData(List<Map<String,Object>> seat) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(Constants.FILE_SEAT_NAME+".data");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(seat);
			return Constants.MSG_SUCCESS;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Constants.MSG_ERR_SAVE_DATA;
		} catch (IOException e) {
			e.printStackTrace();
			return Constants.MSG_ERR_SAVE_DATA;
		}finally {
			if(oos !=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
	}
}
