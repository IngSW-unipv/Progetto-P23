package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Rook extends Piece{

	public Rook (ChessColor c) {
		super(c);
		rules.add(new Rules (Direction.V,8));
		rules.add(new Rules (Direction.H,8));
		
		validDirections.add(Direction.V);
		validDirections.add(Direction.H);

	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
	

