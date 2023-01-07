package it.unipv.ingsfw.chess.piece.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.board.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Pawn extends Piece{
	
	private boolean firstMove;

	public Pawn(ChessColor c) {
		super(c);
		this.firstMove = true;
	}
	
	
	@Override
	public ArrayList<Square> rules() {
		ArrayList<Square> rules = new ArrayList<Square>();
		rules.add(new Square(0,1));
		rules.add(new Square(1,1));
		rules.add(new Square(-1,1));
		return rules;
	}


	public boolean isFirstMove() {
		return firstMove;
	}


	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
	

}
