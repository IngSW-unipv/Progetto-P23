package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Status;

public class GamePanel extends JPanel{


	private GameBoard gameBoard;
	private GameToolBar gameToolBar;
	private JButton back;
	private Dialog dialog;
	private JPanel mainMenu;
	

	public GamePanel (ChessColor color, JPanel mainMenu) {
		super ();
		this.mainMenu = mainMenu;
		setLayout(new BorderLayout());
		gameBoard = new GameBoard (color);
		gameToolBar = new GameToolBar ();

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






	}

	public GameBoard getGameBoard () {
		return gameBoard;
	}

	public GameToolBar getGameToolBar() {
		return gameToolBar;
	}



	public void updateToolBar (ChessColor c, Status s) {
		gameToolBar.update(c);
		gameToolBar.updateStatus(s);

		if (s == Status.CHECK_MATE) {

			dialog = new Dialog (c,back,gameBoard,gameToolBar,mainMenu);

		}
	}
	
	








}
