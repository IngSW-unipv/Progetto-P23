package it.unipv.ingsfw.chess.game;

import it.unipv.ingsfw.chess.pieces.Piece;

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


}
