package it.unipv.ingsfw.chess.game;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Pawn;
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
	
	public PieceType getPieceType() {
		if(this.piece == null) {
			return null;
		}
		return piece.getType();
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
	
	@Override
	public String toString () {
		return Integer.toString(x) + Integer.toString(y);
	}	

	public static void main(String[] args) {
		Square s = new Square(new Pawn(ChessColor.WHITE,PieceType.Pawn),1,2);
		Square s1 = new Square(2,2);
		Piece p = s.getPiece();
		s.releasePiece();
		s1.setPiece(p);
		System.out.println(p.getType());
	}
}

	
	
	


