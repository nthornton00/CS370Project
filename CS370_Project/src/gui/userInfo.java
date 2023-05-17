package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class userInfo extends JFrame {
	private JTextField usernameTextField;
	private JTextField roleTextField;
	private JTextField projectTextField;
	private JLabel idLabel;
	private JTextField idTextField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInfo window = new userInfo();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public userInfo() {
		initialize();
		setTitle("User Info");
		setBounds(100, 100, 370, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(221, 229, 237));
	}

	private void initialize() {
		
		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		usernameLbl.setBounds(10, 22, 67, 36);
		getContentPane().add(usernameLbl);
		
		//JLabel projectLbl = new JLabel("Project:");
		//projectLbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		//projectLbl.setBounds(10, 170, 67, 36);
		//getContentPane().add(projectLbl);
		
		JLabel roleLbl = new JLabel("Role:");
		roleLbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		roleLbl.setBounds(10, 69, 67, 37);
		getContentPane().add(roleLbl);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
		usernameTextField.setEditable(false);
		usernameTextField.setBackground(new Color(221, 229, 237));
		usernameTextField.setBounds(72, 23, 131, 36);
		getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		String username = login_page.user_name;
		usernameTextField.setText(username);
		usernameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		roleTextField = new JTextField();
		roleTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
		roleTextField.setBackground(new Color(221, 229, 237));
		roleTextField.setEditable(false);
		roleTextField.setBounds(42, 69, 131, 37);
		getContentPane().add(roleTextField);
		roleTextField.setColumns(10);
		String role = login_page.role;
		roleTextField.setText(role);
		roleTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		/*
		projectTextField = new JTextField();
		projectTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
		projectTextField.setBackground(new Color(221, 229, 237));
		projectTextField.setEditable(false);
		projectTextField.setBounds(56, 165, 131, 42);
		getContentPane().add(projectTextField);
		projectTextField.setColumns(10);
		projectTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		*/
		
		idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		idLabel.setBounds(10, 117, 67, 36);
		getContentPane().add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
		idTextField.setEditable(false);
		idTextField.setBackground(new Color(221, 229, 237));
		idTextField.setBounds(30, 117, 131, 39);
		getContentPane().add(idTextField);
		idTextField.setColumns(10);
		idTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		String badge = String.valueOf(login_page.badge_id);
		idTextField.setText(badge);
	}
}
