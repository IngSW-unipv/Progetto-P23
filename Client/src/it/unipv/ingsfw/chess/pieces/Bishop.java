package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Bishop extends Piece{

	public Bishop (ChessColor c) {
		super(c);
		rules.add(new Rules (Direction.D1,8));
		rules.add(new Rules (Direction.D2,8));

		validDirections.add(Direction.D1);
		validDirections.add(Direction.D2);
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
