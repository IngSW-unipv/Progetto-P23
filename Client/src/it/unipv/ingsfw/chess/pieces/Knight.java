package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class Knight extends Piece{

	public Knight (ChessColor c) {
		super(c);
		rules.add(new Rules (Direction.L,0));
		
		validDirections.add(Direction.L);
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

	@Override
	public boolean isKnight() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
