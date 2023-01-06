package it.unipv.ingsfw.chess.piece.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.board.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Rook extends Piece{
	final static int maxSquareMove = 8;

	public Rook(ChessColor c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public ArrayList<Square> rules() {
		ArrayList<Square> rules = new ArrayList<Square>();
		for(int i=1;i<maxSquareMove;i++) {
			rules.add(new Square(0,i));
			rules.add(new Square(i,0));
			rules.add(new Square(0,-i));
			rules.add(new Square(-i,0));
		}
		return rules;
	}

}
