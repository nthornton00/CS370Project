package Details;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;


public class establish_connection {
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	private static Properties login = new Properties();
	
	private static Connection c; //Connection
	
	public static void login() throws Exception {	
		
		try (Scanner myObj = new Scanner(System.in)) {
			//System.out.print("Enter username: ");
			//login.put("user", myObj.nextLine());
			//System.out.print("Enter password: ");
			//login.put("password", myObj.nextLine());
			login.put("user", "root");
			login.put("password", "t3$t0573");
		}
		
		System.out.println();
		
		System.out.println("LOGIN SUCCESSFUL");
		
	}
	
	public static Connection connect() throws Exception {
		//Initialize connection to the database
		c = DriverManager.getConnection(CONNECTION,login);
		
		return c;
	}
	
	static void close_con() throws Exception {
		c.close();
	}
}