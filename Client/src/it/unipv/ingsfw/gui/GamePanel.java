package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Status;

public class GamePanel extends JPanel{


	private GameBoard gameBoard;
	private GameToolBar gameToolBar;
	private JButton back;
	private JDialog dialog;
	

	public GamePanel (ChessColor color, JPanel mainMenu) {
		super ();
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

			dialog = new JDialog();
            dialog.setSize(300, 300);
            dialog.setLocationRelativeTo(null);

            // Crea una label per visualizzare il testo nel JDialog
            JLabel label = new JLabel(c.oppositeColor(c).toString() + " wins.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            dialog.getContentPane().add(label);
            
            //crea il bottone 
            
            dialog.getContentPane().add(back, BorderLayout.SOUTH);

            // Mostra il JDialog
            dialog.setVisible(true);


		}
	}
	
	








}
