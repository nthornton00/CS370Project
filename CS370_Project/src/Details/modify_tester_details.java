package Details;

import java.sql.*;

public class modify_tester_details {
	public boolean tester_devices(String tester_name, String devices) throws Exception {
		if (tester_details.checked_out(devices))
			return false;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for tester database
		ResultSet rsDevices = s.executeQuery("select * from labmap.devices");
		
		boolean exists = false;
		
		while(rsDevices.next()) {
			if (rsDevices.getString("device_lot").equals(devices)) {
				exists = true;
				break;
			}
		}
		
		if (!exists)
			return false;
		
		String updateDev = "UPDATE labmap.tester SET devices = ? WHERE tester_name = ?";
		try (PreparedStatement updateUser = c.prepareStatement(updateDev)) {
			updateUser.setString(1, devices);
			updateUser.setObject(2, tester_name);
			updateUser.execute();
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		String check_out = "UPDATE labmap.devices SET checked_out = ? WHERE device_lot = ?";
		try (PreparedStatement checkOut = c.prepareStatement(check_out)) {
			checkOut.setInt(1, 1);
			checkOut.setObject(2, devices);
			checkOut.execute();
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		System.out.println("UPDATE TABLE SUCCESSFUL!\n");
		c.close();
		return true;
	}
	
	public void return_devices(String devices) throws Exception {
		if (devices.equals("NONE"))
			return;
		
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		String updateDev = "UPDATE labmap.devices SET checked_out = ? WHERE device_lot = ?";
		try (PreparedStatement updateUser = c.prepareStatement(updateDev)) {
			updateUser.setInt(1, 0);
			updateUser.setObject(2, devices);
			updateUser.execute();
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		System.out.println("UPDATE TABLE SUCCESSFUL!\n");
		c.close();
	}
	
	public void tester_peripheral(String tester_name, String peripheral) throws Exception {
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		String query = "UPDATE labmap.tester SET peripheral = ? WHERE tester_name = ?";
		try (PreparedStatement updateUser = c.prepareStatement(query)) {
			updateUser.setString(1, peripheral);
			updateUser.setObject(2, tester_name);
			updateUser.execute();
			
		} catch(Exception err) {
            System.out.println("An error has occurred.");
            System.out.println("See full details below.");
            err.printStackTrace();
        }
		
		System.out.println("UPDATE TABLE SUCCESSFUL!\n");
		c.close();
		return;
	}
}