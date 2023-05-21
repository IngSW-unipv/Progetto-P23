package it.unipv.ingsfw.chess.game;


public class Move {
	
	private Square initialPosition;
	private Square finalPosition;
	private boolean specialMove;
	
		
	public Move(Square initialPosition, Square finalPosition, boolean specialMove) {
		super();
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		this.specialMove = specialMove;
	}
	
	
	public Move(Square initialPosition, Square finalPosition) {
		super();
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		this.specialMove=false;
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
	
	
	public boolean isSpecialMove() {
		return specialMove;
	}

	public String toString() {
		return initialPosition.toString()+finalPosition.toString();
	}

	public boolean equals(Move m) {
		if(this.initialPosition.equals(m.initialPosition) && this.finalPosition.equals(m.finalPosition)) {
			return true;
		}
		return false;
	}


}
