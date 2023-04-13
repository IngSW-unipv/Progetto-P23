package it.unipv.ingsfw.chess.pieces;


import java.util.ArrayList;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Direction;
import it.unipv.ingsfw.chess.game.Rules;

public class King extends Piece{


	public King (ChessColor c, PieceType type) {
		super(c, type);
	}


	@Override
	public ArrayList<Rules> directions() {
		ArrayList<Rules> rules = new ArrayList<Rules>();
		rules.add(new Rules(Direction.N,1));
		rules.add(new Rules(Direction.S,1));
		rules.add(new Rules(Direction.E,1));
		rules.add(new Rules(Direction.W,1));
		rules.add(new Rules(Direction.NE,1));
		rules.add(new Rules(Direction.NW,1));
		rules.add(new Rules(Direction.SE,1));
		rules.add(new Rules(Direction.SW,1));
		return rules;
	}
}
