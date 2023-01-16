package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Pawn extends Piece {

	public Pawn (ChessColor c) {
		super(c);
		moves = setRules();
	}
	
	@Override
	public ArrayList <Move> setRules () {
		
		ArrayList <Move> r = new ArrayList <Move> ();
		
		r.add(new Move (Direction.N,1));
		r.add(new Move (Direction.NW,1));
		r.add(new Move (Direction.NE,1));
		
		return r;
	}
	
	@Override
	public  ArrayList <Move> getRules(){
		return moves;
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
