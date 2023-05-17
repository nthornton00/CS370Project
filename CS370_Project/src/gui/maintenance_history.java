package gui;
import Details.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Details.tester_details;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class maintenance_history extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public maintenance_history(int wo_id) throws Exception {
		setResizable(false);
		setTitle("Maintenance - Work Order ID #" + wo_id);
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		maintenance maint = new maintenance();
		
		JLabel lblNewLabel = new JLabel("Tester: " + maint.fetchMaintInfo(wo_id, "tester"));
		lblNewLabel.setBounds(10, 11, 200, 14);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblPeripheral = new JLabel("Peripheral: " + maint.fetchMaintInfo(wo_id, "peripheral"));
			lblPeripheral.setBounds(215, 11, 200, 14);
			contentPanel.add(lblPeripheral);
		}
		{
			JLabel lblSubmitTime = new JLabel("Submit Time: " + maint.fetchMaintInfo(wo_id, "submitted_time"));
			lblSubmitTime.setBounds(10, 36, 200, 14);
			contentPanel.add(lblSubmitTime);
		}
		
		{
			JLabel lblStatus = new JLabel("Finish Time: " + maint.fetchMaintInfo(wo_id, "date_finished"));
			lblStatus.setBounds(215, 36, 200, 14);
			contentPanel.add(lblStatus);
		}
		
		{
			JTextArea currentRemarksTextArea = new JTextArea(maint.fetchMaintInfo(wo_id, "description"));
			currentRemarksTextArea.setEditable(false);
			currentRemarksTextArea.setBounds(10, 75, 414, 106);
			contentPanel.add(currentRemarksTextArea);
			currentRemarksTextArea.setLineWrap(true);
			currentRemarksTextArea.setWrapStyleWord(true);
		}
		{
			JLabel lblCurrentRemarks = new JLabel("Current Remarks: ");
			lblCurrentRemarks.setBounds(10, 58, 414, 14);
			contentPanel.add(lblCurrentRemarks);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("OK");
				cancelButton.setActionCommand("OK");
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
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
