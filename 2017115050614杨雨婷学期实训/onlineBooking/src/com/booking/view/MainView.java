/**  
* @Title: MainView.java
 * @Package com.booking.view
 * @author 姜向阳
 * @date 2018年7月3日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.Timer;

import com.booking.util.ViewBackgroundUtil;

/**
 * @ClassName: MainView
 * @Description: 在线购票系统主窗口
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class MainView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8142213655847297255L;
	private JButton AddFlight;
	/**
	 * @Fields bookingBtn : 增加机票
	 */
	private JButton findFlight;
	/**
	 * @Fields bookingBtn : 查看机票
	 */
	private ActionListener actionListener;
	/**
	 * @Fields bookingBtn : 购票按钮
	 */
	private JButton bookingBtn;
	/**
	 * @Fields checkinBtn : 退票/改签按钮
	 */
	private JButton alterBtn;
	/**
	 * @Fields checkinBtn : 值机按钮
	 */
	private JButton checkinBtn;
	/**
	 * @Fields exitBtn : 退出按钮
	 */
	private JButton exitBtn;

	public MainView(ActionListener actionListener) {
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg1.jpg");
		// 实例化购票按钮
		AddFlight = new CustomButton(120, 400, CustomButton.LEFT);
		AddFlight.setText("添加机票");
		AddFlight.addActionListener(actionListener);
		AddFlight.setActionCommand("addFlight");
		// 实例化购票按钮
		findFlight = new CustomButton(520, 400, CustomButton.RIGHT);
		findFlight.setText("查看机票");
		findFlight.addActionListener(actionListener);
		findFlight.setActionCommand("findFlight");
				

		// 实例化购票按钮
		bookingBtn = new CustomButton(120, 460, CustomButton.LEFT);
		bookingBtn.setText("购票");
		bookingBtn.addActionListener(actionListener);
		bookingBtn.setActionCommand("bookingBtn");
		
		// 实例退改签按钮
		alterBtn = new CustomButton(120, 520, CustomButton.LEFT);
		alterBtn.setText("退票/改签");
		alterBtn.addActionListener(actionListener);
		alterBtn.setActionCommand("alterBtn");

		// 实例化值机按钮
		checkinBtn = new CustomButton(520, 460, CustomButton.RIGHT);
		checkinBtn.setText("值机");
		checkinBtn.addActionListener(actionListener);
		checkinBtn.setActionCommand("checkinBtn");
		
		// 实例退出按钮
		exitBtn = new CustomButton(520, 520, CustomButton.RIGHT);
		exitBtn.setText("退出系统");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JLabel welcomelabel = new JLabel("欢迎使用在线购票系统!");
		welcomelabel.setBounds(30, 560, 500, 30);

		// 转换日期显示格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JLabel time = new JLabel(df.format(new Date(System.currentTimeMillis())));
		time.setBounds(660, 10, 200, 30);

		Timer timeAction = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long timemillis = System.currentTimeMillis();

				time.setText(df.format(new Date(timemillis)));
			}
		});
		timeAction.start();
		this.getContentPane().add(AddFlight);
		this.getContentPane().add(findFlight);
		this.getContentPane().add(bookingBtn);
		this.getContentPane().add(alterBtn);
		this.getContentPane().add(checkinBtn);
		this.getContentPane().add(exitBtn);
		this.getContentPane().add(welcomelabel);
		this.getContentPane().add(time);
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setTitle("在线购票系统");
		
	}
}
