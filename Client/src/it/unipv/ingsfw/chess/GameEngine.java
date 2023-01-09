package it.unipv.ingsfw.chess;

import it.unipv.ingsfw.chess.board.Board;
import it.unipv.ingsfw.chess.color.ChessColor;

public class GameEngine {
	private Board game;
	private Player p1, p2;

	public GameEngine() {
		super();
		this.game = new Board();
		this.p1 = new Player(ChessColor.WHITE);
		this.p2 = new Player(ChessColor.BLACK);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
