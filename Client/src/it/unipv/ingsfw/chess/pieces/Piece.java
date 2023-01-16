package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;
import it.unipv.ingsfw.chess.board.Square;

public abstract class Piece {

	 protected ArrayList <Move> moves;
	 protected ChessColor color;
	 private ArrayList <Square> validMoves;
	 
	 
	 public Piece (ChessColor color) {
		 this.color = color;
		 moves = this.setRules();
		 validMoves = new ArrayList <Square> ();
		 
	 }
	 
	 public ChessColor getColor () {
		 return color;
	 }
	 
	 public ArrayList <Square> getValidMoves (){
		 
		 return validMoves;
	 }
	 
	 
	 
	 
	 public abstract ArrayList <Move> setRules ();
	 public abstract ArrayList <Move> getRules ();
	 public abstract boolean isPawn();
}
