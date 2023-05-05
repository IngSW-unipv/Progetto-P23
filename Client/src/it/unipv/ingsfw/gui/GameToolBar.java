package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import it.unipv.ingsfw.chess.game.Status;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.ingsfw.chess.ChessColor;

public class GameToolBar extends JPanel  {

	private JButton button1;
	private JButton button2;
	private JLabel label;
	private JLabel status;



	public GameToolBar () {
		super ();

		button1 = new JButton ("nuova partita");
		button2 = new JButton ("resa");
		label = new JLabel ("");
		status = new JLabel ("");
		setLayout(new FlowLayout (FlowLayout.LEFT));

		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

		add(button1);
		add(button2);
		add(label);
		//add(status);

	}


	public JButton getButton1() {
		return button1;
	}


	public JButton getButton2() {
		return button2;
	}



	public JLabel getStatus() {
		return status;
	}


	public void update (ChessColor c) {
		if(c == ChessColor.WHITE) {
			label.setText("white turn");
		}else {
			label.setText("black turn");
		}

	}

	public void updateStatus (Status s) {
		label.setText(s.toString());
	}






}
