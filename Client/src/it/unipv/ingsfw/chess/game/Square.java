package it.unipv.ingsfw.chess.game;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.PieceType;



public class Square{
	

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
		if (piece != null) 
			return true;
		else 
			return false;
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


	public ChessColor getPieceColor () {
		if(this.piece == null) {
			return null;
		}
		return piece.getColor();
	}
	
	public void releasePiece() {
		this.piece = null;
	}
	
	public boolean equals(Square s) {
		if(this.x == s.x && this.y == s.y) {
			return true;
		}
		return false;
	}

	public boolean isKing () {
		if(this.piece != null && this.piece.getType() == PieceType.King){
			return true;
		}
		return false;
	}
	
	public String getPieceString() { 
		if(this.piece == null) {
			return " ";
		}
		return piece.toString();
	}
	
	public String toString () {
		return "x:" + this.x +" y:" + this.y;
	}	
}

	
	
	


