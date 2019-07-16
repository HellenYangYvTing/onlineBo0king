/**  
 * @Title: CustomButton.java
 * @Package com.atm.view
 * @author 姜向阳
 * @date 2018年3月20日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * @ClassName: CustomButton
 * @Description: 自定义按钮
 * @author 姜向阳
 * @date 2018年3月20日
 * @since JDK 1.8
 */
public class CustomButton2 extends JButton {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6646240985553770024L;
	
	public static ImageIcon icon = new ImageIcon("img/01.png");
	public static ImageIcon unEnableIcon = new ImageIcon("img/11.png");
	public static ImageIcon clickedIcon = new ImageIcon("img/21.png");
	private MouseAdapter mouseAdapter;
	
	/**
	 * 选中的座位号
	 */
	public static String clickStr;
	
	/**
	 * 创建一个新的实例 CustomButton.
	 *
	 * @param x x坐标
	 * @param y y坐标
	 * @param direction 按钮方向
	 */
	public CustomButton2(int x, int y, String id) {
		super();
		
		this.setIcon(icon);
		// 不绘制边框
		this.setBorderPainted(false);
		// 不绘制按钮区域
		this.setContentAreaFilled(false);
		this.setActionCommand(id);
		
		mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (CustomButton2.this.getIcon() == clickedIcon) {
					CustomButton2.this.setIcon(icon);
					return;
				}
				isCheck(true, true, clickedIcon,"randomClick",null);
			}
		};
		
		this.addMouseListener(mouseAdapter);
		// 文字位置
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
	}

	public void isCheck(boolean isCheck, boolean isEnable, ImageIcon icon,String isSelf,String selfSeatNo) {
		if (isCheck) {
			this.setIcon(clickedIcon);
			// 设置选中的座位号
			clickStr = this.getActionCommand();
		}
		if (!isEnable) {
			this.setIcon(icon);
			this.removeMouseListener(mouseAdapter);
		}
		// 判断如果是自选按钮和随机分配则将已选中的位置设置为未选
		if (isSelf.equals("randomClick")){
			cleanClick(selfSeatNo,clickStr);
		}
	}
	

	private void cleanClick(String selfSeatNo,String clickStr) {
		Component[] components = this.getParent().getComponents();
		for (Component component : components) {
			if (component instanceof CustomButton2) {
				CustomButton2 button = (CustomButton2) component;
				String actionCommand = button.getActionCommand();
				// 判断将已选的座位号设置为未选
				if (button.getIcon() == CustomButton2.clickedIcon && !actionCommand.equals(clickStr)){
					button.setIcon(CustomButton2.icon);
				}
			}
		}
	}
	
}
