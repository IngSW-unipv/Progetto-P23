package it.unipv.ingsfw.chess.game;

public class Rules {
	private Direction direction;
	private int maxdim;
	
	
	public Rules(Direction direction, int maxdim) {
		this.direction = direction;
		this.maxdim = maxdim;
	}
	
	
	public Rules(Direction direction) {
		this.direction = direction;
	}


	public Direction getDirection() {
		return direction;
	}

	public int getMaxdim() {
		return maxdim;
	}	

}
