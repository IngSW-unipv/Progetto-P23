package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Pawn extends Piece {
	
	private boolean firstMove;

	public Pawn (ChessColor c) {
		super(c);
		this.firstMove = true;
		
	 // inizialiazzazione 
		
		rules.add(new Rules (Direction.V,1));
        rules.add(new Rules (Direction.D1,1));
		rules.add(new Rules (Direction.D2,1));
		
		validDirections.add(Direction.V);
	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	

}
