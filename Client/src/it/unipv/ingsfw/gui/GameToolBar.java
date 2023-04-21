package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class GameToolBar extends JPanel  {

	private JButton button1;
	private JButton button2;
	private JLabel label;
	
	
	
	public GameToolBar () {
		super ();
		
		button1 = new JButton ("nuova partita");
		button2 = new JButton ("resa");
		label = new JLabel ("white turn");
		setLayout(new FlowLayout (FlowLayout.LEFT));
		
		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		add(button1);
		add(button2);
		add(label);
	
			
		
	}


	public JButton getButton1() {
		return button1;
	}
	
	public void update () {
		
		if (label.getText().equals("white turn")) {
			label.setText("black turn");
		}
		else {
			label.setText("white turn");
		}
	}


	


	
	
}
