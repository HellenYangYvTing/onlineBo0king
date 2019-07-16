/**  
 * @Title: OrderView.java
 * @Package com.booking.view
 * @author 姜向阳
 * @date 2018年7月3日
 * @version V1.0  
 */
package com.booking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.booking.constants.Constants;
import com.booking.entity.Seat;
import com.booking.entity.Ticket;
import com.booking.service.BookingService;
import com.booking.service.impl.BookingServiceImpl;
import com.booking.util.ViewBackgroundUtil;

import sun.swing.DefaultLookup;

/**
 * @ClassName: OrderView
 * @Description: 退票/改签界面
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class AlterOrderView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7489212337354945149L;

	private ActionListener actionListener;
	
	/**
	 * @Fields handleBtn : 退票按钮
	 */
	private JButton refundBtn;
	
	/**
	 * @Fields handleBtn : 改签按钮
	 */
	private JButton alterBtn;

	/**
	 * @Fields homepage : 返回
	 */
	private JButton homepage;
	
	/**
	 * @Fields orderNoOrIdentityNo : 前一窗口传递的搜索条件（订单号或身份证号）
	 */
	private String orderNoOrIdentityNo;
	
	/**
	 * @Fields orderNo : 选择行的订单号
	 */
	private String orderNo;
	public AlterOrderView(ActionListener actionListener,String orderNoOrIdentityNo) {
		this.actionListener = actionListener;
		this.orderNoOrIdentityNo = orderNoOrIdentityNo;
		init();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");
		// 提示文字,用文本域实现换行（/r/n控制换行）
		JLabel title = new JLabel("待退订/改签订单列表");
		title.setBounds(60, 20, 700, 33);
		// 表格标题
		Vector columnNames = new Vector();
		columnNames.add("订单号");
		columnNames.add("始发地");
		columnNames.add("目的地");
		columnNames.add("乘机人");
		columnNames.add("身份证号");
		columnNames.add("日期");
		columnNames.add("座位");
		DefaultTableModel tableModel = new DefaultTableModel(getTableData(), columnNames);
		// 创建指定列名和数据的表格
		@SuppressWarnings("serial")
		JTable table = new JTable(tableModel){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component comp = super.prepareRenderer(renderer,row,column);
		        Point p = getMousePosition();
		        if(p!=null){
		            int rowUnderMouse = rowAtPoint(p);
		            if(rowUnderMouse == row){
		                  comp.setBackground(Color.ORANGE);
		           }else{
		                  comp.setBackground(DefaultLookup.getColor(this,ui,"Table.alternateRowColor"));
		           }
		        }
		        return comp;
		    }

		};
		table.addMouseMotionListener(new MyTableMouseMotionListener());
		// 表格排序
		RowSorter sorter = new TableRowSorter(tableModel);
		table.setRowSorter(sorter);
		table.setRowHeight(23);
		table.setAutoscrolls(true);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		// 创建显示表格的滚动面板
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(60, 60, 680, 420);

		// 实例化主页按钮
		homepage = new CustomButton(90, 510, CustomButton.LEFT);
		homepage.setText("返回");
		homepage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlterOrderView.this.setVisible(false);
				CheckinView checkinView = new CheckinView(actionListener);
				checkinView.setVisible(true);
				
			}
		});
		
		// 实例化退票按钮
		refundBtn = new CustomButton(330, 510, CustomButton.LEFT);
		refundBtn.setText("退票");
		refundBtn.addActionListener(actionListener);
		refundBtn.setActionCommand("refundBtn");
		
		// 实例化改签按钮
		alterBtn = new CustomButton(560, 510, CustomButton.LEFT);
		alterBtn.setText("改签");
		
		this.getContentPane().add(title);
		// 将滚动面板添加到边界布局的中间
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(homepage);
		this.getContentPane().add(refundBtn);
		this.getContentPane().add(alterBtn);
		
		this.setLocationRelativeTo(null);
		
		table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
	    List<String> orderList =  new ArrayList<>();
    	table.addMouseListener(new MouseAdapter() {
    		private int rowUnderMouse = -1;
    		public void mouseClicked(MouseEvent e) {
    			// 获取所选中行号
    			int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
    			// 获取所有行
    			int numRows = table.getRowCount();
    			// 获取所有列
    			int numCols = table.getColumnCount();
    			TableModel model = table.getModel();
    			// 循环所有行
    			for (int i = 0; i < numRows; i++) {
    				// 判断获取选中行中所有数据
    				if (i == row) {
    					// 循环获取每列数据
    					for (int j = 0; j < numCols; j++) {
    						orderList.add(model.getValueAt(i, j).toString());
    						AlterOrderView.this.orderNo = model.getValueAt(i, 0).toString();
    					}
    				}
    			}
    			JTable table = (JTable)e.getSource();
				
	             Point p = table.getMousePosition();
	             if(p != null){
	                  rowUnderMouse = table.rowAtPoint(p);
	                  if(rowUnderMouse >= 0){
	                          for(int i=0;i<table.getColumnCount();i++){
	                                 table.prepareRenderer(table.getCellRenderer(rowUnderMouse,i),rowUnderMouse,i);
	                                 if(rowUnderMouse != 0){
	                                       table.prepareRenderer(table.getCellRenderer(rowUnderMouse-1,i),rowUnderMouse-1,i);
	                                 }
	                                 if(rowUnderMouse != table.getRowCount()-1){
	                                       table.prepareRenderer(table.getCellRenderer(rowUnderMouse+1,i),rowUnderMouse+1,i);
	                                 }
	                          }
	                          table.repaint(table.getVisibleRect());
	                  }
	             }
    		}
    	});
	    
	    // 点击退票按钮
	    refundBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断是否选中行，没有选中给予提示
				if (orderList.size() == 0) {
					JOptionPane.showMessageDialog(AlterOrderView.this, "请选择一条数据后进行退票！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
				}else {
					JButton ToSeatbtn = (JButton) e.getSource();
					JFrame toSeat = (JFrame) ToSeatbtn.getParent().getParent().getParent().getParent();
					//添加确认提示框，会返回一个整数
					int doRefund = JOptionPane.showConfirmDialog(null, "退票将扣除票价的10%作为手续费，确定退票？", "提示", JOptionPane.YES_NO_OPTION);
					if (doRefund == JOptionPane.YES_OPTION) {
						// 后台退票处理
						BookingService bookingService = new BookingServiceImpl();
						Map<String,Object> resultMap = bookingService.deleteOrderInfo(AlterOrderView.this.orderNo);
						if (resultMap.get("resultData").equals(Constants.MSG_SUCCESS)) {
							JOptionPane.showMessageDialog(AlterOrderView.this, "退票成功！",
									"提示信息", JOptionPane.ERROR_MESSAGE);
						}
						toSeat.setVisible(false);
						toSeat.dispose();
					}
				}
			}
		});
	    // 点击改签按钮
	    alterBtn.addActionListener(new ActionListener() {
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// 判断是否选中行，没有选中给予提示
	    		if (orderList.size() == 0) {
	    			JOptionPane.showMessageDialog(AlterOrderView.this, "请选择一条数据后进行改签！",
	    					"提示信息", JOptionPane.ERROR_MESSAGE);
	    		}else {
	    			JButton ToSeatbtn = (JButton) e.getSource();
	    			JFrame toSeat = (JFrame) ToSeatbtn.getParent().getParent().getParent().getParent();
	    			//添加确认提示框，会返回一个整数
	    			int doAlter = JOptionPane.showConfirmDialog(null, "改签将加收票价的10%作为手续费，确定改签？", "提示", JOptionPane.YES_NO_OPTION);
	    			if (doAlter == JOptionPane.YES_OPTION) {
	    				// 显示输入对话框, 返回输入的改签日期
	                    String inputContent = JOptionPane.showInputDialog(null, "输入改签日期:", "YYYY-MM-dd");
	    				// 后台改签处理
	    				BookingService bookingService = new BookingServiceImpl();
	    				Map<String,Object> resultMap = bookingService.updateTravelDateInfo(AlterOrderView.this.orderNo, inputContent);
	    				if (resultMap.get("resultData").equals(Constants.MSG_SUCCESS)) {
	    					JOptionPane.showMessageDialog(AlterOrderView.this, "改签成功！",
	    							"提示信息", JOptionPane.ERROR_MESSAGE);
	    				}
	    				toSeat.setVisible(false);
	    				toSeat.dispose();
	    			}
	    		}
	    	}
	    });
		// 设置窗口无标题栏
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}

	/**
	 * @Title: getTableData
	 * @Description: 获取table数据
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector getTableData() {
		Vector data = new Vector();
		// 根据订单号或身份证号读取所有订单
		BookingService bookingService = new BookingServiceImpl();
		Map<String, Object> resultMap = bookingService.readData(orderNoOrIdentityNo);
		List<Ticket> tickets = (List<Ticket>)resultMap.get(Constants.RESULT_KEY_DATA);
		// 如果没有账单，直接返回空Vector
		if (tickets == null || tickets.isEmpty()) {
			return data;
		}
		for (int i = 0; i < tickets.size(); i++) {
			Ticket t = tickets.get(i);
			
			Vector vector = new Vector();
			vector.add(t.getOrderNo());
			vector.add(t.getStartAddress());
			vector.add(t.getEndAddress());
			vector.add(t.getUserName());
			vector.add(t.getIdentityNo());
			vector.add(t.getTravelDate());
			vector.add(t.getSeat());
			data.add(vector);
		}
		return data;
	}
	// 内部类座位鼠标行为监听器
	class MyTableMouseMotionListener extends MouseAdapter{

        private int rowUnderMouse = -1;

        public void mouseClicked(MouseEvent e){
             JTable table = (JTable)e.getSource();
             Point p = table.getMousePosition();
             if(p != null){
                  rowUnderMouse = table.rowAtPoint(p);
                  if(rowUnderMouse >= 0){
                          for(int i=0;i<table.getColumnCount();i++){
                                 table.prepareRenderer(table.getCellRenderer(rowUnderMouse,i),rowUnderMouse,i);
                                 if(rowUnderMouse != 0){
                                       table.prepareRenderer(table.getCellRenderer(rowUnderMouse-1,i),rowUnderMouse-1,i);
                                 }
                                 if(rowUnderMouse != table.getRowCount()-1){
                                       table.prepareRenderer(table.getCellRenderer(rowUnderMouse+1,i),rowUnderMouse+1,i);
                                 }
                          }
                          table.repaint(table.getVisibleRect());
                  }
             }
        }
}
}
