package it.unipv.ingsfw.chess.piece.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class King extends Piece{

	public King(ChessColor c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public void addMoves() {

	}
	
	@Override
	public ArrayList<Square> rules() {
		ArrayList<Square> rules = new ArrayList<Square>();
		rules.add(new Square(0,1));
		rules.add(new Square(-1,1));
		rules.add(new Square(1,1));
		rules.add(new Square(0,-1));
		rules.add(new Square(1,-1));
		rules.add(new Square(-1,-1));
		return rules;
	}

}
