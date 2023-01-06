package it.unipv.ingsfw.chess.piece.pieces;

import it.unipv.ingsfw.chess.Square;
import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.Piece;

public class King extends Piece{

	public King(ChessColor c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public void addMoves() {
		this.allMoves.add(new Square(0,1));
		this.allMoves.add(new Square(-1,1));
		this.allMoves.add(new Square(1,1));
		this.allMoves.add(new Square(0,-1));
		this.allMoves.add(new Square(1,-1));
		this.allMoves.add(new Square(-1,-1));
	}

}
