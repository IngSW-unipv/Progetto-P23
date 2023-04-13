package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Move;
import it.unipv.ingsfw.chess.game.Rules;


public abstract class Piece {

	 protected ChessColor color;
	 protected PieceType type;
	 protected boolean attacker;	
	 protected boolean firstMove;
	 protected List<Move> currentMoves;
	 

	 public Piece (ChessColor color, PieceType type) {
		 this.color = color;
		 this.firstMove = true;
		 this.type = type;
	 }
	 
	 
	 public ChessColor getColor () {
		 return color;
	 }
	 

	 public void setAttacker(boolean attacker) {
		 this.attacker = attacker;
	 }


	public PieceType getType() {
		return type;
	}


	public List<Move> getCurrentMoves() {
		return currentMoves;
	}


	public void setCurrentMoves(List<Move> currentMoves) {
		this.currentMoves = currentMoves;
	}

	 
	 @Override
	 public String toString() {
		 return getClass().getSimpleName() + " " + color;
	 }
	 
	 
	 public abstract ArrayList<Rules> directions();

}
