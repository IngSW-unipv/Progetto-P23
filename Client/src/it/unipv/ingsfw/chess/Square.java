package it.unipv.ingsfw.chess;

import it.unipv.ingsfw.chess.piece.Piece;

public class Square {
	private int x, y;
	private Piece piece;
	
	
	
	public Square(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.piece = null;
	}



	public Square(int x, int y, Piece piece) {
		super();
		this.x = x;
		this.y = y;
		this.piece = piece;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Piece getPiece() {
		return piece;
	}


}
