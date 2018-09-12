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
	private JTextField name, sno; // ���ݹ���+ѧ��ɾ��ѧ��

	public DeleteView() {
		init();
	}

	private void init() {
		
		frame = new JFrame();
		
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		
		jPanelCenter.add(new JLabel("����"));
		name = new JTextField();
		jPanelCenter.add(name);
		
		jPanelCenter.add(new JLabel("����"));
		sno = new JTextField();
		jPanelCenter.add(sno);
		
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		
		deleteButton = new JButton("ɾ��");
		jPanelSouth.add(deleteButton);
		
		exitButton = new JButton("�˳�");
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
						JOptionPane.showMessageDialog(null, "�����ɹ�", "�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "�Ҳ�����ʦ","ʧ��",JOptionPane.WARNING_MESSAGE);  
				}
				else
					JOptionPane.showMessageDialog(null,"��ѿ���д����","����ʧ��",JOptionPane.WARNING_MESSAGE);
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


