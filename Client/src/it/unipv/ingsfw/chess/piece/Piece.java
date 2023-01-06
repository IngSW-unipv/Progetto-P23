package it.unipv.ingsfw.chess.piece;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;


public abstract class Piece {
	
	protected ChessColor color;
	protected List<Square> allMoves;
	
	
	public Piece(ChessColor c) {
		super();
		this.color = c;
		this.allMoves = new ArrayList<Square>();
		addMoves();
	}

	public abstract void addMoves();

	public ChessColor getColor() {
		return color;
	}


	public List<Square> getAllMoves() {
		return allMoves;
	}

	

}
