package it.unipv.ingsfw.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JToggleButton;

public class GameButton extends JToggleButton {
	
	public GameButton (Color C) {
		super();
		setOpaque(true);
		setBackground(C);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}

}
