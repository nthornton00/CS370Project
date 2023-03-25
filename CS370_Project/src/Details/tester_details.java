package Details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class tester_details {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	private void tester_details(String testerName) throws Exception {
		System.out.println(dbClassname);
		
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
		
		String devices;
		int staff;
		
		while(rsTester.next()) {
			if (rsTester.getString("tester_name").equals(testerName)) {
				System.out.println("Tester Name: " + rsTester.getString("tester_name"));
				System.out.println("Project: " + rsTester.getString("tester_project"));
				System.out.println("Peripheral: " + rsTester.getString("peripheral"));
				devices = rsTester.getString("devices");
				staff = rsTester.getInt("staff");
				rsTester.close();
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
	
	/* Was wayyyyy too lazy to implement a J-Unit Function
	public static void main(String[] args) throws Exception {
		tester_details test = new tester_details();
		test.tester_details("Fanta");
	}
	*/
}
