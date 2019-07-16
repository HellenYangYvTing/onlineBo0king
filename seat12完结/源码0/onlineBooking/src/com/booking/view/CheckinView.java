/**  
 * @Title: OpenAccountView.java
 * @Package com.atm.view
 * @author 姜向阳
 * @date 2018年3月13日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.booking.util.ValidateDataUtil;
import com.booking.util.ViewBackgroundUtil;

/**
 * @ClassName: OpenAccountView
 * @Description: 开户界面
 * @author 姜向阳
 * @date 2018年3月13日
 * @since JDK 1.8
 */
public class CheckinView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2870945371327830406L;

	private ActionListener actionListener;

	/**
	 * @Fields searchCondition : 搜索条件
	 */
	private JTextField searchCondition;
	/**
	 * @Fields confirmBtn : 确认按钮
	 */
	private JButton confirmBtn;
	/**
	 * @Fields backBtn : 返回按钮
	 */
	private JButton backBtn;

	public CheckinView(ActionListener actionListener) {
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		// 始发地标签
		JLabel noteLabel = new JLabel("请输入您购票时的订单号或乘机人身份证号：");
		noteLabel.setBounds(295, 60, 280, 33);
		
		// 搜索条件文本框
		searchCondition = new JTextField();
		searchCondition.setBounds(295, 100, 200, 33);
		
		// 实例化确认按钮
		confirmBtn = new CustomButton(480, 460, CustomButton.RIGHT);
		confirmBtn.setText("确认");
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取输入的订单号或身份证号
				String inputItem =  searchCondition.getText();
				if ("".equals(inputItem)) {
					JOptionPane.showMessageDialog(CheckinView.this, "请输入订单号或乘机人身份证号！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					
				} else {
					// 验证输入的身份证号是否合法
					boolean identityNo = ValidateDataUtil.getValidateDataUtil().validateIdentityNo(inputItem);
					// 验证输入的订单号是否合法
					boolean orderNo = ValidateDataUtil.getValidateDataUtil().validateIdentityNo(inputItem);
					// 判断输入的订单号或身份证号是否合法
					if (inputItem.length()  < 13) {
						if (!orderNo || !identityNo) {
							JOptionPane.showMessageDialog(CheckinView.this, "订单编号或身份证号输入错误，请重新输入！",
									"提示信息", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					if (inputItem.length()  > 18) {
						if (!identityNo) {
							JOptionPane.showMessageDialog(CheckinView.this, "订单编号或身份证号输入错误，请重新输入！",
									"提示信息", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					// 传递订单号或身份证号到订单窗口
					CheckinView.this.setVisible(false);
					OrderView orderView = new OrderView(actionListener,inputItem);
					orderView.setVisible(true);
				}
			}
		});
		//confirmBtn.setActionCommand("search");
		
		// 实例化返回按钮
		backBtn = new CustomButton(180, 460, CustomButton.LEFT);
		backBtn.setText("返回");
		backBtn.addActionListener(actionListener);
		backBtn.setActionCommand("toMainView");

		this.getContentPane().add(noteLabel);
		this.getContentPane().add(searchCondition);
		this.getContentPane().add(backBtn);
		this.getContentPane().add(confirmBtn);
		
		this.setLocationRelativeTo(null);
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}
}
