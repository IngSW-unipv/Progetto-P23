package it.unipv.ingsfw.database;

import java.util.ArrayList;

import it.unipv.ingsfw.classi.User;

public interface IUserDAO {
	public ArrayList<User> selectAll();
	public ArrayList<User> selectStats(User U);
	public boolean insertUser(User U);
}
