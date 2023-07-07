package it.unipv.ingsfw.gui.panels.gamepanels;

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

		button1 = new JButton ("Nuova Partita");
		button2 = new JButton ("Indietro");
		
		label = new JLabel ();
		status = new JLabel ();
		setLayout(new FlowLayout (FlowLayout.LEFT));
		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

		add(button1);
		add(button2);
		add(label);
		add(status);
		
		
		

	}
	
	public GameToolBar(int i) {
		super ();
		
		
		button1 = new JButton ("Resa");
		
		
		label = new JLabel ();
		status = new JLabel ();
		setLayout(new FlowLayout (FlowLayout.LEFT));
		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));

		add(button1);
		add(label);
		add(status);
		
		
		

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
			label.setText("Bianco");
		}else {
			label.setText("Nero");
		}

	}

	public void updateStatus (Status s) {
		status.setText(s.toString());
	}






}
