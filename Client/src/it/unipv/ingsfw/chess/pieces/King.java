package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;

public class King extends Piece{


	public King (ChessColor c) {
		super(c);
		rules.add(new Rules(Direction.V,1));
		rules.add(new Rules(Direction.H,1));
		rules.add(new Rules(Direction.D1,1));
		rules.add(new Rules(Direction.D2,1));
		
		
		validDirections.add(Direction.V);
		validDirections.add(Direction.D1);
		validDirections.add(Direction.D2);
		validDirections.add(Direction.H);

	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
