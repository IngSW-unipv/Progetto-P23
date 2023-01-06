package it.unipv.ingsfw.chess.piece.pieces;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Pawn extends Piece{
	
	private boolean firstMove;

	public Pawn(ChessColor c) {
		super(c);
		this.firstMove = true;
	}
	
	
	@Override
	public void addMoves() {
		this.allMoves.add(new Square(0,1));
		this.allMoves.add(new Square(0,2));
		this.allMoves.add(new Square(-1,1));
		this.allMoves.add(new Square(1,1));
	}


	public boolean isFirstMove() {
		return firstMove;
	}


	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

}
