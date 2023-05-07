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
	private JTextField devicesTextField;

	public void refreshWindow(String tester_name, int emplNum) throws Exception {
		tester_detail_window tester_detail = new tester_detail_window(tester_name, emplNum);
		tester_detail.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		tester_detail.setVisible(true);
	}
	
	/**
	 * @throws Exception 
	 */
	public tester_detail_window(String tester_name, int emplNum) throws Exception {
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(221, 229, 237));
		setFont(new Font("Corbel Light", Font.PLAIN, 14));

		setTitle("Modify Tester Details");
		setBounds(100, 100, 360, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(221, 229, 237));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		
		setResizable(false);
		
		tester_details test = new tester_details();
		
		contentPanel.setLayout(new MigLayout("", "[112px]", "[18px][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		String devices = test.fetchTesterInfo(tester_name, "devices");
		int employee = test.emplID(tester_name);
		{
			JLabel lblCurrentTesterDetails = new JLabel("Current Tester Details:");
			lblCurrentTesterDetails.setVerticalAlignment(SwingConstants.TOP);
			lblCurrentTesterDetails.setHorizontalAlignment(SwingConstants.LEFT);
			lblCurrentTesterDetails.setFont(new Font("Calibri", Font.BOLD, 14));
			contentPanel.add(lblCurrentTesterDetails, "cell 0 0");
		}
		{
			JLabel lblTesterName = new JLabel("Tester Name: " + tester_name);
			lblTesterName.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTesterName.setHorizontalAlignment(SwingConstants.LEFT);
			lblTesterName.setVerticalAlignment(SwingConstants.TOP);
			contentPanel.add(lblTesterName, "cell 0 1,alignx left,aligny top");
		}
		{
			JLabel lblPrj = new JLabel("Tester Project: " + test.fetchDevicesInfo(devices, "device_project"));
			lblPrj.setVerticalAlignment(SwingConstants.TOP);
			lblPrj.setHorizontalAlignment(SwingConstants.LEFT);
			lblPrj.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblPrj, "cell 0 2");
		}
		{
			JLabel lblPeripheral = new JLabel("Peripheral: " + test.fetchTesterInfo(tester_name, "peripheral"));
			lblPeripheral.setVerticalAlignment(SwingConstants.TOP);
			lblPeripheral.setHorizontalAlignment(SwingConstants.LEFT);
			lblPeripheral.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblPeripheral, "cell 0 3");
		}
		{
			JLabel lblDevLot = new JLabel("Devices: " + devices);
			lblDevLot.setVerticalAlignment(SwingConstants.TOP);
			lblDevLot.setHorizontalAlignment(SwingConstants.LEFT);
			lblDevLot.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblDevLot, "flowx,cell 0 4");
		}
		{
			JLabel lblMfrLot = new JLabel("Manufacturing Lot: " + test.fetchDevicesInfo(devices, "mfr_lot"));
			lblMfrLot.setVerticalAlignment(SwingConstants.TOP);
			lblMfrLot.setHorizontalAlignment(SwingConstants.LEFT);
			lblMfrLot.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblMfrLot, "cell 0 5");
		}
		{
			JLabel lblDevQty = new JLabel("Device Quantity: " + test.fetchDevicesInfo(devices, "device_qty"));
			lblDevQty.setVerticalAlignment(SwingConstants.TOP);
			lblDevQty.setHorizontalAlignment(SwingConstants.LEFT);
			lblDevQty.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblDevQty, "cell 0 6");
		}
		{
			JLabel lblStaff = new JLabel("Staff: " + test.emplName(employee));
			lblStaff.setVerticalAlignment(SwingConstants.TOP);
			lblStaff.setHorizontalAlignment(SwingConstants.LEFT);
			lblStaff.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblStaff, "cell 0 7");
		}
		
				
		String[] peripherals = {"", "HA7200", "HA7300", "M4841", "M4872", "M4171", "M6242", "Manual Socket"};
		{
			JLabel lblStaffRole = new JLabel("Staff Role: " + test.fetchStaffingInfo(employee, "role"));
			lblStaffRole.setVerticalAlignment(SwingConstants.TOP);
			lblStaffRole.setHorizontalAlignment(SwingConstants.LEFT);
			lblStaffRole.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblStaffRole, "cell 0 8");
		}
		{
			JLabel lblModifyTesterDetails = new JLabel("Modify Tester Details:");
			lblModifyTesterDetails.setVerticalAlignment(SwingConstants.TOP);
			lblModifyTesterDetails.setHorizontalAlignment(SwingConstants.LEFT);
			lblModifyTesterDetails.setFont(new Font("Calibri", Font.BOLD, 14));
			contentPanel.add(lblModifyTesterDetails, "cell 0 12");
		}
		{
			JLabel lblNewDevs = new JLabel("New Devices:");
			lblNewDevs.setVerticalAlignment(SwingConstants.TOP);
			lblNewDevs.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewDevs.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewDevs, "flowx,cell 0 13");
		}
		{
			JLabel lblNewPeripheral = new JLabel("New Peripheral:");
			lblNewPeripheral.setVerticalAlignment(SwingConstants.TOP);
			lblNewPeripheral.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewPeripheral.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNewPeripheral, "flowx,cell 0 14");
		}
		{
			JSeparator separator = new JSeparator();
			contentPanel.add(separator, "cell 0 15");
		}
		
		devicesTextField = new JTextField();
		contentPanel.add(devicesTextField, "cell 0 13");
		devicesTextField.setColumns(11);
		
		JComboBox peripheralsCB = new JComboBox(peripherals);
		contentPanel.add(peripheralsCB, "cell 0 14");
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(221, 229, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton claimTesterBtn = new JButton("Claim Tester");
				claimTesterBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							tester_details.claim_tester(emplNum, tester_name);
							tester_detail_window tester_detail = new tester_detail_window(tester_name, emplNum);
							tester_detail.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							tester_detail.setVisible(true);
							JOptionPane.showMessageDialog(null, "You have self-asigned\nyourself this tester!");
							dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(claimTesterBtn);
			}
			{
				JButton modifyTesterBtn = new JButton("Modify Details");
				modifyTesterBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String newPeripheral = (String) peripheralsCB.getItemAt(peripheralsCB.getSelectedIndex());
						modify_tester_details modTester = new modify_tester_details();
						try {
							if (devicesTextField.getText().equals("") || devicesTextField.getText().equals(devices)) {
								if (!newPeripheral.equals("")) {
									modTester.tester_peripheral(tester_name, newPeripheral);
									JOptionPane.showMessageDialog(null, "Updated peripherals");
								}
								else
									JOptionPane.showMessageDialog(null, "Nothing was changed");
								tester_detail_window detail_window = new tester_detail_window(tester_name, emplNum);
								detail_window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								detail_window.setVisible(true);
								dispose();
							}
							else if (modTester.tester_devices(tester_name, devicesTextField.getText())) {
								modTester.return_devices(devices);
								if (!newPeripheral.equals(""))
									modTester.tester_peripheral(tester_name, newPeripheral);
								tester_detail_window detail_window = new tester_detail_window(tester_name, emplNum);
								detail_window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								detail_window.setVisible(true);
								JOptionPane.showMessageDialog(null, "Updated devices and peripheral");
								dispose();
							}
							else {
								refreshWindow(tester_name, emplNum);
								JOptionPane.showMessageDialog(null, "Nothing was changed");
								dispose();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Nothing was changed");
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(modifyTesterBtn);
			}
		}
	}
}
