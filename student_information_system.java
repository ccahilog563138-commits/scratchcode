package practice2;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class student_information_system extends JFrame{
	JLabel lblname,lblcourse, lblsection;
	JTextField txtname, txtcourse,txtsection;
	JButton btnadd,btnupdate, btndelete, btnclear;
	DefaultTableModel model;
	JTable table;
	JScrollPane pane;
	
	public student_information_system() {
		
		setTitle("Student Information System");
		setSize(540,500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		lblname = new JLabel ("Name");
		add(lblname).setBounds(20, 20, 100, 25);
		txtname = new JTextField();
		add(txtname).setBounds(20, 40, 120, 25);
		lblcourse = new JLabel("Course");
		add(lblcourse).setBounds(160,20, 100, 25);
		txtcourse = new JTextField();
		add(txtcourse).setBounds(160,40,120,25);
		lblsection = new JLabel ("Section");
		add(lblsection).setBounds(300, 20, 100,25);
		txtsection = new JTextField();
		add(txtsection).setBounds(300, 40, 120,25);
		
		btnadd = new JButton ("Add");
		add(btnadd).setBounds(30, 80, 80, 25);
		btnupdate = new JButton("Update");
		add(btnupdate).setBounds(120, 80 , 80,25);
		btndelete = new JButton("Delete");
		add(btndelete).setBounds(210, 80, 80, 25);
		btnclear = new JButton("Clear");
		add(btnclear).setBounds(300, 80, 80, 25);
		
		
		
		
	}
	
	
	
	
	public static void main (String [] args) {
		new student_information_system();
	}

}
