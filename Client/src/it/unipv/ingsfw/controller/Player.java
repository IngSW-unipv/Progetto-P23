package it.unipv.ingsfw.controller;

import it.unipv.ingsfw.chess.ChessColor;

public class Player {
	public ChessColor color ;
	
	
	
	public Player(ChessColor c) {
		color=c;
	}



	public ChessColor getColor() {
		return color;
	}



	public void setColor(ChessColor color) {
		this.color = color;
	}
}
