package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import dao.TeacherDAO;

public class MainView {
	private JFrame frame;
	private JButton addbtn;
	private JButton deletebtn;
	private JButton serachbtn;
	private JButton showbtn;
	private JButton updatebtn;
	private JButton exitbtn;
	public static int currPageNum = 1;
	private DefaultTableModel myTableModel;
	public static String[] column = {  "id", "����", "����","����","�Ա�", "����","����", "�绰" };
	public static JTable jTable;
	public MainView() {
		init();
	}

	public void init() {
		
		String[][] result = TeacherDAO.list(currPageNum);
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		ShowView.initJTable(jTable, result);
		
		frame = new JFrame();
		frame.setTitle("������");
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		

		
		addbtn = new JButton("��ӽ�ʦ��Ϣ");
		addbtn.setBounds(60,20, 150, 30);
		frame.getContentPane().add(addbtn);
		
		deletebtn = new JButton("ɾ����ʦ��Ϣ");
		deletebtn.setBounds(60,70, 150, 30);
		frame.getContentPane().add(deletebtn);
		
		serachbtn = new JButton("��ѯ��ʦ��Ϣ");
		serachbtn.setBounds(60,120, 150, 30);
		frame.getContentPane().add(serachbtn);
		
		showbtn = new JButton("��ʾ��ʦ��Ϣ");
		showbtn.setBounds(60,170, 150, 30);
		frame.getContentPane().add(showbtn);
		
		updatebtn = new JButton("�޸Ľ�ʦ��Ϣ");
		updatebtn.setBounds(60,220, 150, 30);
		frame.getContentPane().add(updatebtn);
		
		exitbtn = new JButton("�˳�");
		exitbtn.setBounds(60,270, 150, 30);
		frame.getContentPane().add(exitbtn);
		
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Addview();
			}
		});
		
		showbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowView();
			}
		});
		
		deletebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteView();
			}
		});
		
		serachbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchView();
			}
		});
		
		updatebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateView();
			}
		});
		
		exitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
}
