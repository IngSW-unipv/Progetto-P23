package it.unipv.ingsfw.gui;

import java.awt.*;
import javax.swing.*;

public class GameFrame  extends JFrame {

	private Board board;
	private ToolBar toolBar;
	
	
	public GameFrame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		board = new Board ();
		toolBar = new ToolBar ();
		
		add(board,BorderLayout.CENTER);
		add(toolBar,BorderLayout.PAGE_START);
		
		
		
		
		
		setSize ( 800,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
}
