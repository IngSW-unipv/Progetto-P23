package it.unipv.ingsfw.chess.piece;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;


public abstract class Piece {
	
	protected ChessColor color;
	protected ArrayList<Square> possibleMoves;
	
	
	public Piece(ChessColor c) {
		super();
		this.color = c;
		this.possibleMoves = new ArrayList<Square>();
	}

	public abstract ArrayList<Square> rules();

	public ChessColor getColor() {
		return color;
	}


	public List<Square> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<Square> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	

}
