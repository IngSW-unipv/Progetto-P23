package it.unipv.ingsfw.chess.board;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;



public class Square {
	

	private Piece piece;
	private int x, y;

	public Square (int x, int y,Piece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}
	public Square (int x, int y) {
		this.x = x;
		this.y = y;
		this.piece = null;

	}

	public ChessColor getColor () {
		if (piece == null) {
			return null;
		}
		return piece.getColor();
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public Piece getPiece() {
		return piece;
	}

	public boolean isInBoard () {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		return true;
	}

	public boolean isOccupied () {
		if (piece == null) {
			return false;
		}
		return true;
	}
	
	public boolean isKing () {
		return piece.isKing();
	}
	
	}

	
	
	


