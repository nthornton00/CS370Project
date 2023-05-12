package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login_page {

	private static JFrame mainJFrame;
	private final static JPanel topPanel = new JPanel();
	private static JTextField usernameField;
	private static JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page window = new login_page();
					window.mainJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public static void startLogin() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() throws Exception {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page window = new login_page();
					login_page.mainJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		mainJFrame = new JFrame();
		mainJFrame.setTitle("Lab Map");
		mainJFrame.setBackground(new Color(255, 255, 255));
		mainJFrame.setBounds(100, 100, 500, 530);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.getContentPane().setLayout(null);
		topPanel.setBackground(new Color(192, 192, 192));
		topPanel.setBounds(0, 0, 484, 265);
		mainJFrame.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Lab Map");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(20, 57, 439, 136);
		titleLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 90));
		topPanel.add(titleLabel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 264, 484, 227);
		mainJFrame.getContentPane().add(bottomPanel);
		bottomPanel.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(266, 52, 134, 34);
		bottomPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(105, 50, 100, 36);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bottomPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordLabel.setBounds(105, 97, 100, 34);
		bottomPanel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(266, 97, 134, 34);
		bottomPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				
				checkLogin(username,password);
				
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginButton.setBounds(71, 141, 134, 42);
		bottomPanel.add(loginButton);
		
		JButton createAccountButton = new JButton("Create Account ");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createAccountButton.setBounds(266, 142, 134, 42);
		bottomPanel.add(createAccountButton);
	}
	
	public static boolean checkLogin(String username, char[] password) {
        boolean success = false;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/labmap", "root", "csusm555");

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
                //window.dispose();

            } else {
                // Login unsuccessful
                JOptionPane.showMessageDialog(null, "Login Unsuccessful");
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
