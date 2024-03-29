
package it.unipv.ingsfw.database;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.classi.User;

public class UserDAO implements IUserDAO{

	private String schema;
	private Connection conn;

	public UserDAO() {
		super();
		this.schema = "pepethechess";
	}

	public ArrayList<User> selectAll(){
		ArrayList<User> userList = new ArrayList<>();
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query = "SELECT * FROM UTENTI";
			rs1 = st1.executeQuery(query);

			while(rs1.next()) {

				User user = new User (rs1.getString(1));
				userList.add(user);

			}


		} catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return userList;

	}

	public ArrayList<User> selectStats(User U){

		ArrayList<User> result = new ArrayList<>();
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try{
			st1 = conn.createStatement();
			String query = "SELECT * FROM UTENTI";
			rs1 = st1.executeQuery(query);

			while(rs1.next()) {
				User user = new User (rs1.getString(1), rs1.getString(2));
			}
		} catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}

	public User validUser(String username) {
		User result = null;
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query = "SELECT * FROM UTENTI WHERE USERNAME=? ";
			st1 = conn.prepareStatement(query);
			st1.setString(1,username);
			rs1 = st1.executeQuery();

			while(rs1.next()) {
				result = new User(rs1.getString(1), rs1.getString(2));
			}

		} catch (Exception e) {e.printStackTrace();}

		DBConnection.closeConnection(conn); 
		return result;

	}
	public User setStats(String username) {
		User result = null;
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query = "SELECT * FROM UTENTI WHERE USERNAME=? ";
			st1 = conn.prepareStatement(query);
			st1.setString(1,username);
			rs1 = st1.executeQuery();

			while(rs1.next()) {

				result = new User(rs1.getString(1), rs1.getString(2),rs1.getInt(3),rs1.getInt(5),rs1.getInt(4));
			}

		} catch (Exception e) {e.printStackTrace();}

		DBConnection.closeConnection(conn); 
		return result;
	}
	public void addWin(String username) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		try {

			String query = "UPDATE UTENTI SET WIN=WIN+1  WHERE USERNAME=?";
			st1 = conn.prepareStatement(query);
			
			st1.setString(1, username);

			st1.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addLose(String username) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		try {

			String query = "UPDATE UTENTI SET LOSE=LOSE+1  WHERE USERNAME=?";
			st1 = conn.prepareStatement(query);
			
			st1.setString(1, username);
			
			st1.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addDraw(String username) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		try {

			String query = "UPDATE UTENTI SET DRAW=DRAW+1  WHERE USERNAME=?";
			st1 = conn.prepareStatement(query);
			
			st1.setString(1, username);
			
			st1.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean insertUser(User u) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;
		boolean esito = true;

		try {

			String query = "INSERT INTO UTENTI(username,password,win,draw,lose) VALUES(?,?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setString(1, u.getUsername());
			st1.setString(2, u.getPsw());
			st1.setInt(3, u.getWin());
			st1.setInt(4, u.getDraw());
			st1.setInt(5, u.getLose());

			st1.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
}

