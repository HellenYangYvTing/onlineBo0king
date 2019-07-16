/**  
 * @Title: CustomButton.java
 * @Package com.atm.view
 * @author 姜向阳
 * @date 2018年3月20日
 * @version V1.0  
 */
package com.booking.view;

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
public class CustomButton extends JButton {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6646240985553770024L;
	/**
	 * @Fields RIGHT : 按钮方向-右侧
	 */
	public static final String RIGHT = "_right";
	/**
	 * @Fields LEFT : 按钮方向-左侧
	 */
	public static final String LEFT = "_left";
	
	/**
	 * 创建一个新的实例 CustomButton.
	 *
	 * @param x x坐标
	 * @param y y坐标
	 * @param direction 按钮方向
	 */
	public CustomButton(int x, int y, String direction) {
		super();
		ImageIcon icon = new ImageIcon("img/btn"+direction+".png");
		ImageIcon rolloverIcon = new ImageIcon("img/btn_in"+direction+".png");
		this.setIcon(icon);
		// 不绘制边框
		this.setBorderPainted(false);
		// 不绘制按钮区域
		this.setContentAreaFilled(false);
		// 设置鼠标经过图片
		this.setRolloverIcon(rolloverIcon);
		// 文字位置
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
	}

	

}
