package it.unipv.ingsfw.classi;

public class User {

	private String username;
	private String password;
	private Stats stats;
	
	
	public User(String username,String password) {
		this.password=password;
		this.username=username;
	}


	public User(String username) {
		this.username=username;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return username;
	}


	public Stats getStats() {
		return stats;
	}



	

}