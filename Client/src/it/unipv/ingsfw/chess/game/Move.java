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




	public Square getInitialPosition() {
		return initialPosition;
	}


	public Square getFinalPosition() {
		return finalPosition;
	}
	
	
	public boolean isSpecialMove() {
		return specialMove;
	}


	public boolean equals(Move m) {
		if(this.initialPosition.equals(m.initialPosition) && this.finalPosition.equals(m.finalPosition)) {
			return true;
		}
		return false;
	}


}
