package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import Details.establish_connection;

public class maintenance_log extends JDialog {

	/**
	 * Create the dialog.
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel table = new DefaultTableModel();
	Container cnt = this.getContentPane();
	JTable jtbl = new JTable(table);
	private JTextField woTextField;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	
	public maintenance_log() throws Exception {
		table.addColumn("Work Order");
		table.addColumn("Tester");
		table.addColumn("Peripheral");
		table.addColumn("Staus");
		table.addColumn("Submit Time");
		table.addColumn("Complete Time");
		
		jtbl.getColumnModel().getColumn(0).setMaxWidth(40);
		jtbl.getColumnModel().getColumn(1).setMaxWidth(80);
		jtbl.getColumnModel().getColumn(2).setMaxWidth(80);
		jtbl.getColumnModel().getColumn(3).setMaxWidth(80);
		jtbl.getColumnModel().getColumn(4).setMaxWidth(160);
		jtbl.getColumnModel().getColumn(5).setMaxWidth(160);
		
		//Create dialog window
		setTitle("Maintenance Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 254);
		setResizable(false);
        
		//Initialize connection to the database
  		Connection c = establish_connection.connect();
  		
  		//Create the database statement
  		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  		
  		//Initialize result set and classes for tester database based in the selected region
  		ResultSet rsMaintLog = s.executeQuery("SELECT * FROM labmap.maintenance WHERE status='Closed'");
		
		while (rsMaintLog.next()) {
  			String id = String.valueOf(rsMaintLog.getInt("wo_id"));
  			String tester = rsMaintLog.getString("tester");
  			String peripheral = rsMaintLog.getString("peripheral");
  			String status = rsMaintLog.getString("status");
  			String submit = rsMaintLog.getString("submitted_time");
  			String complete = rsMaintLog.getString("date_finished");
  			
  			
  			table.addRow(new Object[] {id, tester, peripheral, status, submit, complete});
  		}
		
		ResultSet rsWO = s.executeQuery("SELECT MAX(wo_id) AS maxWO FROM labmap.maintenance");
		
		rsWO.next();
		
		int maxWO = rsWO.getInt("maxWO"); //Grabs next work order number
		
		c.close(); //Close connection

		JScrollPane pg = new JScrollPane(jtbl);
		pg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pg.setPreferredSize(new Dimension(580,218));
        cnt.add(pg);
        this.pack();
        
		{ //Exit Button
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel = new JLabel("Work Order:");
				buttonPane.add(lblNewLabel);
			}
			{
				woTextField = new JTextField();
				woTextField.setText("");
				buttonPane.add(woTextField);
				woTextField.setColumns(20);
			}
			JButton btnViewWorkOrder = new JButton("View Work Order");
			buttonPane.add(btnViewWorkOrder);
			btnViewWorkOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String woIDString = woTextField.getText();
						int woID = Integer.parseInt(woIDString); 
						if (woID > maxWO || woID < 0) {
							JOptionPane.showMessageDialog(null, "This work order does not exist!");
						}
						else {
							maintenance_history historyLog = new maintenance_history(woID);
							historyLog.setVisible(true);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Please input valid integer!");
					}
				}
			});
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Close");
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
				closeButton.addActionListener(new ActionListener() {
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
