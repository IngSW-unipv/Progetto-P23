package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Board extends JPanel {

	private GameButton [][] tasti;
	
	public Board () {
		super();
		tasti = new GameButton [8][8];
		
		setLayout(new GridLayout(8,8,0,0));
		
	    ImageIcon rookB = new ImageIcon(getClass().getResource("img/rookB.png"));
		ImageIcon rookW = new ImageIcon(getClass().getResource("img/rookW.png"));
		ImageIcon queenB = new ImageIcon(getClass().getResource("img/queenB.png"));
		ImageIcon queenW = new ImageIcon(getClass().getResource("img/queenW.png"));
		ImageIcon bishopB = new ImageIcon(getClass().getResource("img/bishopB.png"));
		ImageIcon bishopW = new ImageIcon(getClass().getResource("img/bishopW.png")); 
		ImageIcon kingB = new ImageIcon(getClass().getResource("img/kingB.png"));
		ImageIcon kingW = new ImageIcon(getClass().getResource("img/kingW.png"));
		ImageIcon pawnB = new ImageIcon(getClass().getResource("img/pawnB.png"));
		ImageIcon pawnW = new ImageIcon(getClass().getResource("img/pawnW.png"));
		ImageIcon knightB = new ImageIcon(getClass().getResource("img/knightB.png"));
		ImageIcon knightW = new ImageIcon(getClass().getResource("img/knightW.png")); 
		
		
		boolean white = true;
		for (int y = 0 ; y <8;  y++) {
			white = !white;
			for (int x = 0 ; x < 8 ;x++) {
				white = !white;
				if (!white) {
					tasti[x][y] = new GameButton(new Color (110,247,69));
				}
				else {
					tasti[x][y] = new GameButton(new Color (223,253,214));
				}
				add(tasti [x][y]);
				
			}
		}
		
		
	
	
		for (int i = 0 ;i < 8 ; i++) {
			tasti[i][6].setIcon(pawnW);
		}
		
		for (int i = 0 ;i < 8 ; i++) {
			tasti[i][1].setIcon(pawnB);
		}
		
		tasti[0][0].setIcon(rookB);
		tasti[7][0].setIcon(rookB);
		
		tasti[0][7].setIcon(rookW);
		tasti[7][7].setIcon(rookW);
		
		tasti[2][0].setIcon(bishopB);
		tasti[5][0].setIcon(bishopB);
		
		tasti[2][7].setIcon(bishopW);
		tasti[5][7].setIcon(bishopW);
		
		tasti[1][0].setIcon(knightB);
		tasti[6][0].setIcon(knightB);
		
		tasti[1][7].setIcon(knightW);
		tasti[6][7].setIcon(knightW);
		
		tasti[4][0].setIcon(kingB);
		tasti[3][0].setIcon(queenB);
		
		tasti[4][7].setIcon(kingW);
		tasti[3][7].setIcon(queenW);
		
		
		
		
		
		
		
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
