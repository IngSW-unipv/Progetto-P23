package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Direction;
import it.unipv.ingsfw.chess.game.Rules;


public abstract class Piece {

	 protected ChessColor color;
	 protected PieceType type;	
	 protected boolean firstMove;
	 protected List<Rules> directions;
	 


	public Piece (ChessColor color, PieceType type) {
		 this.color = color;
		 this.firstMove = true;
		 this.type = type;
		 directions = new ArrayList<Rules>();
		 initDirections();
	 }
	 
	
	private void initDirections() {
		// TODO Auto-generated method stub
		this.directions.addAll(pieceRules());
	}

	 public List<Rules> getDirections() {
		return directions;
	}
	 
	public void removeDirection(Direction direction) {
		for(Rules r: this.directions) {
			if(r.getDirection() == direction) {
				this.directions.remove(r);
				break;
			}
		}
	}

	public boolean isFirstMove() {
		return firstMove;
	}


	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}


	public ChessColor getColor () {
		 return color;
	 }
	 

	public PieceType getType() {
		return type;
	}


	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + color;
	}
	 
	
	public abstract ArrayList<Rules> pieceRules();

}
