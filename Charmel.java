package cahilog;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Charmel extends JFrame{
	static JLabel lblid, lblname, lblage,lbldate,lblnation, lblcontact, lblemail,lbldepart,lbljob, lblstatus, lblgender;
	static JTextField txtid, txtname, txtage, txtdate, txtnation, txtcontact, txtemail, txtdepart, txtjob;
	static JComboBox<String> cbstatus;
	static JRadioButton male, female;
	static ButtonGroup bg;
	static JButton btnadd, btndelete, btnupdate;
	static JTable table;
	static DefaultTableModel model;
	static JScrollPane pane;
	
	public Charmel() {
		setTitle("Employee Management System");
		setSize(800,500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblid = new JLabel ("Employee ID");
		add(lblid).setBounds(20,20,100,25);
		
		txtid = new JTextField();
		add(txtid).setBounds(20, 42,150,25);
		
		lblname = new JLabel("Full name");
		add (lblname).setBounds(20, 80, 100,25);;
		
		txtname = new JTextField();
		add(txtname).setBounds(20, 102 , 150, 25);;
		
		lblage = new JLabel("Age");
		add (lblage).setBounds(200,  20, 100, 25);;
		
		txtage = new JTextField();
		add (txtage).setBounds(200, 42, 150, 25);;
		
		lbldate = new JLabel ("Date of Birth");
		add(lbldate).setBounds(20, 140, 100, 25);;
		
		txtdate = new JTextField();
		add (txtdate).setBounds(20, 162, 150, 25);;
		
		lblnation = new JLabel ("Nationality");
		add(lblnation).setBounds(200, 140, 100, 25);;
		
		txtnation = new JTextField ();
		add (txtnation).setBounds(200, 162, 150,25);;
		
		lblcontact = new JLabel ("Contact Number");
		add (lblcontact).setBounds(380, 80, 100, 25);;
		
		txtcontact = new JTextField();
		add (txtcontact).setBounds(380, 102, 150, 25);
		
		lblemail = new JLabel ("Email");
		add (lblemail).setBounds(380, 140, 100,25);;
		
		txtemail = new JTextField();
		add (txtemail).setBounds(380, 162, 150, 25 );;
		
		lbldepart = new JLabel("Department");
		add(lbldepart).setBounds(560,80, 100, 25);
		
		txtdepart = new JTextField();
		add(txtdepart).setBounds(560, 102, 150, 25 );;
		
		lbljob = new JLabel("Job Title/Position");
		add (lbljob).setBounds(560, 140, 100,25);;
		
		txtjob = new JTextField();
		add (txtjob).setBounds(560,162,150, 25);;
		
		lblstatus =new JLabel ("Civil Status");
		add (lblstatus).setBounds(200, 80, 100, 25);; 
		
		cbstatus = new JComboBox<String>();
		cbstatus.addItem("Single");
		cbstatus.addItem("Married");
		cbstatus.addItem("Widowed");
		cbstatus.addItem("Separated");
		cbstatus.addItem("Divorced");
		add (cbstatus).setBounds(200, 102, 150, 25);;
		
		lblgender = new JLabel ("Gender");
		add (lblgender).setBounds(380, 20,100, 25 );;
		
		male = new JRadioButton("Male");
		add(male).setBounds(380, 42,70,25);
	
		female = new JRadioButton("Female");
		add(female).setBounds(450,42,80,25);;
	
		bg = new ButtonGroup();
		bg.add(male);
		bg.add(female);
		
		btnadd = new JButton("Add Employee");
		add(btnadd).setBounds(660,220,120,25);;
		
		btndelete = new JButton("Delete");
		add(btndelete).setBounds(530, 220, 120, 25);
		
		btnupdate = new JButton ("Update");
		add(btnupdate).setBounds(400, 220, 120, 25);
		
		String[] columns = { "Employee ID", "Full Name", "Birth", "Age", "Civil Status", "Nationality", "Gender", "Contact", "Email", "Department", "Job Title" };
		
		model = new DefaultTableModel(columns, 0);
		
		table = new JTable(model);
		pane = new JScrollPane(table); 
		pane.setBounds(20, 270, 740, 170); 
		add(pane);
		read();
		

		
		 table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                int row = table.getSelectedRow();

	                txtid.setText(model.getValueAt(row, 0).toString());
	                txtname.setText(model.getValueAt(row, 1).toString());
	                txtdate.setText(model.getValueAt(row, 2).toString());
	                txtage.setText(model.getValueAt(row, 3).toString());
	                cbstatus.setSelectedItem(model.getValueAt(row, 4).toString());
	                txtnation.setText(model.getValueAt(row, 5).toString());

	                String gender = model.getValueAt(row, 6).toString();
	                if (gender.equals("Male")) male.setSelected(true);
	                else female.setSelected(true);

	                txtcontact.setText(model.getValueAt(row, 7).toString());
	                txtemail.setText(model.getValueAt(row, 8).toString());
	                txtdepart.setText(model.getValueAt(row, 9).toString());
	                txtjob.setText(model.getValueAt(row, 10).toString());
	            }
	        });

	        btnadd.addActionListener(e -> {
	            add();
	            read();
	            clear();
	        });

	 
	        btndelete.addActionListener(e -> delete());

	        setVisible(true);
	    }

	    public static void add() {
	        try (FileWriter f = new FileWriter("employees.txt", true)) {

	            String gender = male.isSelected() ? "Male" : "Female";
	            String status = (String) cbstatus.getSelectedItem();

	            String data = txtid.getText() + "#" + txtname.getText() + "#" +
	                    txtdate.getText() + "#" + txtage.getText() + "#" +
	                    status + "#" + txtnation.getText() + "#" +
	                    gender + "#" + txtcontact.getText() + "#" +
	                    txtemail.getText() + "#" + txtdepart.getText() + "#" +
	                    txtjob.getText() + "\n";

	            f.write(data);

	            JOptionPane.showMessageDialog(null, "Employee added successfully!");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    public static void delete() {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(null, "Select a record first!");
	            return;
	        }
	        
	        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure u want to delete this?" , 
	        		"Delete Confirmation", 
	        		JOptionPane.YES_NO_OPTION
	        		);
	        	if (confirm == JOptionPane.YES_OPTION) {
	        		
	    
	        ArrayList<String> lines = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
	            String line;
	            int index = 0;

	            while ((line = br.readLine()) != null) {
	                if (index != selectedRow) lines.add(line);
	                index++;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"))) {
	            for (String l : lines) bw.write(l + "\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        JOptionPane.showMessageDialog(null, "Deleted successfully!");
	        read();
	        clear();
	    }}

	    public static void read() {
	        model.setRowCount(0);

	        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                model.addRow(line.split("#"));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void clear() {
	        txtid.setText("");
	        txtname.setText("");
	        txtdate.setText("");
	        txtage.setText("");
	        txtnation.setText("");
	        txtcontact.setText("");
	        txtemail.setText("");
	        txtdepart.setText("");
	        txtjob.setText("");

	        bg.clearSelection();
	        cbstatus.setSelectedIndex(0);
	    }

	    public static void main(String[] args) {
	        new Charmel();
	    }
	}