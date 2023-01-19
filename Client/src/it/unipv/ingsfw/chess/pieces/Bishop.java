package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Bishop extends Piece{

	public Bishop (ChessColor c) {
		super(c);
		moves = setRules();
	}
	
	@Override
	public ArrayList <Move> setRules () {
		
		ArrayList <Move> r = new ArrayList <Move> ();
		
		r.add(new Move (Direction.NE,8));
		r.add(new Move (Direction.SE,8));
		r.add(new Move (Direction.SW,8));
		r.add(new Move (Direction.NW,8));
		
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
		return false;
	}
}
