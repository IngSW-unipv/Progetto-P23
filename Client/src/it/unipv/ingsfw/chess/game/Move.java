package it.unipv.ingsfw.chess.game;

import it.unipv.ingsfw.chess.pieces.Piece;

public class Move {
	
	private Square initialPosition;
	private Square finalPosition;
	private Piece pInit;
	private Piece pFin;
	
		
	public Move(Square initialPosition, Square finalPosition) {
		super();
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		this.pInit = initialPosition.getPiece();
		if(finalPosition.getPiece() != null) {
			this.pFin = finalPosition.getPiece();
		}
	}
	
	public Move(String st) {
		 char s[] = st.toCharArray();
	     int x1 = Character.getNumericValue(s[0]);
	     int y1 = Character.getNumericValue(s[1]);
	     int x2 = Character.getNumericValue(s[2]);
	     int y2 = Character.getNumericValue(s[3]);
	     this.initialPosition = new Square(x1,y1);
	     this.finalPosition = new Square(x2,y2);
	     
	}
	

	public Square getInitialPosition() {
		return initialPosition;
	}


	public Square getFinalPosition() {
		return finalPosition;
	}
	
	
	public Piece getpInit() {
		return pInit;
	}


	public Piece getpFin() {
		return pFin;
	}


	public boolean equals(Move m) {
		if(this.initialPosition.equals(m.initialPosition) && this.finalPosition.equals(m.finalPosition)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.initialPosition.toString() + this.finalPosition.toString();
	}
}
