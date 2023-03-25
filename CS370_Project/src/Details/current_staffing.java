package Details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

public class current_staffing {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	static void current_shift() throws Exception {
		System.out.println("Current Staffing below:");
		Calendar C = new GregorianCalendar();
        int hour = C.get( Calendar.HOUR_OF_DAY);
        String current_shift;
        
        if (hour > 19 || hour < 7)
        	current_shift = "Night";
        else
        	current_shift = "Day";
        
        Properties p = new Properties();
		
		//Login
		p.put("user", "root");
		p.put("password", "t3$t0573");
		
		//Initialize connection to the database
		Connection c = DriverManager.getConnection(CONNECTION,p);
		
		//Create the database statement
		Statement s = c.createStatement();
        
		//Initialize result set and classes for staffing database
		ResultSet rsStaffing = s.executeQuery("select * from labmap.staffing");
    	while(rsStaffing.next()) {
			if (rsStaffing.getString("shift").equals(current_shift)) {
				System.out.println("Employee Name: " + rsStaffing.getString("employee_name"));
				System.out.println("Role: " + rsStaffing.getString("role") + "\n");
			}
        }
    	System.out.println("______________________________________________________________");
	}
}
