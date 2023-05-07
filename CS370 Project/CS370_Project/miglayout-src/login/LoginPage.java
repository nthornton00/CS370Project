package login;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;


import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LoginPage {

	private JFrame frmLabMap;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLabMap.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLabMap = new JFrame();
		frmLabMap.setTitle("Lab Map");
		frmLabMap.getContentPane().setBackground(new Color(128, 128, 128));
		frmLabMap.setBackground(new Color(255, 255, 255));
		frmLabMap.setBounds(100, 100, 650, 570);
		frmLabMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLabMap.getContentPane().setLayout(null);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 283, 634, 248);
		frmLabMap.getContentPane().add(bottomPanel);
		bottomPanel.setLayout(null);
		
		username = new JTextField();
		username.setBounds(318, 57, 140, 30);
		bottomPanel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(318, 100, 140, 30);
		bottomPanel.add(password);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		usernameLabel.setBounds(143, 57, 98, 30);
		bottomPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordLabel.setBounds(143, 100, 98, 30);
		bottomPanel.add(passwordLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u_name = username.getText();
				char[] p_word = password.getPassword();
				
				checkLogin(u_name,p_word);
				
			}
		});
		
		
		
		
		loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
		loginButton.setBounds(143, 161, 135, 44);
		bottomPanel.add(loginButton);
		
		JButton createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createAccountButton.setFont(new Font("Arial", Font.PLAIN, 16));
		createAccountButton.setBounds(318, 161, 146, 44);
		bottomPanel.add(createAccountButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(192, 192, 192));
		topPanel.setBounds(0, 0, 634, 285);
		frmLabMap.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lab Map");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 99));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 0, 634, 285);
		topPanel.add(lblNewLabel);
		
	}
	
	public static int checkLogin(String username, char[] password) {
        int success = 0;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/labmap", "root", "38925");

            // Create a PreparedStatement to execute the SELECT query
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, String.valueOf(password));

            // Execute the SELECT query
            ResultSet rs = ps.executeQuery();

            // Check if the SELECT query returned a result
            if (rs.next()) {
                // Login successful
                JOptionPane.showMessageDialog(null, "Login Successful");
                success = 1;

            } else {
                // Login unsuccessful
                JOptionPane.showMessageDialog(null, "Login Unsuccessful");
                success = -1;
            }

            // Close the database connection and resources
            rs.close();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return success;
    }
}