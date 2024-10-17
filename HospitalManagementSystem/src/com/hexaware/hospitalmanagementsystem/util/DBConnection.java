package com.hexaware.hospitalmanagementsystem.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
   
	private static Connection conn;

	public static Connection getDBConn() {
		
		
		try {
			FileReader reader = new FileReader("resources/DbConfig.properties");
			Properties prop = new Properties();
			prop.load(reader);
			
			String driverName = prop.getProperty("driverName");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
				Class.forName(driverName);
			
			conn = DriverManager.getConnection(url, username, password);
		}catch(SQLException e) {
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		catch (IOException e) {
		
			e.printStackTrace();
		}


		return conn;
	}

	public static void dbClose() {
		try {
			conn.close();
			// System.out.println("Connection closed..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
