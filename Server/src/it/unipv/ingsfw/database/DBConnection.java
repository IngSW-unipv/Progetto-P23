package it.unipv.ingsfw.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	
	private static final String PROPERTYDBDRIVER = "DBDRIVER";
	private static final String PROPERTYDBURL = "DBURL";
	private static String dbDriver;
	private static String dbURL;
	private static String dbusn;
	private static String dbpsw;
	private static DBConnection conn;
	private static boolean isInit = false;
	private static final String usn = "db_usn";
	private static final String psw = "db_psw";
	
	private static void init() {
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("properties/properties"));
			dbDriver = "com.mysql.cj.jdbc.Driver";//p.getProperty(PROPERTYDBDRIVER);
			dbURL ="jdbc:mysql://127.0.0.1:3306/%s";// p.getProperty(PROPERTYDBURL);//
			dbusn ="username";//p.getProperty(usn); //
			dbpsw = "password";//p.getProperty(psw);//
			
		
			
			isInit = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Connection startConnection(Connection conn, String schema)
	{
		if (!isInit)
			init();
		
		if (isOpen(conn))
			closeConnection(conn);
		try {
			dbURL=String.format(dbURL,schema); 
			Class.forName(dbDriver);
			
			conn = DriverManager.getConnection(dbURL, dbusn, dbpsw);// Apertura connessione
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
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
