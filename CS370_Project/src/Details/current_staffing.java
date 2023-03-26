package Details;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class current_staffing {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	public static void current_shift() throws Exception {
		System.out.println("Current Staffing below:");
		Calendar C = new GregorianCalendar();
        int hour = C.get( Calendar.HOUR_OF_DAY);
        String current_shift;
        
        if (hour > 19 || hour < 7)
        	current_shift = "Night";
        else
        	current_shift = "Day";
        
        //Initialize connection to the database
  		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement();
		
		//Initialize result set and classes for staffing database
		ResultSet rsStaffing = s.executeQuery("SELECT * FROM labmap.staffing ORDER BY employee_firstname ASC");
    	while(rsStaffing.next()) {
			if (rsStaffing.getString("shift").equals(current_shift)) {
				System.out.print("Employee Name: " + rsStaffing.getString("employee_firstname"));
				System.out.println(" " + rsStaffing.getString("employee_lastname"));
				System.out.println("Badge ID: " + rsStaffing.getInt("badge_ID"));
				System.out.println("Role: " + rsStaffing.getString("role"));
				System.out.println("Shift: " + rsStaffing.getString("shift") + "\n");
			}
        }
    	
    	System.out.println("______________________________________________________________");
    	c.close();
	}
}
