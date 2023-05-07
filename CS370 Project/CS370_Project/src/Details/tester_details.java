package Details;

import java.sql.*;

public class tester_details {
	//This method grabs the tester info by the requested column and returns a string
	public String fetchTesterInfo(String testerName, String column) throws Exception {
		String result = "NONE";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("SELECT " + column +
		" FROM labmap.tester WHERE tester_name='" + testerName + "'");
		
		//Fetch the result
		rsTester.next();
		result = rsTester.getString(column);
		
		if (result == "null")
			result = "NONE";
		
		//Close connection and return result
		c.close();
		return result;
	}
	
	//This method grabs the devices info by the requested column and returns a string
	public String fetchDevicesInfo(String devices, String column) throws Exception {
		String result = "NONE";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("SELECT " + column +
		" FROM labmap.devices WHERE device_lot='" + devices + "'");
		
		//Fetch the result
		rsTester.next();
		result = rsTester.getString(column);
		
		if (result == "null")
			result = "NONE";
				
		//Close connection and return result
		c.close();
		return result;
	}
	
	//This method grabs the staffing info by the requested column and returns a string
	public String fetchStaffingInfo(int emplID, String column) throws Exception {
		String result = "NONE";
		
		if (emplID == 0)
			return result;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("SELECT " + column +
		" FROM labmap.staffing WHERE badge_id='" + emplID + "'");
		
		//Fetch the result
		rsTester.next();
		result = rsTester.getString(column);
		
		if (result == "null")
			result = "NONE";
		
		//Close connection and return result
		c.close();
		return result;
	}
	
	//This method determines if a device is checked out at the moment and returns a boolean
	public static boolean checked_out(String devices) throws Exception {
		int result = 0;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsDevices = s.executeQuery("SELECT checked_out FROM " +
		"labmap.devices WHERE device_lot='" + devices + "'");
		
		rsDevices.next();
		result = rsDevices.getInt("checked_out");
		
		c.close();
		
		if (result == 1)
			return true;
		else 
			return false;
	}
	
	//This method fetches the employee ID from the tester and returns an int
	public int emplID(String testerName) throws Exception {
		int result = 0;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("SELECT staff FROM " +
		"labmap.tester WHERE tester_name='" + testerName + "'");
		
		rsTester.next();
		result = rsTester.getInt("staff");
		
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	//Returns employee full name from a badge ID
	public String emplName(int emplID) throws Exception {
		String result = "NONE";
		
		if (emplID == 0)
			return result;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsStaffing = s.executeQuery("SELECT * FROM "+
		"labmap.staffing WHERE badge_id='" + emplID + "'");
		
		//Combines the string from employee first name and last name
		//to return the full name.
		rsStaffing.next();
		result = rsStaffing.getString("employee_firstname");
		result += " " + rsStaffing.getString("employee_lastname");
		
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	//Lets the currently logged in user claim the tester
	public static void claim_tester(int employee_ID, String tester_name) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		String query = "UPDATE labmap.tester SET staff = ? WHERE tester_name = ?";
		try (PreparedStatement updateUser = c.prepareStatement(query)) {
			updateUser.setInt(1, employee_ID);
			updateUser.setObject(2, tester_name);
			updateUser.execute();
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		System.out.println("Successfully claimed tester!\n");
		c.close();
	}
}
