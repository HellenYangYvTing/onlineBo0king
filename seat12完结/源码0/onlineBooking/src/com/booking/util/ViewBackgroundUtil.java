/**  
 * @Title: ViewBackgroundUtil.java
 * @Package com.atm.util
 * @author 姜向阳
 * @date 2018年3月20日
 * @version V1.0  
 */
package com.booking.util;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @ClassName: ViewBackgroundUtil
 * @Description: 设置界面背景工具类
 * @author 姜向阳
 * @date 2018年3月20日
 * @since JDK 1.8
 */
public class ViewBackgroundUtil {

	/**
	 * @Title: setBG
	 * @Description: 设置界面背景图片
	 * @param view
	 * @param image
	 */
	public static void setBG(JFrame view, String image) {
		// 设置背景图片
		ImageIcon icon = new ImageIcon(image);
		JLabel bgLabel = new JLabel(icon);
		view.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		// 设置背景标签的位置
		bgLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		Container contentPane = view.getContentPane();
		((JPanel) contentPane).setOpaque(false);
	}
}
