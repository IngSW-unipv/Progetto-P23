package it.unipv.ingsfw.chess.game;


public class Move {
	
	private Square initialPosition;
	private Square finalPosition;
	private Direction d;
	
	
	public Move (Square initialPosition, Square finalPosition, Direction d) {
		super();
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
		this.d = d;
	}
	
	public Move(Square initialPosition, Square finalPosition) {
		super();
		this.initialPosition = initialPosition;
		this.finalPosition = finalPosition;
	}


	public Square getInitialPosition() {
		return initialPosition;
	}


	public Square getFinalPosition() {
		return finalPosition;
	}
	
	
	public Direction getD() {
		return d;
	}
	
	public boolean equals(Move m) {
		if(this.initialPosition.equals(m.initialPosition) && this.finalPosition.equals(m.finalPosition)) {
			return true;
		}
		return false;
	}


}
