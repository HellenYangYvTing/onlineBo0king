/**  
 * @Title: LoginView.java
 * @Package com.atm.view
 * @author 姜向阳
 * @date 2018年3月20日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

/**
 * @ClassName: BaseFrame
 * @Description: 基础Frame类，封装通用界面设置及拖拽事件
 * @author 姜向阳
 * @date 2018年3月20日
 * @since JDK 1.8
 */
public class BaseFrame extends JFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 551155999130631688L;

	/**
	 * @Fields isMoved : 是否可拖拽窗口标记 true：可拖拽 false：不可拖拽
	 */
	private boolean isMoved;
	/**
	 * @Fields pre_point : 鼠标按下去时，光标相对当前frame所在位置
	 */
	private Point prePoint;
	/**
	 * @Fields end_point : 推拽后窗口相对显示器位置
	 */
	private Point endPoint;

	/**
	 * 创建一个新的实例 BaseFrame.
	 *
	 */
	public BaseFrame() {
		// 设置窗口大小
		this.setSize(800, 600);
		// 隐藏边框
		this.setUndecorated(true);
		// 设置居中
		this.setLocationRelativeTo(null);
		// 设置是否可以改变大小
		this.setResizable(false);
		// 用户单击窗口的关闭按钮时程序执行的操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 窗体布局为空
		this.getContentPane().setLayout(null);
		
		setDragable();
	}

	/**
	 * @Title: setDragable
	 * @Description: 拖拽窗口方法
	 */
	private void setDragable() {
		// 鼠标事件
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 鼠标释放后，不能再拖拽
				isMoved = false;
				// 鼠标释放后，将光标变回默认箭头
				BaseFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// 鼠标按下并且移动
				isMoved = true;
				// 得到按下去的位置
				prePoint = new Point(e.getX(), e.getY());
				// 将光标状态修改为移动状态
				BaseFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		// 拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// 判断是否可以拖拽
				if (isMoved) {
					// BaseFrame.this.getLocation().x 是当前frame的最左边在整个屏幕上的x轴位置
					// e.getX() 鼠标，在当前frame中的x轴位置（随着鼠标拖动，位置在变）
					// pre_point.x 鼠标按下去时，在当前frame中的x轴位置
					endPoint = new Point(BaseFrame.this.getLocation().x + e.getX() - prePoint.x,
							BaseFrame.this.getLocation().y + e.getY() - prePoint.y);
					BaseFrame.this.setLocation(endPoint);
				}
			}
		});
	}

}
