package it.unipv.ingsfw.classi;

public class User {


	private String username,psw;
	
	public User(String username, String psw) {
		this.username = username;
		this.psw = psw;
		
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