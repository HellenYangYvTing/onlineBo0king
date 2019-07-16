package com.booking.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.booking.constants.Constants;
import com.booking.entity.Flight;
import com.booking.entity.Ticket;
import com.booking.service.BookingService;
import com.booking.service.impl.AddFlightServiceImpl;
import com.booking.service.impl.BookingServiceImpl;
import com.booking.util.CalendarPanel;
import com.booking.util.DateFormatUtil;
import com.booking.util.ViewBackgroundUtil;

public class AddFlightLsitView extends BaseFrame { //班号、始发地、目的地、起飞时间时间、到达时间、航空公司、机型以及票价

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2870945371327830406L;
	/**
	 * @Fields flightNo : 航班号
	 */
	private JTextField flightNo;
	/**
	 * @Fields startPlace : 始发地
	 */
	private JComboBox<String> startComboBox;
	/**
	 * @Fields endPlace : 目的地
	 */
	private JComboBox<String> endComboBox;
	/**
	 * @Fields date : 出行日期
	 */
	private JTextField date;
	
	/**
	 * @Fields idNumber : 到达时间
	 */
	private JTextField enddate;
	/**
	 * @Fields name :航空公司
	 */
	
	private JTextField name;
	/**
	 * @Fields name :机型
	 */
	
	private JTextField Type;
	/**
	 * @Fields name :票价
	 */
	
	private JTextField Money;
	
	/**
	 * @Fields confirmBtn : 确认按钮
	 */
	private JButton confirmBtn;
	/**
	 * @Fields backBtn : 返回按钮
	 */
	private JButton backBtn;
	/**
	 * @Fields dialog : 航班选择对话窗口
	 */


	public JTextField getFlightNo() {
		return flightNo;
	}

