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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		
		//for (int i = 0; i < 32; i++)
			//table.addRow(new Object[] {"TEST", "TEST", "TEST", "TEST"});

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
