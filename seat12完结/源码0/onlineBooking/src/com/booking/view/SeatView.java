/**  
 * @Title: SeatView.java
 * @Package com.booking.view
 * @author 姜向阳
 * @date 2018年7月3日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import com.booking.constants.Constants;
import com.booking.entity.Seat;
import com.booking.service.BookingService;
import com.booking.service.impl.BookingServiceImpl;
import com.booking.util.ViewBackgroundUtil;

/**
 * @ClassName: SeatView
 * @Description: 选座界面
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class SeatView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7489212337354945149L;

	
	/**
	 * 座位列头
	 */
	private String[] column = {"A", "B", "C", "D", "E", "F"};
	/**
	 * @Fields submitBtn : 确认按钮
	 */
	private JButton submitBtn;
	
	private Map<String, Object> testData;
	
	/**
	 * @Fields autoBtn : 自动按钮
	 */
	private JButton autoBtn;
	ImageIcon image = null;
	JLabel empty = null;
	private int maxRow = 5;
	private int maxColumn = 7;
	
	private List<String> orderList;

	public SeatView(ActionListener actionListener,List<String> orderList) {
		this.orderList = orderList;
		init();
	}

	private void init() {
		testData = new HashMap<>();
	    BookingService bookingService = new BookingServiceImpl();
	    Map<String, Object> map = bookingService.readSeatData(orderList.get(0));
	    Seat seat =  new Seat();
	    String seatNo = null;
	    if (map.get("resultCode") != Constants.MSG_ERR_GET_DATA){
	    	@SuppressWarnings("unchecked")
			List<Map<String, Object>> seatList = (List<Map<String, Object>>)map.get(Constants.RESULT_KEY_DATA);
	    	if (seatList.size() > 0) {
	    		Iterator<Map<String, Object>> it = seatList.iterator();
	    		while(it.hasNext()) {
	    			Map<String, Object> seatMap = it.next();
	    			seatNo = String.valueOf(seatMap.get("seatNo"));
	    		}
	    	}
	    }
	    @SuppressWarnings("unchecked")
		List<String> testList =  (List<String>) map.get("seatNoList");
	    if (testList != null) {
	    	if (testList.contains(seatNo) ) {
	    		testList.remove(seatNo);
	    		testData.put("my", seatNo);
	    	}
	    }
	    testData.put("testList", testList);
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		
		int x = 200;
		int y = 160;
		for (int i = 1; i <= maxRow; i++) {
			for (int j = 1; j <= maxColumn; j++) {
				// 左侧座位数字
				if (j == 1) {
					JLabel jLabel = new JLabel(i+"");
					//x轴固定为最左侧，y轴起始位置为160，每增加1行，从起始位置+图片高度（27+10）*行号
					jLabel.setBounds(x, y + 37 * i, 30, 27);
					this.getContentPane().add(jLabel);
				}
				if (j != 4) {
					String columnStr = "";
					if (j < 4) {
						columnStr = column[j-1];
					} else if (rootPaneCheckingEnabled) {
						columnStr = column[j-2];
					}
					if (i == 1) {
						JLabel jLabel = new JLabel(columnStr, JLabel.CENTER);
						//x轴固定为最左侧，y轴起始位置为160，每增加1行，从起始位置+图片高度（27+10）*行号
						jLabel.setBounds(x + 40 * j, y, 30, 27);
						this.getContentPane().add(jLabel);
					}
					
					JButton homepage = new CustomButton2(x + 40 * j, y + 37 * i, String.valueOf(columnStr + "" + i));
					this.getContentPane().add(homepage);
				}

			}
		}
		
		checked();
		if ("".equals(testData.get("my"))) {
			randomClick();
		}
		// 实例化确认按钮
		submitBtn = new CustomButton(130, 460, CustomButton.LEFT);
		submitBtn.setText("确认");
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//添加确认提示框，会返回一个整数
				int doSelected = JOptionPane.showConfirmDialog(null, "确定选择： "+ CustomButton2.clickStr +" 座位？", "提示", JOptionPane.YES_NO_OPTION);
				//如果这个整数等于JOptionPane.YES_OPTION，则说明你点击的是“确定”按钮，则允许继续操作，否则结束
				if(doSelected == JOptionPane.YES_OPTION){
				    seat.setStatus("已占");
				    seat.setSeatNo(CustomButton2.clickStr);
				    // 存储选座信息  设置对象的座位号、状态，写入clickStr
				   bookingService.createSeatInfo(seat,orderList);
				    JOptionPane.showMessageDialog(null, "座位选定，值机成功！");
				    // 关闭选座窗口，返回主窗口
				    SeatView.this.setVisible(false);
				}
			}
		});
		submitBtn.setActionCommand("choosedSubmit");
//		
		// 实例化自动按钮
		autoBtn = new CustomButton(500, 460, CustomButton.LEFT);
		autoBtn.setText("自动");
		autoBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				randomClick();
			}
		});
		autoBtn.setActionCommand("autoChoosedSubmit");

		this.getContentPane().add(submitBtn);
		this.getContentPane().add(autoBtn);
		
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}
	
	@SuppressWarnings("unchecked")
	public void checked() {
		List<String> testList = (List<String>) testData.get("testList");
		String my = String.valueOf(testData.get("my"));
		Component[] components = this.getContentPane().getComponents();
		for (Component component : components) {
			if (component instanceof CustomButton2) {
				CustomButton2 button = (CustomButton2) component;
				String actionCommand = button.getActionCommand();
				if (testList != null && !testList.isEmpty() && testList.contains(actionCommand) && !actionCommand.equals(my)) {
					button.isCheck(true, false, CustomButton2.unEnableIcon,"init",null);
				}
				if (my != null && !my.isEmpty() && actionCommand.equals(my)) {
					button.isCheck(true, true, CustomButton2.clickedIcon,"myInit",my);
				}
			}
		}
	}
	
	private void randomClick() {
		Component[] components = this.getContentPane().getComponents();
		int randomColumn = (int) (Math.random()*maxColumn-1);
		int randomRow = (int) (1+Math.random()*maxRow);
		String id = column[randomColumn] + "" + randomRow;
		@SuppressWarnings("unchecked")
		List<String> testList = (List<String>) testData.get("testList");
		// 如果随机数是不可选座位，重新随机
		if (testList != null ) {
			if (testList.contains(id)) {
				randomClick();
				return;
			}
		}
		for (Component component : components) {
			if (component instanceof CustomButton2) {
				CustomButton2 button = (CustomButton2) component;
				String actionCommand = button.getActionCommand();
				if (actionCommand.equals(id)) {
					button.isCheck(true, true, CustomButton2.clickedIcon,"randomClick",null);
				}
			}
		}
	}
	

}
