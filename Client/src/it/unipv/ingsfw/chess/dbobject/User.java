
package it.unipv.ingsfw.chess.dbobject;

public class User {

	private String username,psw;
	private int win,draw,lose;
	
	public User(String username, String psw) {
		this.username = username;
		this.psw = psw;
		
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public User(String string) {
		this.username = string;
	}

	public String getUsername() {
		return username;
	}

	public String getPsw() {
		return psw;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}



}

