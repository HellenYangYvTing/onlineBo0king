package com.booking.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.booking.constants.Constants;
import com.booking.entity.Flight;
import com.booking.entity.Ticket;
import com.booking.service.BookingService;
import com.booking.service.impl.BookingServiceImpl;

import sun.swing.DefaultLookup;

public class FlightListView extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame = null;
    private boolean modal = false;
    private JTextField flightNo;
    private Ticket ticket;
    private JButton choose;
    private String startTime;        //此处增加起飞时间字符串

    public FlightListView() {
        this(null, false);
    }

    public FlightListView(JFrame frame) {
        this(frame, false);
    }

    public FlightListView(JFrame frame, boolean modal) {
        this(frame, modal, "",null,null);
    }

    public FlightListView(JFrame frame, boolean modal, String title, JTextField flightNo, Ticket ticket) {
        super(title);
        this.frame = frame;
        this.modal = modal;
        this.flightNo = flightNo;
        this.ticket = ticket;
        this.init();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
        if(modal) {
        	frame.setEnabled(false);//仅把窗口设置为不可用
        }
	     // 航班列表表格标题
		Vector columnNames = new Vector();
		columnNames.add("航班号");
		columnNames.add("始发地");
		columnNames.add("目的地");
		columnNames.add("始发时间");
		columnNames.add("终到时间");
		columnNames.add("航空公司");
		columnNames.add("机型");
		columnNames.add("票价");
		DefaultTableModel tableModel = new DefaultTableModel(getTableData(), columnNames);
		// 创建指定列名和数据的表格
		//单击JTable某行背景改变颜色
		
		@SuppressWarnings("serial")
		JTable table = new JTable(tableModel){
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		        Component comp = super.prepareRenderer(renderer,row,column);
		        Point p = getMousePosition();
		        if(p!=null){
		            int rowUnderMouse = rowAtPoint(p);//
		            if(rowUnderMouse == row){//选中行，背景设置为橙色
		                  comp.setBackground(Color.ORANGE);
		           }else{//没有选中的行，颜色还原
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
		//设置填充高度
		table.setEnabled(false);
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
					FlightListView.this.flightNo.setText(model.getValueAt(row, 0).toString());
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
		// 创建显示表格的滚动面板
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 600, 300);
		
		// 实例化选好按钮
		choose = new CustomButton(490, 380, CustomButton.LEFT);
		choose.setText("选好");
		// 将滚动面板添加到边界布局的中间
		this.getContentPane().add(scrollPane, BorderLayout.NORTH);
		this.getContentPane().add(choose);
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断是否选中行，没有选中则给予提示，否则打开选座页面
				if (orderList.size() == 0) {
					JOptionPane.showMessageDialog(FlightListView.this, "您未选择航班！",
							"提示信息", JOptionPane.ERROR_MESSAGE);
				}else {
					FlightListView.this.setVisible(false);
					FlightListView.this.dispose();
					frame.setEnabled(true);
					frame.requestFocus();
					// 设置父窗口航班号文本框value为选中航班号
					FlightListView.this.flightNo.setText(orderList.get(0));
					startTime=orderList.get(4);     //在所选行里面筛选出出行时间
				}
				
			}
		});
     
    }

    public void windowOpened(WindowEvent windowEvent) {
    }
    public String readStartTime() {       //返回起飞时间代码
    	return startTime;
    }

    public void windowClosing(WindowEvent windowEvent) {
    	// 关闭对话框，激活父窗口
        if(modal) {
        	frame.setEnabled(true);
        	this.flightNo.setText(FlightListView.this.flightNo.getText());
        }
    }   
    
	/**
	 * @Title: getTableData
	 * @Description: 获取航班信息table数据
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector getTableData() {
		Vector data = new Vector();
		// 根据始发地和目的地读取相应航班信息
		BookingService bookingService = new BookingServiceImpl();
		Map<String, Object> resultMap = bookingService.readFlightData(ticket.getStartAddress(),ticket.getEndAddress());//ticket为参数传入
		List<Flight> flights = (List<Flight>)resultMap.get(Constants.RESULT_KEY_DATA);
		// 如果航班信息为空，直接返回空Vector
		if (flights == null || flights.isEmpty()) {
			return data;
		}
		for (int i = 0; i < flights.size(); i++) {
			Flight t = flights.get(i);
			Vector vector = new Vector();
			vector.add(t.getFlightNo());
			vector.add(t.getStartAddress());
			vector.add(t.getEndAddress());
			vector.add(t.getStartTime());
			vector.add(t.getArrivedTime());
			vector.add(t.getAirLineName());
			vector.add(t.getPlaneType());
			vector.add(t.getPrice());
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
