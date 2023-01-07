package it.unipv.ingsfw.chess.piece.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.board.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Bishop extends Piece{
	final static int maxSquareMove = 8;
	
	
	public Bishop(ChessColor c) {
		super(c);
		// TODO Auto-generated constructor stub
	}


	public ArrayList<Square> rules() {
		ArrayList<Square> rules = new ArrayList<Square>();
		for(int i=1;i<maxSquareMove;i++) {
			rules.add(new Square(i,i));
			rules.add(new Square(i,-i));
			rules.add(new Square(-i,i));
			rules.add(new Square(-i,-i));
		}
		return rules;
	}

}
