package dev.llewellyn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Singleton Design Pattern

public class ConnectionUtil {

	private static ConnectionUtil cu;
	private static Properties dbProps;
	
	// Private constructor
	private ConnectionUtil() {
		// Initialize properties object to hold DB credentials
		dbProps = new Properties();
		
		// Stream the credentials from our connection.properties file to this object
		InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
		
		// Try to load props from file into properties object
		try {
			dbProps.load(props);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Public getter to return an instance of this class
	public static synchronized ConnectionUtil getConnectionUtil() {
		// Check if instance already exists
		if (cu == null) {
			// Create one if it doesn't
			cu = new ConnectionUtil();
		}
		
		// Return instance
		return cu;
	}
	
	// Method to establish connection to the DB
	public Connection getConnection() {
		// Get a connection
		Connection conn = null;
		
		// Get the necessary fields from dbProps
		String url = dbProps.getProperty("url");
		String username = dbProps.getProperty("username");
		String password = dbProps.getProperty("password");
		
		// Try using the DriverManager to get a connection to the DB
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Return conn if connection was established
		return conn;
	}
}
