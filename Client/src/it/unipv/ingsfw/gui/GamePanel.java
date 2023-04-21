package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel{


	private GameBoard gameBoard;
	private GameToolBar gameToolBar;

	public GamePanel () {
		super ();
		setLayout(new BorderLayout());
		gameBoard = new GameBoard ();
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
	
	public void updateToolBar () {
		gameToolBar.update();
	}

	
	


}
