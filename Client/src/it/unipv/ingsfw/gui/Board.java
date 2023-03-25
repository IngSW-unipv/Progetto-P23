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
	//	ImageIcon queenB = new ImageIcon(getClass().getResource("img/queenB.png"));
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
		for (int i = 0 ; i <8;  i++) {
			white = !white;
			for (int j = 0 ; j < 8 ;j++) {
				white = !white;
				if (!white) {
					tasti[i][j] = new GameButton(Color.RED.darker());
				}
				else {
					tasti[i][j] = new GameButton(Color.white.brighter());
				}
				add(tasti [i][j]);
				
			}
		}
		
		
	//	tasti[3][1].setIcon(queenB);
	
		for (int i = 0 ;i < 8 ; i++) {
			tasti[6][i].setIcon(pawnW);
		}
		
		for (int i = 0 ;i < 8 ; i++) {
			tasti[1][i].setIcon(pawnB);
		}
		
		tasti[0][0].setIcon(rookB);
		tasti[0][7].setIcon(rookB);
		
		tasti[7][0].setIcon(rookW);
		tasti[7][7].setIcon(rookW);
		
		tasti[0][1].setIcon(bishopB);
		tasti[0][6].setIcon(bishopB);
		
		tasti[7][1].setIcon(bishopW);
		tasti[7][6].setIcon(bishopW);
		
		tasti[0][2].setIcon(knightB);
		tasti[0][5].setIcon(knightB);
		
		tasti[7][2].setIcon(knightW);
		tasti[7][5].setIcon(knightW);
		
		tasti[0][4].setIcon(kingB);
	//	tasti[0][3].setIcon(queenB);
		
		tasti[7][4].setIcon(kingW);
		tasti[7][3].setIcon(queenW);
		
		
		
		
		
		
		
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
