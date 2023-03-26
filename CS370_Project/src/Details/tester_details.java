package Details;

import java.sql.*;

public class tester_details {
	public String tester_name(String testerName) throws Exception {
		String result = "???";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				result = rsTester.getString("tester_name");
			}
		}
		c.close();
		System.out.println(result);
		return result;
	}

	public String tester_prj(String testerName) throws Exception {
		String result = "None";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				result = rsTester.getString("tester_project");
			}
		}
		c.close();
		System.out.println(result);
		return result;
	}
	
	public String tester_peripheral(String testerName) throws Exception {
		String result = "None";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				result = rsTester.getString("peripheral");
			}
		}
		c.close();
		System.out.println(result);
		return result;
	}
	
	public String tester_devices(String testerName) throws Exception {
		String result = "None";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				result = rsTester.getString("devices");
			}
		}
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	public String devices_mfr(String devices) throws Exception {
		String result = "NA";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsDevices = s.executeQuery("select * from labmap.devices");
		
		while(rsDevices.next()) {
			if (rsDevices.getString("device_lot").equals(devices)) {
				result = rsDevices.getString("mfr_lot");
			}
		}
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	public String devices_qty(String devices) throws Exception {
		String result = "NA";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsDevices = s.executeQuery("select * from labmap.devices");
		
		while(rsDevices.next()) {
			if (rsDevices.getString("device_lot").equals(devices)) {
				result = rsDevices.getString("device_qty");
			}
		}
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	public int emplID(String testerName) throws Exception {
		int result = 0;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				result = rsTester.getInt("staff");
			}
		}
		
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	public String emplName(int emplID) throws Exception {
		String result = "None";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsStaffing = s.executeQuery("select * from labmap.staffing");
		
		while(rsStaffing.next()) {
			if (rsStaffing.getInt("badge_id") == emplID) {
				result = rsStaffing.getString("employee_firstname");
				result += " " + rsStaffing.getString("employee_lastname");
			}
		}
		c.close();
		System.out.println(result);
		
		return result;
	}
	
	public String emplRole(int emplID) throws Exception {
		String result = "None";
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsStaffing = s.executeQuery("select * from labmap.staffing");
		
		while(rsStaffing.next()) {
			if (rsStaffing.getInt("badge_id") == emplID) {
				result = rsStaffing.getString("role");
			}
		}
		c.close();
		System.out.println(result);
		
		return result;
	}
	
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
		
		System.out.println("UPDATE TABLE SUCCESSFUL!\n");
		c.close();
	}
	
	//Was wayyyyy too lazy to implement a J-Unit Function
	

}
