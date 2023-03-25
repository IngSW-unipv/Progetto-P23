package it.unipv.ingsfw.gui;

import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.JButton;


public class GameButton extends JButton {

	public GameButton (Color C) {


		super();
		setOpaque(true);
		setBackground(C);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}





}
