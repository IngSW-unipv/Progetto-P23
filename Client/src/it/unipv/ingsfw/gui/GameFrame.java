package it.unipv.ingsfw.gui;

import java.awt.*;
import javax.swing.*;

public class GameFrame  extends JFrame {

	private Board board;
	
	
	public GameFrame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		board = new Board ();
		add(board,BorderLayout.CENTER);
		
		
		
		
		
		setSize(700, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
}
