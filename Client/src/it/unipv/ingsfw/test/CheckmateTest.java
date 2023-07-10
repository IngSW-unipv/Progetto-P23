package it.unipv.ingsfw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.chess.game.Move;
import it.unipv.ingsfw.chess.game.Square;
import it.unipv.ingsfw.chess.game.Status;


public class CheckmateTest {

	@Test
	public void checkmate() {
		
		GameModel gameModel = new GameModel();
		
		gameModel.makeMove(new Move(new Square(4,6), new Square(4,4))); //pedoneB e2e4
		gameModel.makeMove(new Move(new Square(5,1), new Square(5,3))); //pedoneN f7f5
		gameModel.makeMove(new Move(new Square(3,6), new Square(3,4))); //pedoneB d2d4
		gameModel.makeMove(new Move(new Square(6,1), new Square(6,3))); //pedoneN g7g5
		gameModel.makeMove(new Move(new Square(3,7), new Square(7,3))); //reginaB d1h5
		
		assertTrue(gameModel.isCheckMate());
		
		assertEquals(Status.CHECK_MATE,gameModel.getGameStatus());
		
	}
}
