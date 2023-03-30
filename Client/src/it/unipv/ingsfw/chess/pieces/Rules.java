package it.unipv.ingsfw.chess.pieces;

import it.unipv.ingsfw.chess.Direction;

public class Rules {

	private Direction d;
	private int max;



	public Rules (Direction d , int max) {
		this.d = d;
		this.max = max;
	}

	public Direction getDirection () {
		return d;
	}
	
	public int getMax () {
		return max;
	}

}
