package it.unipv.ingsfw.database;

import java.util.ArrayList;

import it.unipv.ingsfw.classi.Game;


public interface IGameDAO {

	public ArrayList<Game> selectAll();
	
	public boolean insertGame(Game g);
	
}
