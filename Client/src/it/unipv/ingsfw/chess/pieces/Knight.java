package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Direction;
import it.unipv.ingsfw.chess.game.Rules;


public class Knight extends Piece{

	public Knight (ChessColor c, PieceType type) {
		super(c, type);
	}


	@Override
	public ArrayList<Rules> pieceRules() {
		ArrayList<Rules> rules = new ArrayList<Rules>();
		rules.add(new Rules(Direction.L));
		return rules;
	}

}
