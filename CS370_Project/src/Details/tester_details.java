package Details;

import java.sql.*;
import java.util.Properties;

public class tester_details {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	private void tester_details(String testerName) throws Exception {
		Properties p = new Properties();
		
		//Login
		p.put("user", "root");
		p.put("password", "t3$t0573");
		
		//Initialize connection to the database
		Connection c = DriverManager.getConnection(CONNECTION,p);
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		String devices;	//Holds device string
		int staff;		//Holds employee ID
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				System.out.println("Tester Name: " + rsTester.getString("tester_name"));
				System.out.println("Project: " + rsTester.getString("tester_project"));
				System.out.println("Peripheral: " + rsTester.getString("peripheral"));
				devices = rsTester.getString("devices");
				staff = rsTester.getInt("staff");
				rsTester.close();
				if (devices == null) {
					System.out.println("Devices: None");
					System.out.println("Manufacturing Lot: NA");
					System.out.println("Quantity: NA");
				}
				ResultSet rsDevices = s.executeQuery("select * from labmap.devices");
				while(rsDevices.next()) {
					if (rsDevices.getString("device_lot").equals(devices)) {
						System.out.println("Devices: " + devices);
						System.out.println("Manufacturing Lot: " + rsDevices.getString("mfr_lot"));
						System.out.println("Quantity: " + rsDevices.getInt("device_qty"));
						rsDevices.close();
						break;
					}
				}
				ResultSet rsStaffing = s.executeQuery("select * from labmap.staffing");
				while(rsStaffing.next()) {
					if (rsStaffing.getInt("badge_id") == staff) {
						System.out.println("Employee Name: " + rsStaffing.getString("employee_name"));
						System.out.println("Role: " + rsStaffing.getString("role"));
						break;
					}
				}
				break;
			}
		}
		
		c.close();
	}
	
	private void claim_tester(int employee_ID, String tester_name) throws Exception {
Properties p = new Properties();
		
		//Login
		p.put("user", "root");
		p.put("password", "t3$t0573");
		
		//Initialize connection to the database
		Connection c = DriverManager.getConnection(CONNECTION,p);
		
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
		
		System.out.println("\nUPDATE SUCCESSFUL!\n");
	}
	
	//Was wayyyyy too lazy to implement a J-Unit Function
	public static void main(String[] args) throws Exception {
		tester_details test = new tester_details();
		test.tester_details("Fanta");
		test.claim_tester(7198, "Fanta");
		test.tester_details("Fanta");
		test.claim_tester(7200, "Fanta");
		test.claim_tester(7199, "Coca-Cola");
		test.tester_details("Coca-Cola");
	}

}
