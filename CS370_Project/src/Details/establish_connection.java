package Details;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class establish_connection {
	private static final String dbClassname = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/labmap";
	
	private static String username;
	private static String password;
	
	private static Connection c; //Connection
	
	static void login() throws Exception {		
		Scanner myObj = new Scanner(System.in);
		
		System.out.print("Enter username: ");
		username = myObj.nextLine();
		System.out.print("Enter password: ");
		password = myObj.nextLine();
		System.out.println();
	}
	
	static Connection connect() throws Exception {
		Properties p = new Properties();
		
		//Login
		p.put("user", username);
		p.put("password", password);
		
		//Initialize connection to the database
		c = DriverManager.getConnection(CONNECTION,p);
		
		return c;
	}
	
	static void close_con() throws Exception {
		c.close();
	}
}
