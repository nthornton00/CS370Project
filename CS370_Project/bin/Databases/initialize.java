package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class initialize {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";

	public initialize() throws Exception {
		System.out.println(dbClassname);
		
		Properties p = new Properties();
		
		//Login
		p.put("user", "shoko");
		p.put("password", "test");
		
		//Initialize connection to the database
		Connection c = DriverManager.getConnection(CONNECTION,p);
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsTester = s.executeQuery("select * from labmap.tester");
		
		//Encapsulates the new classes for the tester
		while (rsTester.next()) {
			tester testerDB = new tester();
	        testerDB.setTesterName(rsTester.getString("tester_name"));
	        testerDB.setTesterProject(rsTester.getString("tester_project"));
	        testerDB.setPeripheral(rsTester.getString("peripheral"));
		}
		
		//Initialize result set and classes for staffing database
		ResultSet rsStaffing = s.executeQuery("select * from labmap.staffing");
		
		//Encapsulates the new classes for the staffing
		while (rsStaffing.next()) {
			staffing staffingDB = new staffing();
			staffingDB.setBadgeID(rsStaffing.getInt("badge_id"));
			staffingDB.setEmployeeName(rsStaffing.getString("employee_name"));
			staffingDB.setRole(rsStaffing.getString("role"));
		}
		
		//Initialize result set and classes for devices database
		ResultSet rsDevices = s.executeQuery("select * from labmap.devices");
		
		//Encapsulates the new classes for the devices
		while (rsDevices.next()) {
			devices devicesDB = new devices();
			devicesDB.setDeviceLot(rsDevices.getString("device_lot"));
			devicesDB.setDeviceMfrLot(rsDevices.getString("mfr_lot"));
			devicesDB.setDevicesQty(rsDevices.getInt("device_qty"));
			devicesDB.setDevicesProject(rsDevices.getString("devices_project"));
			devicesDB.setIsCheckedOut(rsTester.getBoolean("checked_out"));
		}
	}
}
