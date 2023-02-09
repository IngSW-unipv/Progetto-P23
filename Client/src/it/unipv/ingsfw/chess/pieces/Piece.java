package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;
import it.unipv.ingsfw.chess.board.Square;

public abstract class Piece {

	 protected ArrayList <Move> moves;
	 protected ArrayList <Square> validMoves;
	 protected ArrayList <Move> validDirections;
	 protected ChessColor color;
	
	 
	 
	 public Piece (ChessColor color) {
		 this.color = color;
		 moves = this.setRules();
		 validDirections = new ArrayList <Move> ();
		 validDirections = moves;
		 validMoves = new ArrayList <Square> ();
		 
	 }
	 
	 public ChessColor getColor () {
		 return color;
	 }
	 
	 public ArrayList <Square> getValidMoves (){
		 
		 return validMoves;
	 }
	 
	 public ArrayList <Move>  getValidDirections () {
		 return validDirections;
	 }
	 
	 
	 public void setValidDirections (Direction d) {
		 if (d == Direction.N || d == Direction.S ) {
			 validDirections.add(new Move(Direction.N,8) );
			 validDirections.add(new Move(Direction.S,8) );
		 }
		 
		 if (d == Direction.W  || d == Direction.E ) {
			 validDirections.add(new Move(Direction.W,8) );
			 validDirections.add(new Move(Direction.E,8) );
		 }
		 if (d == Direction.NE || d == Direction.SW ) {
			 validDirections.add(new Move(Direction.NE,8) );
			 validDirections.add(new Move(Direction.SW,8) );
		 }
		 if (d == Direction.NW || d == Direction.SE ) {
			 validDirections.add(new Move(Direction.NW,8) );
			 validDirections.add(new Move(Direction.SE,8) );
		 }
		 
	 }
	 
	 
	 
	 public abstract ArrayList <Move> setRules ();
	 public abstract ArrayList <Move> getRules ();
	 public abstract boolean isPawn();
	 public abstract boolean isKing();
}
