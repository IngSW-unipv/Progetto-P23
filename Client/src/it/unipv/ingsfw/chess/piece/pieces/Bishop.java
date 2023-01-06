package it.unipv.ingsfw.chess.piece.pieces;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Bishop extends Piece{
	final static int maxSquareMove = 8;
	
	
	public Bishop(ChessColor c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMoves() {
		
		for(int i=1;i<maxSquareMove;i++) {
			this.allMoves.add(new Square(i,i));
			this.allMoves.add(new Square(i,-i));
			this.allMoves.add(new Square(-i,i));
			this.allMoves.add(new Square(-i,-i));
		}
	}

}
