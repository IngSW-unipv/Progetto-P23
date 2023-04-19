package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Direction;
import it.unipv.ingsfw.chess.game.Rules;


public class Queen extends Piece{

	public Queen (ChessColor c, PieceType type) {
		super(c, type);
	}


	@Override
	public ArrayList<Rules> pieceRules() {
		ArrayList<Rules> rules = new ArrayList<Rules>();
		rules.add(new Rules(Direction.N,8));
		rules.add(new Rules(Direction.S,8));
		rules.add(new Rules(Direction.E,8));
		rules.add(new Rules(Direction.W,8));
		rules.add(new Rules(Direction.NE,8));
		rules.add(new Rules(Direction.NW,8));
		rules.add(new Rules(Direction.SE,8));
		rules.add(new Rules(Direction.SW,8));
		return rules;
	}
	
}
