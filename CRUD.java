import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CRUD extends JFrame{
	static JLabel lblname, lblroom, lblindate, lbloutdate ;
	static JTextField txtname, txtroom, txtindate, txtoutdate;
	static JButton btnadd, btnupdate, btndelete, btnexit;
	static JTable table;
	static DefaultTableModel model;
	static JScrollPane pane;
	
	public CRUD() {
		
		setTitle("Hotel Reservation System");
		setSize(840,890);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblname = new JLabel("Guest Name");
		add(lblname).setBounds(20, 675, 120,25);
		txtname = new JTextField ();
		add (txtname).setBounds(20, 700, 150,25);
		
		lblroom = new JLabel ("Room Type");
		add(lblroom).setBounds(180, 675,120,25);
		txtroom = new JTextField();
		add(txtroom).setBounds(180,700, 150, 25);
		
		lblindate = new JLabel ("Check-in Date");
		add(lblindate).setBounds(340, 675, 120,25);
		txtindate= new JTextField();
		add(txtindate).setBounds(340,700,150,25);
		
		lbloutdate= new JLabel ("Check-out Date");
		add(lbloutdate).setBounds(500,675, 120,25);
		txtoutdate = new JTextField();
		add(txtoutdate).setBounds(500, 700, 150,25);
	    setVisible(true);
	    
	    btnadd = new JButton("ADD");
	    add(btnadd).setBounds(700, 700,90,25);
	    
	    btnupdate = new JButton ("UPDATE");
	    add(btnupdate).setBounds(700, 730, 90,25);
	    
	    btndelete = new JButton ("DELETE");
	    add(btndelete).setBounds(700, 760,90, 25);
	    
	    btnexit = new JButton ("EXIT");
	    add(btnexit).setBounds(700, 790, 90, 25);
	
		String [] columns= {"Guest Name", "Room Type", "Check-in Date", "Check-out Dates"};

		model = new DefaultTableModel(columns, 0);
		
		table = new JTable(model);
		pane = new JScrollPane(table); 
		pane.setBounds(10, 4, 800, 600); 
		add(pane);
		read();
		 table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                int row = table.getSelectedRow();

	                txtname.setText(model.getValueAt(row, 0).toString());
	                txtroom.setText(model.getValueAt(row, 1).toString());
	                txtindate.setText(model.getValueAt(row, 2).toString());
	                txtoutdate.setText(model.getValueAt(row, 3).toString());
	            }
	        });

	        btnadd.addActionListener(e -> {
	            add();
	            read();
	            clear();
	        });
	        btnexit.addActionListener(e -> exit ());
	        btnupdate.addActionListener(e -> update());
	        btndelete.addActionListener(e -> delete());

	        setVisible(true);
	    }
	
	public static void add () {
		 try (FileWriter f = new FileWriter("employees.txt", true)) {

	            String data = txtname.getText() + "#" + txtroom.getText() + "#" + txtindate.getText() + "#" + txtoutdate.getText() + "\n";
	            f.write(data);
	            
	            JOptionPane.showMessageDialog(null, "added successfully");
	        } catch (IOException a) {
	        	a.printStackTrace();
	        }
	    }
	public static void update() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Select a record first!");
				return;
		}
		
		ArrayList<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
			String line;
			int index = 0;
			
			while ((line = br.readLine()) != null) {
				if (index == selectedRow) {
					
					String update = txtname.getText() + "#" + txtroom.getText() + "#" +
						txtindate.getText() + "#" + txtoutdate.getText() + "#" ;

					
						lines.add(update);
					} else {
					lines.add(line);
					}
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
		
		JOptionPane.showMessageDialog(null, "Updated successfully!");
		read();
		clear();
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
		}} catch (IOException e) {
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
		
		public static void exit() {
		
		    int confirm = JOptionPane.showConfirmDialog(
		        null,
		        "Are you sure you want to exit?",
		        "Exit Confirmation",
		        JOptionPane.YES_NO_OPTION
		    );

		    if (confirm == JOptionPane.YES_OPTION) {
		        System.exit(0);
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
	        txtname.setText("");
	        txtroom.setText("");
	        txtindate.setText("");
	        txtoutdate.setText("");
	    }


	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new CRUD ();
	}

}
