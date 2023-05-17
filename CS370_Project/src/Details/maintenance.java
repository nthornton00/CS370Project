package Details;

import java.sql.Connection;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
	
	public void modify_description(int woID, String Desc) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Establish time for the update description
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(timestamp));
		
		String updateDesc = fetchMaintInfo(woID, "description");
		
		if (!Desc.equals("")) {
			updateDesc += "\nUPDATE " + dateFormat.format(timestamp) + ": " + Desc;
		}
		
		String updateMaint = "UPDATE labmap.maintenance SET description = ? WHERE wo_id = ?";
		try (PreparedStatement updateWO = c.prepareStatement(updateMaint)) {
			updateWO.setString(1, updateDesc);
			updateWO.setInt(2, woID);
			updateWO.execute();
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		System.out.println("UPDATE SUCCESSFUL!\n");
		c.close();
		return;
	}
	
	public void set_status_closed(int woID) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		String updateMaint = "UPDATE labmap.maintenance SET status = 'Closed' WHERE wo_id = " + woID;
				
		PreparedStatement setClosed = c.prepareStatement(updateMaint);
		
		String finish = "UPDATE labmap.maintenance SET date_finished = CURRENT_TIMESTAMP() WHERE wo_id = " + woID;
		
		PreparedStatement finishTime = c.prepareStatement(finish);
		
		setClosed.execute();
		finishTime.execute();
		
		c.close();
		return;
	}
	
	public String fetchMaintInfo(int woID, String column) throws Exception {
		String result = "NONE";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsMaint = s.executeQuery("SELECT " + column +
		" FROM labmap.maintenance WHERE wo_id='" + woID + "'");
		
		//Fetch the result
		rsMaint.next();
		result = rsMaint.getString(column);
		
		if (result == "null")
			result = "NONE";
		
		//Close connection and return result
		c.close();
		return result;
	}
}
