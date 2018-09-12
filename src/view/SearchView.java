package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



public class SearchView {
	public static JLabel currPageNumJLabel;
	public static int currPageNum = 1;
	private JPanel jPanelSouth, jPanelCenter;
	private JFrame frame;
	private JButton searchbtn,exitButton;
	private static JTextField name;
	public static JTable jTable;
	static String[] column = { "id", "姓名", "工号","年龄","性别", "部门","工资", "电话" };
	
	public SearchView() {
		init();
	}
	
	public void init() {
		frame = new JFrame();

		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(2, 2));
		
		jPanelCenter.add(new JLabel("姓名"));
		name = new JTextField();
		jPanelCenter.add(name);
		
//		jPanelCenter.add(new JLabel("学号"));
//		sno = new JTextField();
//		jPanelCenter.add(sno);
		
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		
		searchbtn = new JButton("查询");
		jPanelSouth.add(searchbtn);
		
		exitButton = new JButton("退出");
		jPanelSouth.add(exitButton);

		frame.getContentPane().add(jPanelCenter, BorderLayout.CENTER);
		frame.getContentPane().add(jPanelSouth, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(470, 250, 400, 130);
		frame.setResizable(false);
		frame.setVisible(true);
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		searchbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchView_2();
				SearchView_2.find();
			}
		});
	}

	public static JTextField getName() {
		return name;
	}

	@SuppressWarnings("static-access")
	public void setName(JTextField name) {
		this.name = name;
	}

}
