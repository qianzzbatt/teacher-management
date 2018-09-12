package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



import dao.TeacherDAO;
import model.Teacher;

public class Addview {
	private JFrame frame;
	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField name, sno, department, age, tel, sex,wage;
	
	public Addview() {
		init();
	}
	
	public void init() {
		
		frame = new JFrame();
		
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(7, 2));
		
		jPanelCenter.add(new JLabel("����"));
		name = new JTextField();
		jPanelCenter.add(name);
		
		jPanelCenter.add(new JLabel("����"));
		sno = new JTextField();
		jPanelCenter.add(sno);
		
		jPanelCenter.add(new JLabel("����"));
		age = new JTextField();
		jPanelCenter.add(age);
		
		jPanelCenter.add(new JLabel("�Ա�"));
		sex = new JTextField();
		jPanelCenter.add(sex);
		
		jPanelCenter.add(new JLabel("����"));
		department = new JTextField();
		jPanelCenter.add(department);
		
		jPanelCenter.add(new JLabel("����"));
		wage = new JTextField();
		jPanelCenter.add(wage);

		jPanelCenter.add(new JLabel("�绰"));
		tel = new JTextField();
		jPanelCenter.add(tel);
		
//		jPanelCenter.add(new JLabel("-------------------------------------------------"));
//		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton("���");
		jPanelSouth.add(addButton);
		
		exitButton = new JButton("�˳�");
		jPanelSouth.add(exitButton);
		
		frame.add(jPanelCenter, BorderLayout.CENTER);
		frame.add(jPanelSouth, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(470, 200, 400, 270);
		frame.setResizable(false);
		frame.setVisible(true);
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Teacher teacher = new Teacher();
					buildTeacher(teacher);
					boolean isSuccess =TeacherDAO.add(teacher);
					if (isSuccess) {
						setEmpty();
						if (ShowView.currPageNum < 0 || ShowView.currPageNum > 99) {
							ShowView.currPageNum = 1;
						}
						String[][] result =TeacherDAO.list(ShowView.currPageNum);
						ShowView.initJTable(MainView.jTable, result);
						JOptionPane.showMessageDialog(null, "�����ɹ�", "�����ɹ�",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null,"�����Ѵ���", "���ʧ��",JOptionPane.WARNING_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "�뽫����д����", "���ʧ��",JOptionPane.WARNING_MESSAGE);  
			}
		});
	}
	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(department.getText())
				|| "".equals(sex.getText()) || "".equals(wage.getText()) || "".equals(tel.getText())
				|| "".equals(age.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildTeacher(Teacher teacher) {
		teacher.setDepartment(department.getText());
		teacher.setAge(age.getText());
		teacher.setWage(wage.getText());
		teacher.setName(name.getText());
		teacher.setSno(sno.getText());
		teacher.setTel(tel.getText());
		teacher.setSex(sex.getText());
	}

	private void setEmpty() {
		name.setText("");
		sno.setText("");
		department.setText("");
		sex.setText("");
		age.setText("");
		wage.setText("");
		tel.setText("");
	}
}
