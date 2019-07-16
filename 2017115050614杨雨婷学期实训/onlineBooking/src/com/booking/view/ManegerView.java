package com.booking.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import com.booking.util.ViewBackgroundUtil;

public class ManegerView extends BaseFrame {

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

	public ManegerView(ActionListener actionListener) {
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		// 始发地标签
		JLabel noteLabel = new JLabel("请输入管理员密码");
		noteLabel.setBounds(295, 60, 240, 33);
		
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
					JOptionPane.showMessageDialog(ManegerView.this, "请输入管理员密码！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					
						if (!"123456".equals(inputItem)) {
							JOptionPane.showMessageDialog(ManegerView.this, "管理员密码输入错误，请重新输入！",
									"提示信息", JOptionPane.ERROR_MESSAGE);
							return;
						}
					
					
					// 传递订单号或身份证号到订单窗口
					ManegerView.this.setVisible(false);
					AddFlightLsitView addFlightLsitView = new AddFlightLsitView();
					addFlightLsitView.setVisible(true);
				}
				
			}
		});
		
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
