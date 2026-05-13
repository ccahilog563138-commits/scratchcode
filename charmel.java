package Class;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class charmel extends JFrame {
	JLabel lblname,lblcourse, lblsection;
	JTextField txtname, txtcourse, txtsection;
	JButton btnadd, btnupdate, btndelete, btnclear;
	DefaultTableModel model;
	JScrollPane pane;
	JTable table;
	
	public charmel() {
	
	setTitle("Student Information Form");
	setSize(450,405);
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
	
	
	String [] columns = {"Name" , "Course", "Section"};
	model = new DefaultTableModel (columns, 0) {
	
	@Override
	public boolean isCellEditable(int row, int column) {
	return false;
			}};
	table = new JTable (model);
	pane = new JScrollPane (table);

	table.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			int row = table.rowAtPoint(evt.getPoint());
			
			if (row >= 0) {
			String name = model.getValueAt(row, 0).toString();
			String course = model.getValueAt(row, 1).toString();
			String section = model.getValueAt(row, 1).toString();
			} } });
	
	btnadd.addActionListener(e->{
		model.addRow(new Object [] {txtname.getText(), txtcourse.getText(), txtsection.getText()});
		
		try(FileWriter fw = new FileWriter ("form.txt")){
			String name = txtname.getText().toString();
			String course = txtcourse.getText().toString();
			String section = txtsection.getText().toString();
			
					
		fw.write(name + " # " + course + " # " + section);
		
		}catch (IOException m) {
		}
	
	
	});
	
	btnupdate.addActionListener(e-> {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Please choose a record first!");
		} else {
			String name = model.getValueAt(selectedRow, 0).toString();
			String course = model.getValueAt(selectedRow, 1).toString();
			String section = model.getValueAt(selectedRow, 1).toString();
			
			txtname.setText(name);
			txtcourse.setText(course);
			txtsection.setText(section);
			
		} });
	
	btndelete.addActionListener(e-> {
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(this, "Please select a row!");
		} else {
		
		int confirm = JOptionPane.showConfirmDialog(this, "Are you certain?", "Delete Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(confirm == JOptionPane.YES_OPTION) {
			model.removeRow(table.getSelectedRow());
		} }
		
		String name = model.getValueAt(selectedRow, 0).toString();
		String course = model.getValueAt(selectedRow, 1).toString();
		String section = model.getValueAt(selectedRow, 1).toString();
		
		try (BufferedReader bw = new BufferedReader(new FileReader("Data.txt"))) {
			String line;
			StringBuilder sb = new StringBuilder();
			
			while ((line = bw.readLine()) != null) {
				String [] data = line.split(" # ");
				if(data.length == 3) {
					if (!data[0].trim().equals(name) || !data[1].trim().equals(course) || !data[2].trim().equals(section)) {
						sb.append(line).append("\n");
					} } }
		
			try (FileWriter fw = new FileWriter("Data.txt")){
				fw.write(sb.toString());
				}
			} catch (IOException x) {
				x.printStackTrace();
				refreshTable(); } });
	
	btnclear.addActionListener(e-> {
		txtname.setText(" ");
		txtcourse.setText(" ");
		txtsection.setText(" ");
	});
	setVisible(true);
	}
	
	public void refreshTable() {
		model.setRowCount(0);
		try (BufferedReader bw = new BufferedReader(new FileReader("Data.txt"))) {
			String line;
			int count = 1;
			
			while ((line = bw.readLine()) != null) {
				String [] data = line.split(" # ");
				if(data.length == 3) {
					model.addRow(new Object [] {txtname.getText(), txtcourse.getText(), txtsection.getText() });
					count++;
				}
			}
		} catch (IOException x) {
	}}
	
	public static void main (String [] args) {
	new charmel();
	}
}