	public AddFlightLsitView() {
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		JLabel flightLabel = new JLabel("添加航班号：");
		flightLabel.setBounds(180, 120, 90, 33);
		// 文字右对齐
		flightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 航班号码文本框
		flightNo = new JTextField();
		flightNo.setBounds(380, 120, 200, 33);
		flightNo.setToolTipText("填入大写字母");
		// 定义下拉列表的条目
        String[] startComboData = new String[]{"大连","北京","上海","广州","深圳","成都","昆明","哈尔滨","长春","沈阳","天津","西安","乌鲁木齐","郑州","武汉","无锡","南京","温州","重庆","三亚","厦门","长沙","青岛","杭州"};
        String[] endComboData = new String[]{"大连","北京","上海","广州","深圳","乌鲁木齐","郑州","武汉","成都","三亚","厦门","长沙","昆明","哈尔滨","长春","沈阳","天津","西安","无锡","南京","温州","重庆","青岛","杭州"};
		// 始发地标签
		JLabel startLabel = new JLabel("始发地:");
		startLabel.setBounds(180, 160, 90, 33);
		// 文字右对齐
		startLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 始发地下拉列表
		startComboBox = new JComboBox<>(startComboData);
		startComboBox.setBounds(380, 160, 200, 33);
        // 设置默认选中的条目
		startComboBox.setSelectedIndex(0);
		// 目的地标签
		JLabel endLabel = new JLabel("目的地:");
		endLabel.setBounds(180, 200, 90, 33);
		// 文字右对齐
		endLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 目的地下拉列表
		endComboBox = new JComboBox<>(endComboData);
		endComboBox.setBounds(380, 200, 200, 33);
		endComboBox.setToolTipText("请输入目的地");	
		JLabel dateLabel = new JLabel("出行日期:");
		dateLabel.setBounds(180, 240, 90, 33);
		dateLabel.setForeground(Color.ORANGE);
		// 文字右对齐
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 出行日期文本框
		date = new JTextField();
		date.setBounds(380, 240, 200, 33);
		date.setToolTipText("请输入出行日期");
		// 定义日历控件面板类
		
		CalendarPanel acalendar = new CalendarPanel(date, "yyyy-MM-dd HH:mm:ss");
		acalendar.initCalendarPanel();
		acalendar.add(date);
		JLabel enddateLabel = new JLabel("到达时间");
		enddateLabel.setBounds(180, 280, 90, 33);
		// 文字右对齐
		enddateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 姓名文本框
		enddate = new JTextField();
		enddate.setBounds(380, 280, 200, 33);
		enddate.setToolTipText("请输入到达时间");
		CalendarPanel abalendar = new CalendarPanel(enddate, "yyyy-MM-dd HH:mm:ss");
		abalendar.initCalendarPanel();
		abalendar.add(enddate);
		JLabel nameLabel = new JLabel("航空公司：");
		nameLabel.setBounds(180, 320, 90, 33);
		// 文字右对齐
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 航班号码文本框
		name = new JTextField();
		name.setBounds(380, 320, 200, 33);
		name.setToolTipText("请输入航空公司");
		//jixing
		JLabel TypeLabel = new JLabel("机型：");
		TypeLabel.setBounds(180, 360, 90, 33);
		// 文字右对齐
		TypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 航班号码文本框
		Type = new JTextField();
		Type.setBounds(380, 360, 200, 33);
		Type.setToolTipText("请输入机型");
		//票价
		JLabel MoneyLabel = new JLabel("票价：");
		MoneyLabel.setBounds(180, 400, 90, 33);
		// 文字右对齐
		MoneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 航班号码文本框
		Money = new JTextField();
		Money.setBounds(380, 400, 200, 33);
		Money.setToolTipText("请输入票价");
		// 实例化确认按钮
		confirmBtn = new CustomButton(460, 480, CustomButton.RIGHT);
		confirmBtn.setText("确认");
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override//表示下面为方法重写
			public void actionPerformed(ActionEvent e) {
				String startAddress = startComboData[startComboBox.getSelectedIndex()];
				String endAddress = endComboData[endComboBox.getSelectedIndex()];
				String planeType = Type.getText();
				String airLineName = name.getText();
				String startTime = date.getText();
				String arrivedTime = enddate.getText();
				String price = Money.getText();
				String aflightNo = flightNo.getText();
				if (startAddress.equals(endAddress)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "始发地和目的地不能相同！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(planeType)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "机型不能为空，请填写机型！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if ("".equals(airLineName)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "航空公司不能为空，请填写航空公司！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(startTime)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "出行时间不能为空，请填写出行时间！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if ("".equals(arrivedTime)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "到达时间不能为空，请填写到达时间！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				} 
				if ("".equals(price)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "票价不能为空，请填写票价！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(aflightNo)) {
					JOptionPane.showMessageDialog(AddFlightLsitView.this, "航班号不能为空，请填写航班号！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					// 获取当前时间
					String nowDate = DateFormatUtil.getDateFormatUtil().formatStrByDate(new Date());
					// 判断出行时间不能早于当前时间
					if(startTime.compareToIgnoreCase(nowDate) < 0){
						JOptionPane.showMessageDialog(AddFlightLsitView.this, "出行时间不能早于当前时间！",
								"提示信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(arrivedTime.compareToIgnoreCase(startTime) < 0){
						JOptionPane.showMessageDialog(AddFlightLsitView.this, "到达时间不能早于出行时间！",
								"提示信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				Flight flight = new Flight();
				flight.setStartAddress(startAddress);
				flight.setEndAddress(endAddress);
				flight.setPlaneType(planeType);
				flight.setAirLineName(airLineName);
				flight.setArrivedTime(arrivedTime);
				flight.setStartTime(startTime);
				flight.setPrice(price);
				flight.setFlightNo(aflightNo);
				
				if (JOptionPane.showConfirmDialog(null, "确定创建新的机票？", "提示", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					AddFlightServiceImpl addFlightServiceImpl = new AddFlightServiceImpl();
					addFlightServiceImpl.createBookingInfo(flight);
					JOptionPane.showMessageDialog(null, "创建机票成功！",
							"银联卡支付成功！", JOptionPane.ERROR_MESSAGE);
					AddFlightLsitView.this.setVisible(false);
					}
			}
		});
		
		// 实例化返回按钮
		backBtn = new CustomButton(160, 480, CustomButton.LEFT);
		backBtn.setText("退出");
		backBtn.addActionListener(new ActionListener() {
			
			@Override//表示下面为方法重写
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(null, "确定退出系统？", "提示", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
					AddFlightLsitView.this.setVisible(false);
					}
					
				}
		});
		this.getContentPane().add(startLabel);
		this.getContentPane().add(startComboBox);//1
		this.getContentPane().add(endLabel);
		this.getContentPane().add(endComboBox);//2
		this.getContentPane().add(dateLabel);
		this.getContentPane().add(date);
		this.getContentPane().add(enddateLabel);//3
		this.getContentPane().add(enddate);
		this.getContentPane().add(startLabel);
		this.getContentPane().add(startComboBox);//4
		this.getContentPane().add(acalendar);
		this.getContentPane().add(abalendar);
		this.getContentPane().add(flightLabel);
		this.getContentPane().add(flightNo);//5
		this.getContentPane().add(nameLabel);
		this.getContentPane().add(name);//6
		this.getContentPane().add(TypeLabel);
		this.getContentPane().add(Type);//7
		this.getContentPane().add(MoneyLabel);
		this.getContentPane().add(Money);//6
		this.getContentPane().add(backBtn);
		this.getContentPane().add(confirmBtn);
		
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
	}

}
