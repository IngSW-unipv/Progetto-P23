package it.unipv.ingsfw.chess.piece.pieces;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class Knight extends Piece{

	public Knight(ChessColor c) {
		super(c);

	}
	
	@Override
	public void addMoves() {
		this.allMoves.add(new Square(1,-2));
		this.allMoves.add(new Square(2,-1));
		this.allMoves.add(new Square(2,1));
		this.allMoves.add(new Square(1,2));
		this.allMoves.add(new Square(-1,2));
		this.allMoves.add(new Square(-2,1));
		this.allMoves.add(new Square(-2,-1));
		this.allMoves.add(new Square(-1,-2));

	}
	
	

}
