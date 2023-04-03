package it.unipv.ingsfw.gui;

import java.awt.*;
import javax.swing.*;

public class GameFrame  extends JFrame {

	private GameBoard gameBoard;
	private ToolBar toolBar;
	
	
	
	public GameFrame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		gameBoard = new GameBoard ();
		toolBar = new ToolBar ();
		
		
		
	
		
		add(gameBoard,BorderLayout.CENTER);
		add(toolBar,BorderLayout.PAGE_START);
		
		
		
		
		
		
		
		setSize (800,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public GameBoard getGameBoard () {
		return gameBoard;
	}
	

}
