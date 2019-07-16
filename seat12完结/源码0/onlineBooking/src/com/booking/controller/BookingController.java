/**  
 * @Title: BookingController.java
 * @Package com.atm.controller
 * @author 姜向阳
 * @date 2018年3月13日
 * @version V1.0  
 */
package com.booking.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.booking.view.AddFlightLsitView;
import com.booking.view.BookingView;
import com.booking.view.CheckAllFlightView;
import com.booking.view.CheckinView;
import com.booking.view.FlightListView;
import com.booking.view.MainView;
import com.booking.view.OrderView;
import com.booking.view.PayView;
import com.booking.view.RefundOrAlterView;

import freeseawind.lf.LittleLuckLookAndFeel;

/**
 * @ClassName: BookingController
 * @Description: 在线订票系统主控制器
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class BookingController implements ActionListener {

	/**
	 * @Fields mainView : 主界面
	 */
	private MainView mainView;
	/**
	 * @Fields bookingView : 购票界面
	 */
	private BookingView bookingView;
	/**
	 * @Fields refundOrAlterView : 退票/改签界面
	 */
	private RefundOrAlterView refundOrAlterView;
	/**
	 * @Fields checkinView : 值机界面
	 */
	private CheckinView checkinView;
	/**
	 * @Fields PayView : 支付界面
	 */
	private PayView payView;
	/**
	 * @Fields OrderView : 订单界面
	 */
	private OrderView orderView;
	private CheckAllFlightView dialog;
	private AddFlightLsitView addFlightLsitView;
	public BookingController() {
		showMainView();
	}

	/*
	 * (非 Javadoc)
	 * 
	 * 
	 * @param e
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取点击事件发生的按钮上的ActionCommand字符串
		String actionCommand = e.getActionCommand();
		// 根据不同按钮点击，显示不同窗口
		switch (actionCommand) {
		case "addFlight":
			// 显示添加机票票窗口
			 addFlightLsitView=new AddFlightLsitView();
			 addFlightLsitView.setVisible(true);
			break;
		case "findFlight":
			// 显示查看所有机票窗口
			dialog = new CheckAllFlightView("航班信息");
			dialog.setSize(600,520);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			break;
		// 点击“购票”按钮
		case "bookingBtn":
			// 显示购票窗口
			bookingView = new BookingView(BookingController.this);
			bookingView.setVisible(true);
			break;
			// 点击“退票/改签”按钮
		case "alterBtn":
			// 显示值机窗口
			refundOrAlterView = new RefundOrAlterView(this);
			refundOrAlterView.setVisible(true);
			break;
		// 点击“值机”按钮
		case "checkinBtn":
			// 显示值机窗口
			checkinView = new CheckinView(this);
			checkinView.setVisible(true);
			break;
		// 购票窗口点击“返回”按钮
		case "backToMainView":
			JButton btn = (JButton) e.getSource();
			JFrame f = (JFrame) btn.getParent().getParent().getParent().getParent();
			f.setVisible(false);
			f.dispose();
			mainView.setVisible(true);
			break;
		// 购票窗口点击“确认”按钮
		/*case "paySubmit":
			// 关闭购票窗口
			bookingView.dispose();
			// 显示支付窗口
			payView = new PayView(this,null);
			payView.setVisible(true);
			break;*/
		// 支付窗口点击“返回”按钮
		case "backToPayView":
			JButton backPay = (JButton) e.getSource();
			JFrame pay = (JFrame) backPay.getParent().getParent().getParent().getParent();
			pay.setVisible(false);
			pay.dispose();
			bookingView = new BookingView(this);
			bookingView.setVisible(true);
			break;
		/*// 支付窗口点击“支付”按钮
		case "doPay":
			// 关闭支付窗口
			payView.setVisible(false);
			mainView.setVisible(true);
			break;*/
		// 值机窗口点击“确认”按钮
		/*case "search":
			// 关闭支付窗口
			checkinView.setVisible(false);
			// 显示主窗口
			orderView = new OrderView(this,null);
			orderView.setVisible(true);
			break;*/
		// 值机窗口点击“返回”按钮
		case "toMainView":
			JButton backToMainbtn = (JButton) e.getSource();
			JFrame main = (JFrame) backToMainbtn.getParent().getParent().getParent().getParent();
			main.setVisible(false);
			main.dispose();
			mainView.setVisible(true);
			break;
		// 订单窗口点击“返回”按钮
		case "homepage":
			JButton ToMainbtn = (JButton) e.getSource();
			JFrame toMain = (JFrame) ToMainbtn.getParent().getParent().getParent().getParent();
			toMain.setVisible(false);//设置为主窗口不可见
			toMain.dispose();
			mainView.setVisible(true);
			break;
		}
	}

	/**
	 * @Title: showMainView
	 * @Description: 显示主界面
	 */
	private void showMainView() {
		mainView = new MainView(this);
		mainView.setVisible(true);
	}

	/**
	 * @Title: main
	 * @Description: 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		// 设置swing界面样式风格
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(LittleLuckLookAndFeel.class.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new BookingController();
			}
		});
	}
}
