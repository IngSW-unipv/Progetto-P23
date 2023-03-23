package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Board extends JPanel {

	private JToggleButton[][] tasti;
	
	public Board () {
		super();
		tasti = new JToggleButton [8][8];
		
		setLayout(new GridLayout(8,8,0,0));
		
		
		boolean white = true;
		for (int i = 0 ; i <8;  i++) {
			white = !white;
			for (int j = 0 ; j < 8 ;j++) {
				white = !white;
				tasti[i][j] = new JToggleButton () ;
				tasti[i][j].setOpaque(true);
				tasti[i][j].setBorder(BorderFactory.createLineBorder(Color.GREEN.darker()));
				if (!white) {
					tasti[i][j].setBackground(Color.green.brighter());
				}
				else {
					tasti[i][j].setBackground(Color.white.brighter());
				}
				
				add(tasti [i][j]);
			}
		}
			
	}
}
