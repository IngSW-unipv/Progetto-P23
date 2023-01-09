package it.unipv.ingsfw.chess.board;

import it.unipv.ingsfw.chess.piece.Piece;

public class Square implements Comparable<Square>{
	
	private Piece piece;
	private int x, y;
	
	
	

	public Square(int x, int y) {
		super();
		this.x=x;
		this.y=y;
	}


	public Square(int x, int y, Piece piece) {
		super();
		this.x=x;
		this.y=y;
		this.piece=piece;
		// TODO Auto-generated constructor stub
	}




	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
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

	public boolean isEmpty() {
		if(this.piece==null) {
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Square o) {
		if(this.x != o.x) {
			return this.x - o.x;
		}else if(this.y != o.y) {
			return this.y - o.y;
		}
		return 0;
	}

	
	


}
