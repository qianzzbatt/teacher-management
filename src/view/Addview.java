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
		
		jPanelCenter.add(new JLabel("姓名"));
		name = new JTextField();
		jPanelCenter.add(name);
		
		jPanelCenter.add(new JLabel("工号"));
		sno = new JTextField();
		jPanelCenter.add(sno);
		
		jPanelCenter.add(new JLabel("年龄"));
		age = new JTextField();
		jPanelCenter.add(age);
		
		jPanelCenter.add(new JLabel("性别"));
		sex = new JTextField();
		jPanelCenter.add(sex);
		
		jPanelCenter.add(new JLabel("部门"));
		department = new JTextField();
		jPanelCenter.add(department);
		
		jPanelCenter.add(new JLabel("工资"));
		wage = new JTextField();
		jPanelCenter.add(wage);

		jPanelCenter.add(new JLabel("电话"));
		tel = new JTextField();
		jPanelCenter.add(tel);
		
//		jPanelCenter.add(new JLabel("-------------------------------------------------"));
//		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton("添加");
		jPanelSouth.add(addButton);
		
		exitButton = new JButton("退出");
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
						JOptionPane.showMessageDialog(null, "操作成功", "操作成功",JOptionPane.INFORMATION_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null,"工号已存在", "添加失败",JOptionPane.WARNING_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "请将空填写完整", "添加失败",JOptionPane.WARNING_MESSAGE);  
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
