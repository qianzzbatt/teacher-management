package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.TeacherDAO;


public class ShowView {
	private JFrame frame;
	private final int maxPageNum = 99;

	private JPanel jPanelSouth, jPanelCenter;
	private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre;
	public static JLabel currPageNumJLabel;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;

	public static String[] column = {  "id", "姓名", "工号","年龄","性别", "部门","工资", "电话" };
	public static int currPageNum = 1;

	public ShowView() {
		init();
	}

	private void init() {
	
		frame = new JFrame();

		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));

		// init jTable
		String[][] result =TeacherDAO.list(currPageNum);
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);

		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 5));

		jButtonFirst = new JButton("首页");
		jButtonFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = 1;
				String[][] result =TeacherDAO.list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText("页"+ currPageNum+ "/99");
			}
		});
		jButtonPre = new JButton("上页");
		jButtonPre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum--;
				if (currPageNum <= 0) {
					currPageNum = 1;
				}
				String[][] result = TeacherDAO.list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText("页" + currPageNum+"/99");
			}
		});
		jButtonNext = new JButton("下页");
		jButtonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum++;
				if (currPageNum > maxPageNum) {
					currPageNum = maxPageNum;
				}
				String[][] result =TeacherDAO.list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText("页" + currPageNum+"/99");
			}
		});
		jButtonLast = new JButton("最后一页");
		jButtonLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = maxPageNum;
				String[][] result = TeacherDAO.list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText("页" + currPageNum+"/99");
			}
		});

		currPageNumJLabel = new JLabel("页" + currPageNum +"/99");
		currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);

		jPanelSouth.add(jButtonFirst);
		jPanelSouth.add(jButtonPre);
		jPanelSouth.add(currPageNumJLabel);
		jPanelSouth.add(jButtonNext);
		jPanelSouth.add(jButtonLast);

	
		frame.add(jPanelCenter, BorderLayout.CENTER);
		frame.add(jPanelSouth, BorderLayout.SOUTH);

		frame.setBounds(400, 200, 750, 340);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		jTable.setRowHeight(20);
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);	
		secondColumn.setPreferredWidth(60);
		secondColumn.setMaxWidth(60);
		secondColumn.setMinWidth(60);
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(100);
		thirdColumn.setMaxWidth(100);
		thirdColumn.setMinWidth(100);
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(50);
		fourthColumn.setMaxWidth(50);
		fourthColumn.setMinWidth(50);
		TableColumn seventhColumn = jTable.getColumnModel().getColumn(6);
		seventhColumn.setPreferredWidth(120);
		seventhColumn.setMaxWidth(120);
		seventhColumn.setMinWidth(120);
		TableColumn eightColumn = jTable.getColumnModel().getColumn(7);
		eightColumn.setPreferredWidth(120);
		eightColumn.setMaxWidth(120);
		eightColumn.setMinWidth(120);
	}
}
