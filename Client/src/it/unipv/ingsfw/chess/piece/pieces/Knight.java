package it.unipv.ingsfw.chess.piece.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.board.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Knight extends Piece{

	public Knight(ChessColor c) {
		super(c);

	}
	
	
	@Override
	public ArrayList<Square> rules() {
		ArrayList<Square> rules = new ArrayList<Square>();
		rules.add(new Square(1,-2));
		rules.add(new Square(2,-1));
		rules.add(new Square(2,1));
		rules.add(new Square(1,2));
		rules.add(new Square(-1,2));
		rules.add(new Square(-2,-1));
		rules.add(new Square(-2,-1));
		rules.add(new Square(-1,-2));
		return rules;
	}
	
	

}
