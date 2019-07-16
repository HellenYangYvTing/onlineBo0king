/**  
* @Title: PayView.java
 * @Package com.booking.view
 * @author 姜向阳
 * @date 2018年7月3日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;

import com.booking.util.ViewBackgroundUtil;

/**
 * @ClassName: PayView
 * @Description: 支付窗口
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class PayView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8142213655847297255L;

	private ActionListener actionListener;
	/**
	 * @Fields unionPayRadioBtn : 银联卡单选按钮
	 */
	private JRadioButton unionPayRadioBtn;
	/**
	 * @Fields aliPayRadioBtn : 支付宝单选按钮
	 */
	private JRadioButton aliPayRadioBtn;
	/**
	 * @Fields weChatRadioBtn : 微信单选按钮
	 */
	private JRadioButton weChatRadioBtn;
	/**
	 * @Fields confirmBtn : 确认按钮
	 */
	private JButton confirmBtn;
	/**
	 * @Fields backBtn : 返回按钮
	 */
	private JButton backBtn;
	/**
	 * @Fields selectedValue : 单选按钮选中值
	 */
	private String selectedValue;

	public PayView(ActionListener actionListener,String identityNo) {
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		// 单选按钮监听器
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton temp=(JRadioButton)e.getSource();
				if(temp.isSelected()){
					selectedValue=temp.getText();
				}
				
			}
		};
		// 支付方式单选按钮列表标签
		JLabel noteLabel = new JLabel("请选择付款方式：");
		noteLabel.setBounds(340, 60, 120, 33);
		
		// 实例化银联卡单选按钮
		unionPayRadioBtn = new JRadioButton("银联卡");
		unionPayRadioBtn.setSelected(true);
		selectedValue = "银联卡";
		unionPayRadioBtn.addActionListener(listener);
		unionPayRadioBtn.setBounds(340, 200, 90, 33);
		
		// 实例化支付宝单选按钮
		aliPayRadioBtn = new JRadioButton("支付宝");
		aliPayRadioBtn.addActionListener(listener);
		aliPayRadioBtn.setBounds(340, 240, 90, 33);
		
		// 实例化微信单选按钮
		weChatRadioBtn = new JRadioButton("微信");
		weChatRadioBtn.addActionListener(listener);
		weChatRadioBtn.setBounds(340, 280, 90, 33);
		
		// 将单选按钮编组
		ButtonGroup group = new ButtonGroup();
		group.add(this.unionPayRadioBtn);
		group.add(this.aliPayRadioBtn);
		group.add(this.weChatRadioBtn);
		
		
		// 实例化确认按钮
		confirmBtn = new CustomButton(480, 440, CustomButton.RIGHT);
		confirmBtn.setText("支付");
		confirmBtn.addActionListener(actionListener);
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedValue.equals("银联卡")) {
					JOptionPane.showMessageDialog(null, "银联卡支付成功！",
							"银联卡支付成功！", JOptionPane.ERROR_MESSAGE);
				} 
				if (selectedValue.equals("支付宝")) {
					JOptionPane.showMessageDialog(null, "支付宝支付成功！",
							"支付宝支付成功！", JOptionPane.ERROR_MESSAGE);
				} 
				if (selectedValue.equals("微信")) {
					JOptionPane.showMessageDialog(null, "微信支付成功！",
							"微信支付成功！", JOptionPane.ERROR_MESSAGE);
				} 
				// 关闭支付方式选择窗口
				PayView.this.setVisible(false);
				BookingView bookingView = new BookingView(actionListener);
				bookingView.setVisible(true);
			}
		});
		
		// 实例化返回按钮
		backBtn = new CustomButton(180, 440, CustomButton.LEFT);
		backBtn.setText("返回");
		backBtn.addActionListener(actionListener);
		backBtn.setActionCommand("backToPayView");

		this.getContentPane().add(noteLabel);
		this.getContentPane().add(unionPayRadioBtn);
		this.getContentPane().add(aliPayRadioBtn);
		this.getContentPane().add(weChatRadioBtn);
		this.getContentPane().add(backBtn);
		this.getContentPane().add(confirmBtn);
		
		this.setLocationRelativeTo(null);
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}
}
