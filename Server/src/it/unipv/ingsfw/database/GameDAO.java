package it.unipv.ingsfw.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.classi.Game;


public class GameDAO {

	private String schema;
	private Connection conn;

	public GameDAO() {
		super();
		this.schema = "PEPETHECHESS";
		this.conn = null;
		conn=DBConnection.startConnection(conn,schema);
	}

	public ArrayList<Game> selectAll(){
		ArrayList<Game> gameList = new ArrayList<>();
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query = "SELECT * FROM PARTITE";
			rs1 = st1.executeQuery(query);

			while(rs1.next()) {

				Game game = new Game (rs1.getInt(1), rs1.getString(2),rs1.getString(3));
				gameList.add(game);

			}


		} catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return gameList;

	}
	public boolean insertGame(Game g) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;
		boolean esito = true;

		try {

			String query = "INSERT INTO PARTITE VALUES(?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, g.getGameCode());
			st1.setString(2, g.getWhitePlayer());
			st1.setString(3, g.getBlackPlayer());

			st1.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}
}
