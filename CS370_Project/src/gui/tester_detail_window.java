package gui;
import Details.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class tester_detail_window extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * @throws Exception 
	 */
	public tester_detail_window(String tester_name) throws Exception {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(221, 229, 237));
		setFont(new Font("Corbel Light", Font.PLAIN, 14));

		setTitle("Tester Details");
		setBounds(100, 100, 360, 265);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(221, 229, 237));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		
		tester_details test = new tester_details();
		
		contentPanel.setLayout(new MigLayout("", "[112px]", "[18px][][][][][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Tester Name: " + tester_name);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			contentPanel.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		}
		{
			JLabel lblNewLabel = new JLabel("Tester Project: " + test.tester_prj(tester_name));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 1");
		}
		{
			JLabel lblNewLabel = new JLabel("Peripheral: " + test.tester_peripheral(tester_name));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 2");
		}
		String devices = test.tester_devices(tester_name);
		{
			JLabel lblNewLabel = new JLabel("Devices: " + devices);
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 3");
		}
		{
			JLabel lblNewLabel = new JLabel("Manufacturing Lot: " + test.devices_mfr(devices));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 4");
		}
		{
			JLabel lblNewLabel = new JLabel("Device Quantity: " + test.devices_qty(devices));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 5");
		}
		int employee = test.emplID(tester_name);
		{
			JLabel lblNewLabel = new JLabel("Staff: " + test.emplName(employee));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 6");
		}
		{
			JLabel lblNewLabel = new JLabel("Staff Role: " + test.emplRole(employee));
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewLabel, "cell 0 7");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(221, 229, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Claim Tester");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							tester_details.claim_tester(7198, "Fanta");
							tester_detail_window dialog = new tester_detail_window(tester_name);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(btnNewButton);
			}
		}
	}

}
