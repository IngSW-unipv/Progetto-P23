package it.unipv.ingsfw.controller;

import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.gui.GameBoard;


public abstract class MessageReceivedListener {
	
	public GameModel model;
	public GameBoard viewBoard;
	
	public void onMessageReceived(String message) {};
}
