package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Board extends JPanel {

	private JToggleButton[][] tasti;
	
	public Board () {
		super();
		tasti = new JToggleButton [8][8];
		
		setLayout(new GridLayout(8,8,0,0));
	/*	ImageIcon rookB = new ImageIcon("");
		ImageIcon rookW = new ImageIcon("/Users/alessandrorotta/desktop/rookW.png");
		ImageIcon queenB = new ImageIcon("/Users/alessandrorotta/desktop/queenB.png");
		ImageIcon queenW = new ImageIcon("/Users/alessandrorotta/desktop/queenW.png");
		ImageIcon bishopB = new ImageIcon("/Users/alessandrorotta/desktop/bishopB.png");
		ImageIcon bishopW = new ImageIcon("/Users/alessandrorotta/desktop/bishopW.png"); */
		ImageIcon kingB = new ImageIcon("/Progetto-P23/Client/img/kingB.png");
	/*	
		ImageIcon kingW = new ImageIcon("/Users/alessandrorotta/desktop/kingW.png");
		ImageIcon pawnB = new ImageIcon("/Users/alessandrorotta/desktop/pawnB.png");
		ImageIcon pawnW = new ImageIcon("/Users/alessandrorotta/desktop/pawnW.png");
		ImageIcon knightB = new ImageIcon("/Users/alessandrorotta/desktop/knightB.png");
		ImageIcon knightW = new ImageIcon("/Users/alessandrorotta/desktop/knightW.png"); */
		
		
		boolean white = true;
		for (int i = 0 ; i <8;  i++) {
			white = !white;
			for (int j = 0 ; j < 8 ;j++) {
				white = !white;
				tasti[i][j] = new JToggleButton () ;
				tasti[i][j].setOpaque(true);
				tasti[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if (!white) {
					tasti[i][j].setBackground(Color.RED.darker());
				}
				else {
					tasti[i][j].setBackground(Color.white.brighter());
				}
				
				
				add(tasti [i][j]);
				
			}
		}
	/*	tasti[1][1].setIcon(rookB);
		tasti[2][1].setIcon(rookW);
		tasti[3][1].setIcon(queenB);
		tasti[4][1].setIcon(queenW);
		tasti[5][1].setIcon(bishopB);
		tasti[6][1].setIcon(bishopW); */
		tasti[6][2].setIcon(kingB);
	/*	
		tasti[6][3].setIcon(kingW);
		tasti[6][4].setIcon(pawnB);
		tasti[6][5].setIcon(pawnW);
		tasti[1][2].setIcon(knightB);
		tasti[1][3].setIcon(knightW); */
		
		
			
	}
}
