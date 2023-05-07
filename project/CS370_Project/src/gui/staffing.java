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
import javax.swing.table.DefaultTableModel;

import Details.establish_connection;

import javax.swing.ScrollPaneConstants;

public class staffing extends JDialog {
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
	
	public staffing(String shift, String region) throws Exception {
		table.addColumn("ID");
		table.addColumn("Name");
		table.addColumn("Role");
		table.addColumn("Region");
		
		jtbl.getColumnModel().getColumn(0).setMaxWidth(40);
		jtbl.getColumnModel().getColumn(1).setMaxWidth(150);
		jtbl.getColumnModel().getColumn(2).setMaxWidth(332);
		jtbl.getColumnModel().getColumn(3).setMaxWidth(80);
		
		//Create dialog window
		setTitle("Staffing - " + shift);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 254);
		setResizable(false);
        
		//Initialize connection to the database
  		Connection c = establish_connection.connect();
  		
  		//Create the database statement
  		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  		
  		//Initialize result set and classes for tester database based in the selected region
  		ResultSet rsStaffing = s.executeQuery("SELECT * FROM labmap.staffing WHERE (shift='" + shift + "'"+
  		" AND region='" + region + "') ORDER BY employee_lastname");
		
		while (rsStaffing.next()) {
  			String id = String.valueOf(rsStaffing.getInt("badge_id"));
  			String name = rsStaffing.getString("employee_lastname");
  			name += ", " + rsStaffing.getString("employee_firstname");
  			String role = rsStaffing.getString("role");
  			String staff_region = rsStaffing.getString("region");
  			//String empl_shift = rsStaffing.getString("shift");
  			
  			table.addRow(new Object[] {id, name, role, staff_region});
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
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
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
