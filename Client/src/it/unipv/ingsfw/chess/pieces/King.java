package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class King extends Piece{


	public King (ChessColor c) {
		super(c);
		moves = setRules();
	}
	
	@Override
	public ArrayList <Move> setRules () {
		
		ArrayList <Move> r = new ArrayList <Move> ();
		
		r.add(new Move (Direction.NE,1));
		r.add(new Move (Direction.SE,1));
		r.add(new Move (Direction.SW,1));
		r.add(new Move (Direction.NW,1));
		r.add(new Move (Direction.N,1));
		r.add(new Move (Direction.E,1));
		r.add(new Move (Direction.S,1));
		r.add(new Move (Direction.W,1));
		
		return r;
	}
	
	@Override
	public  ArrayList <Move> getRules(){
		return moves;
	}

	@Override
	public boolean isPawn() {
		return false;
	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return true;
	}
	
		
	@Override
	public void setValidDirections (Direction d) {
		 if (d == Direction.N || d == Direction.S ) {
			 validDirections.add(new Move(Direction.N,1) );
			 validDirections.add(new Move(Direction.S,1) );
		 }
		 
		 if (d == Direction.W  || d == Direction.E ) {
			 validDirections.add(new Move(Direction.W,1) );
			 validDirections.add(new Move(Direction.E,1) );
		 }
		 if (d == Direction.NE || d == Direction.SW ) {
			 validDirections.add(new Move(Direction.NE,1) );
			 validDirections.add(new Move(Direction.SW,1) );
		 }
		 if (d == Direction.NW || d == Direction.SE ) {
			 validDirections.add(new Move(Direction.NW,1) );
			 validDirections.add(new Move(Direction.SE,1) );
		 }
	}
}
