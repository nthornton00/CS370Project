package gui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Details.*;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class submit_request extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public submit_request(int emplNum) throws Exception {
		setResizable(false);
		setTitle("Submit Maintenance Request");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tester:");
		lblNewLabel.setBounds(10, 11, 84, 21);
		contentPanel.add(lblNewLabel);
		
		JComboBox testerCB = new JComboBox(new Object[]{});
		testerCB.setBounds(94, 10, 330, 22);
		contentPanel.add(testerCB);
		
		try {			
			//Initialize connection to the database
			Connection c = establish_connection.connect();
			
			//Create the database statement
			Statement s = c.createStatement();
			
			//Initialize result set and classes for tester database
			ResultSet rsTester = s.executeQuery("SELECT * FROM labmap.tester");
			
			while (rsTester.next()) {
				String name = rsTester.getString("tester_name");
				testerCB.addItem(name);
			}
			
			c.close();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		
		JLabel lblPeripheral = new JLabel("Peripheral:");
		lblPeripheral.setBounds(10, 43, 84, 22);
		contentPanel.add(lblPeripheral);
		
		String[] peripherals = {"HA7200", "HA7300", "M4841", "M4872", "M4171", "M6242", "Manual Socket"};
		
		JComboBox peripheralsCB = new JComboBox(peripherals);
		peripheralsCB.setBounds(94, 43, 330, 22);
		contentPanel.add(peripheralsCB);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 74, 414, 22);
		contentPanel.add(lblDescription);
		
		JTextArea descriptionTextField = new JTextArea();
		descriptionTextField.setBounds(10, 95, 414, 122);
		contentPanel.add(descriptionTextField);
		descriptionTextField.setLineWrap(true);
		descriptionTextField.setWrapStyleWord(true);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton submitButton = new JButton("Submit");
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				submitButton.setActionCommand("Submit");
				buttonPane.add(submitButton);
				getRootPane().setDefaultButton(submitButton);
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String submitTester = (String) testerCB.getItemAt(testerCB.getSelectedIndex());
						String submitPeripheral = (String) peripheralsCB.getItemAt(peripheralsCB.getSelectedIndex());
						String description = descriptionTextField.getText();
						maintenance submit_request = new maintenance();
						try {
							submit_request.submit_request(emplNum, submitTester, submitPeripheral, description);
							JOptionPane.showMessageDialog(null, "Submission is successful!");
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
	}
}
