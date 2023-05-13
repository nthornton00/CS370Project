package Details;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class maintenance {
	public void submit_request(int empl_Num, String tester, String peripheral, String description) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		ResultSet rsWO = s.executeQuery("SELECT MAX(wo_id) AS maxWO FROM labmap.maintenance");
		
		rsWO.next();
		
		int maxWO = rsWO.getInt("maxWO") + 1; //Grabs next work order number
		
		String query = "INSERT INTO labmap.maintenance (wo_id, submitted_time, status, tester, peripheral, requestor, description)"
		+ " VALUES ('" + maxWO + "', CURRENT_TIMESTAMP(), 'Active', '" + tester + "', '" + peripheral + "', '"
		+ empl_Num + "', '" + description + "')";
		
		PreparedStatement submitRequest = c.prepareStatement(query);
		submitRequest.execute();
		
		System.out.println("SUBMISSION SUCCESSFUL!\n");
		c.close();
		return;
	}
	
	public void modify_request(int woID) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		ResultSet rsWO = s.executeQuery("SELECT * FROM labmap.maintenance WHERE 'woID'=" + woID);
		
		rsWO.next();
		
		System.out.println("SUBMISSION SUCCESSFUL!\n");
		c.close();
		return;
	}
}
