package it.unipv.ingsfw.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	private static final String PROPERTYDBURL = "db.url";
	private static final String PROPERTYDRIVER = "db.driver";
	private static final String PROPERTYNAME = "db.username";
	private static final String PROPERTYPSW = "db.password";
	private static String dbURL;
	private static String dbDRIVER;
	private static String dbusn;
	private static String dbpsw;

	
	private static void init() {
		Properties p = new Properties();
		
		try {
			p.load(new FileInputStream("properties/properties"));
			dbURL = p.getProperty(PROPERTYDBURL);
			dbDRIVER = p.getProperty(PROPERTYDRIVER);
			dbusn = p.getProperty(PROPERTYNAME);
			dbpsw = p.getProperty(PROPERTYPSW);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Connection startConnection(Connection conn, String schema)
	{
		
        if (conn != null) {
            return conn;
        }
    	try {
    		init();
    		dbURL = String.format(dbURL,schema); 
    		Class.forName(dbDRIVER);
    		conn = DriverManager.getConnection(dbURL, dbusn, dbpsw);
    		return conn;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
	}


	public static boolean isOpen(Connection conn) {
		return !(conn == null);
	}

	public static Connection closeConnection(Connection conn)
	{
		if (!isOpen(conn)) {
			return null;
		}
		
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}
