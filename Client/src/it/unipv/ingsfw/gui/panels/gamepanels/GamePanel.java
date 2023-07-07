package it.unipv.ingsfw.gui.panels.gamepanels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JPanel;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Status;
import it.unipv.ingsfw.gui.Dialog;

public class GamePanel extends JPanel{


	private GameBoard gameBoard;
	private GameToolBar gameToolBar;
	private JButton back;
	private Dialog dialog;
	private JPanel mainMenu;
	
	

	public GamePanel (ChessColor color) {
		super ();
		setLayout(new BorderLayout());
		gameBoard = new GameBoard (color);
		gameToolBar = new GameToolBar ();

		add(gameBoard,BorderLayout.CENTER);
		add(gameToolBar,BorderLayout.PAGE_START);
		repaint();
		
		
		 back = new JButton("Chiudi");
         back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mainMenu.setVisible(true);
					gameBoard.setVisible(false);
					gameToolBar.setVisible(false);
					dialog.dispose();
					
				}
			});
	}

	public GamePanel (ChessColor color,int i) {
		super ();
		setLayout(new BorderLayout());
		gameBoard = new GameBoard (color);
		gameToolBar = new GameToolBar (i);

		add(gameBoard,BorderLayout.CENTER);
		add(gameToolBar,BorderLayout.PAGE_START);
		
		
		 back = new JButton("Chiudi");
         back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mainMenu.setVisible(true);
					gameBoard.setVisible(false);
					gameToolBar.setVisible(false);
					dialog.dispose();
					
				}
			});
         
         repaint();
	}
	
	

	
	
	public GameBoard getGameBoard () {
		return gameBoard;
	}

	public GameToolBar getGameToolBar() {
		return gameToolBar;
	}
	
	public void setGameToolBar (GameToolBar g) {
		this.gameToolBar = g;
		
	}



	public void updateToolBar (ChessColor c, Status s) {
		gameToolBar.update(c);
		gameToolBar.updateStatus(s);

		if (s == Status.CHECK_MATE || s == Status.BLACK_WIN || s == Status.WHITE_WIN) {

			dialog = new Dialog (c,back,gameBoard,gameToolBar,mainMenu);

		}
		
	}
	
	public void setmenu (JPanel menu) {
		mainMenu = menu ;
	}
	

	public void close () {
		this.setVisible(false);
	}








}
