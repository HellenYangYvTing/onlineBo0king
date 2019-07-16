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
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.booking.constants.Constants;
import com.booking.entity.Ticket;
import com.booking.service.BookingService;
import com.booking.service.impl.BookingServiceImpl;
import com.booking.util.ViewBackgroundUtil;

import sun.swing.DefaultLookup;

/**
 * @ClassName: OrderView
 * @Description: 账单界面
 * @author 姜向阳
 * @date 2018年7月3日
 * @since JDK 1.8
 */
public class OrderView extends BaseFrame {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7489212337354945149L;

	private ActionListener actionListener;
	
	/**
	 * @Fields handleBtn : 办理按钮
	 */
	private JButton handleBtn;
	/**
	 * @Fields homepage : 返回
	 */
	private JButton homepage;
	
	/**
	 * @Fields orderNoOrIdentityNo : 前一窗口传递的搜索条件（订单号或身份证号）
	 */
	private String orderNoOrIdentityNo;
	
	
	public Boolean isSelect = false;
	
	public OrderView(ActionListener actionListener,String orderNoOrIdentityNo) {
		this.actionListener = actionListener;
		this.orderNoOrIdentityNo = orderNoOrIdentityNo;
		init();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		// 设置背景图片
		ViewBackgroundUtil.setBG(this, "img/bg2.jpg");

		// 提示文字,用文本域实现换行（/r/n控制换行）
		JLabel title = new JLabel("购票订单列表");
		title.setBounds(60, 20, 700, 33);

		// 表格标题
		Vector columnNames = new Vector();
		columnNames.add("订单号");
		columnNames.add("始发地");
		columnNames.add("目的地");
		columnNames.add("乘机人");
		columnNames.add("身份证号");
		columnNames.add("日期");
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
		homepage = new CustomButton(140, 510, CustomButton.LEFT);
		homepage.setText("返回");
		homepage.addActionListener(actionListener);
		homepage.setActionCommand("homepage");
		
		// 实例化办理按钮
		handleBtn = new CustomButton(490, 510, CustomButton.LEFT);
		handleBtn.setText("办理");
		handleBtn.addActionListener(actionListener);
		handleBtn.setActionCommand("handleBtn");

		this.getContentPane().add(title);
		// 将滚动面板添加到边界布局的中间
		this.getContentPane().add(scrollPane, BorderLayout.NORTH);
		this.getContentPane().add(homepage);
		this.getContentPane().add(handleBtn);
		
		this.setLocationRelativeTo(null);
		// 设置表格只能单选行
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
	    List<String> orderList =  new ArrayList<>();
    	table.addMouseListener(new MouseAdapter() {
    		private int rowUnderMouse = -1;
    		public void mouseClicked(MouseEvent e) {
    			// 清空选中行数据列表
    			orderList.clear();
    			// 获取所选中行号
    			int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
    			if(row == -1) {
    				return;
    			}
    			int numCols = table.getColumnCount();
    			// 判断获取选中行中所有数据
    			TableModel model = table.getModel();
				// 循环获取每列数据
				for (int i = 0; i < numCols; i++) {
					orderList.add(model.getValueAt(row, i).toString());
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
	    
	    // 点击办理按钮
	    handleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断是否选中行，没有选中则给予提示，否则打开选座页面
				if (orderList.size() == 0) {
					JOptionPane.showMessageDialog(OrderView.this, "请选择一条数据后办理！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
				}else {
					JButton ToSeatbtn = (JButton) e.getSource();
					JFrame toSeat = (JFrame) ToSeatbtn.getParent().getParent().getParent().getParent();
					toSeat.setVisible(false);
					toSeat.dispose();
					SeatView seatView = new SeatView(actionListener,orderList);
					seatView.setVisible(true);
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
