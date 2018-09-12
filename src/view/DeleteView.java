package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import dao.TeacherDAO;
import model.Teacher;



public class DeleteView {
	private JFrame frame;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField name, sno; // 根据工号+学号删除学生

	public DeleteView() {
		init();
	}

	private void init() {
		
		frame = new JFrame();
		
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		
		jPanelCenter.add(new JLabel("姓名"));
		name = new JTextField();
		jPanelCenter.add(name);
		
		jPanelCenter.add(new JLabel("工号"));
		sno = new JTextField();
		jPanelCenter.add(sno);
		
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		
		deleteButton = new JButton("删除");
		jPanelSouth.add(deleteButton);
		
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
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Teacher teacher = new Teacher();
					buildTeacher(teacher);
					boolean isSuccess =TeacherDAO.delete(teacher);
					if (isSuccess) {
						System.out.println("Delete success");
						setEmpty();
						if (ShowView.currPageNum < 0 || ShowView.currPageNum > 99) {
							ShowView.currPageNum = 1;
						}
						String[][] result = TeacherDAO.list(ShowView.currPageNum);
						ShowView.initJTable(MainView.jTable, result);
						JOptionPane.showMessageDialog(null, "操作成功", "操作成功",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "找不到教师","失败",JOptionPane.WARNING_MESSAGE);  
				}
				else
					JOptionPane.showMessageDialog(null,"请把空填写完整","操作失败",JOptionPane.WARNING_MESSAGE);
			}
		});
}
	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildTeacher(Teacher teacher) {
		teacher.setName(name.getText());
		teacher.setSno(sno.getText());
	}

	private void setEmpty() {
		name.setText("");
		sno.setText("");
	}
}


