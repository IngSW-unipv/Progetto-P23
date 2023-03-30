package it.unipv.ingsfw.chess.board;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;

/* è il contenitore dei pezzi può essere vuoto */

public class Square {
	

	private Piece piece;
	private int x,y;
	
	public Square (Piece piece,int x ,int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	
	public Square (int x ,int y) {
		this.piece = null;
		this.x = x;
		this.y = y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece p) {
		this.piece = p;

	}
	
	public boolean isOccupied () {
		if (piece != null) return true;
		else return false;
	}
	
	public ChessColor getPieceColor () {
		return piece.getColor();
	}

	public boolean isKing () {
		return piece.isKing();
	}
	
	public String toString () {
		return "x:" + x +" y:" + y;
	}
	
	
	}

	
	
	


