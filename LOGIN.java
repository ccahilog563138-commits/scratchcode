import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LOGIN extends JFrame implements ActionListener {
    
	JLabel lbltitle, lblname, lblpass;
    JTextField txtname;
    JPasswordField txtpass; 
    JButton btnlog, btnregister, btnexit;

    public LOGIN() {
        setTitle("Login");
        setSize(530, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 

        lbltitle = new JLabel("LOGIN");
        lbltitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lbltitle).setBounds(225, 40, 150, 40);

        lblname = new JLabel("Username:");
        add(lblname).setBounds(25, 120, 100, 25);
        
        txtname = new JTextField();
        add(txtname).setBounds(130, 120, 300, 25);
        
        lblpass = new JLabel("Password:");
        add(lblpass).setBounds(25, 165, 100, 25);
        
        txtpass = new JPasswordField();
        add(txtpass).setBounds(130, 165, 300, 25);
       

        // Buttons
        btnlog = new JButton("Login");
        btnlog.setBackground(new Color(52, 120, 246));
        btnlog.setForeground(Color.WHITE);
        btnlog.addActionListener(this);
        add(btnlog).setBounds(35, 220, 120, 28);

        btnregister = new JButton("Register");
        btnregister.setBackground(new Color(46, 204, 113));
        btnregister.setForeground(Color.WHITE);
        btnregister.addActionListener(this);
        add(btnregister).setBounds(190, 220, 120, 28);

        btnexit = new JButton("Exit");
        btnexit.setBackground(new Color(231, 76, 60));
        btnexit.setForeground(Color.WHITE);
        btnexit.addActionListener(this);
        add(btnexit).setBounds(345, 220, 120, 28);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnlog) {
            String username = txtname.getText();
            String password = new String(txtpass.getPassword());
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 2 && data[0].trim().equals(username) && data[1].trim().equals(password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Username/password not found!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnregister) {
            new AccountManagementSystem_Register();
            this.dispose();
        }

        if (e.getSource() == btnexit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LOGIN());
    }
}
