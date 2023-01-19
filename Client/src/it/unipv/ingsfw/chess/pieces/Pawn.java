package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Pawn extends Piece {
	private boolean firstMove;

	public Pawn (ChessColor c) {
		super(c);
		this.firstMove = true;
		moves = setRules();
	}
	
	//ricontrollare questo metodo
	
	@Override
	public ArrayList <Move> setRules () {
		
		ArrayList <Move> r = new ArrayList <Move> ();
		if(this.color == ChessColor.WHITE) {
			if(firstMove==true) {
				r.add(new Move (Direction.N,2));
			}else {
				r.add(new Move (Direction.N,1));
			}
			r.add(new Move (Direction.NW,1));
			r.add(new Move (Direction.NE,1));
			return r;
		}
		if(firstMove==true) {
			r.add(new Move (Direction.S,2));
		}else {
			r.add(new Move (Direction.S,1));
		}
		r.add(new Move (Direction.SW,1));
		r.add(new Move (Direction.SE,1));
		
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

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return false;
	}
}
