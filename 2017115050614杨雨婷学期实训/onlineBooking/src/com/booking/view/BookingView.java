/**  
 * @Title: BookingView.java
 * @Package com.booking.view
 * @author 姜向阳
 * @date 2018年7月3日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.booking.entity.Ticket;
import com.booking.service.BookingService;
import com.booking.service.impl.BookingServiceImpl;
import com.booking.util.CalendarPanel;
import com.booking.util.DateFormatUtil;
import com.booking.util.ViewBackgroundUtil;

/**
 * @ClassName: BookingView
 * @Description: 开户界面
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class BookingView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2870945371327830406L;

	private ActionListener actionListener;

	/**
	 * @Fields startPlace : 始发地
	 */
	private JComboBox<String> startComboBox;
	/**
	 * @Fields endPlace : 目的地
	 */
	private JComboBox<String> endComboBox;
	/**
	 * @Fields idNumber : 身份证号码
	 */
	private JTextField idNumber;
	/**
	 * @Fields name : 姓名
	 */
	private JTextField name;
	/**
	 * @Fields date : 出行日期
	 */
	private JTextField date;
	/**
	 * @Fields flightNo : 航班号
	 */
	private JTextField flightNo;
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
	private FlightListView dialog;

	public JTextField getFlightNo() {
		return flightNo;
	}

	public BookingView(ActionListener actionListener) {
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
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
		
		// 身份证号码
		JLabel idLabel = new JLabel("身份证号:");
		idLabel.setBounds(180, 240, 90, 33);
		// 文字右对齐
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 身份证文本框
		idNumber = new JTextField();
		idNumber.setBounds(380, 240, 200, 33);
		idNumber.setToolTipText("请输入身份证号码");

		// 姓名
		JLabel nameLabel = new JLabel("姓名:");
		nameLabel.setBounds(180, 280, 90, 33);
		// 文字右对齐
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 姓名文本框
		name = new JTextField();
		name.setBounds(380, 280, 200, 33);
		name.setToolTipText("请输入姓名");
		
		// 出行日期
		JLabel dateLabel = new JLabel("出行日期:");
		dateLabel.setBounds(180, 360, 90, 33);
		dateLabel.setForeground(Color.ORANGE);
		// 文字右对齐
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 出行日期文本框
		date = new JTextField();
		date.setBounds(380, 360, 200, 33);
		date.setToolTipText("请输入出行日期");
		// 定义日历控件面板类
		CalendarPanel calendar = new CalendarPanel(date, "yyyy-MM-dd HH:mm:ss");
		calendar.initCalendarPanel();
		calendar.add(date);
		
		// 航班号码
		JLabel flightLabel = new JLabel("选择航班:");
		flightLabel.setBounds(180, 320, 90, 33);
		// 文字右对齐
		flightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// 航班号码文本框
		flightNo = new JTextField();
		flightNo.setBounds(380, 320, 200, 33);
		flightNo.setToolTipText("请点击选择航班");
		flightNo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Ticket ticket = new Ticket();
				ticket.setStartAddress(startComboData[startComboBox.getSelectedIndex()]);
				ticket.setEndAddress(endComboData[endComboBox.getSelectedIndex()]);
				dialog = new FlightListView(BookingView.this,true,"航班信息", flightNo, ticket);
				dialog.setSize(600,520);
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
			}
		});
		
		// 实例化确认按钮
		confirmBtn = new CustomButton(460, 480, CustomButton.RIGHT);
		confirmBtn.setText("确认");
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override//表示下面为方法重写
			public void actionPerformed(ActionEvent e) {
				String startAddress = startComboData[startComboBox.getSelectedIndex()];
				String endAddress = endComboData[endComboBox.getSelectedIndex()];
				String identityNo = idNumber.getText();
				String username = name.getText();
				String startdate = date.getText();
				String airNo = flightNo.getText();
				if (startAddress.equals(endAddress)) {
					JOptionPane.showMessageDialog(BookingView.this, "始发地和目的地不能相同！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(identityNo)) {
					JOptionPane.showMessageDialog(BookingView.this, "身份证号不能为空，请填写身份证号！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if ("".equals(username)) {
					JOptionPane.showMessageDialog(BookingView.this, "姓名不能为空，请填写姓名！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if ("".equals(startdate)) {
					JOptionPane.showMessageDialog(BookingView.this, "出行时间不能为空，请填写出行时间！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					// 获取当前时间
					String nowDate = DateFormatUtil.getDateFormatUtil().formatStrByDate(new Date());
					// 判断出行时间不能早于当前时间
					if(startdate.compareToIgnoreCase(nowDate) < 0){
						JOptionPane.showMessageDialog(BookingView.this, "出行时间不能早于当前时间！",
								"提示信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(startdate.compareToIgnoreCase(dialog.readStartTime()) < 0){
						JOptionPane.showMessageDialog(BookingView.this, "出行时间不能早于起飞时间！",
								"提示信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				if ("".equals(airNo)) {
					JOptionPane.showMessageDialog(BookingView.this, "请选择航班！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Ticket ticket = new Ticket();
				ticket.setStartAddress(startAddress);
				ticket.setEndAddress(endAddress);
				ticket.setIdentityNo(identityNo);
				ticket.setUserName(username);
				ticket.setTravelDate(dialog.readStartTime());//此处将所选航班的出发时间返回
				ticket.setSeat("未选");
				BookingService bookService = new BookingServiceImpl();
				Map<String,Object> resultMap = bookService.createBookingInfo(ticket);
				// 获取返回值
				String resultCode =  String.valueOf(resultMap.get("resultCode"));
				// 验证身份证号输入是否合法
				if (Constants.MSG_ERR_IDENTITYNO.equals(resultCode)) {
					JOptionPane.showMessageDialog(BookingView.this, "身份证号输入错误，请重新输入！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 验证姓名输入是否合法
				if (Constants.MSG_ERR_USERNAME.equals(resultCode)) {
					JOptionPane.showMessageDialog(BookingView.this, "姓名输入错误，请重新输入！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "确定去支付？", "提示", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					BookingView.this.setVisible(false);
					PayView payView = new PayView(actionListener,identityNo);
					payView.setVisible(true);
				}
			}
		});
		
		// 实例化返回按钮
		backBtn = new CustomButton(160, 480, CustomButton.LEFT);
		backBtn.setText("返回");
		backBtn.addActionListener(actionListener);
		backBtn.setActionCommand("backToMainView");


		this.getContentPane().add(startLabel);
		this.getContentPane().add(startComboBox);
		this.getContentPane().add(endLabel);
		this.getContentPane().add(endComboBox);
		this.getContentPane().add(idLabel);
		this.getContentPane().add(idNumber);
		this.getContentPane().add(nameLabel);
		this.getContentPane().add(name);
		this.getContentPane().add(dateLabel);
		this.getContentPane().add(date);
		this.getContentPane().add(calendar);
		this.getContentPane().add(flightLabel);
		this.getContentPane().add(flightNo);
		this.getContentPane().add(backBtn);
		this.getContentPane().add(confirmBtn);
		
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
	}
}
