package it.unipv.ingsfw.classi;

import java.sql.Date;

public class Game {
	private String gameCode;
	private User whitePlayer,blackPlayer;
	private Date d;
	public Game(String gameCode, User whitePlayer, User blackPlayer) {
		super();
		this.gameCode = gameCode;
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
	}
	public Game(String gameCode, String whitePlayer, String blackPlayer) {
		super();
		this.gameCode = gameCode;
		this.whitePlayer = new User(whitePlayer);
		this.blackPlayer = new User(blackPlayer);
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getWhitePlayer() {
		return whitePlayer.toString();
	}
	public void setWhitePlayer(User whitePlayer) {
		this.whitePlayer = whitePlayer;
	}
	public String getBlackPlayer() {
		return blackPlayer.toString();
	}
	public void setBlackPlayer(User blackPlayer) {
		this.blackPlayer = blackPlayer;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}



}
