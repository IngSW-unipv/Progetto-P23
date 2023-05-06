package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import it.unipv.ingsfw.chess.ChessColor;

public class GamePanel extends JPanel{


	private GameBoard gameBoard;
	private GameToolBar gameToolBar;

	public GamePanel (ChessColor color) {
		super ();
		setLayout(new BorderLayout());
		gameBoard = new GameBoard (color);
		gameToolBar = new GameToolBar ();
		add(gameBoard,BorderLayout.CENTER);
		add(gameToolBar,BorderLayout.PAGE_START);
	}
	
	public GameBoard getGameBoard () {
		return gameBoard;
	}

	public GameToolBar getGameToolBar() {
		return gameToolBar;
	}
	
	public void updateToolBar (ChessColor c) {
		gameToolBar.update(c);
	}

	
	


}
