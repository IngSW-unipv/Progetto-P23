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
		
		tasti[1][1].setIcon(rookB);
		tasti[2][1].setIcon(rookW);
	//	tasti[3][1].setIcon(queenB);
		tasti[4][1].setIcon(queenW);
		tasti[5][1].setIcon(bishopB);
		tasti[6][1].setIcon(bishopW); 
		tasti[6][2].setIcon(kingB);
		tasti[6][3].setIcon(kingW);
		tasti[6][4].setIcon(pawnB);
		tasti[6][5].setIcon(pawnW);
		tasti[1][2].setIcon(knightB);
		tasti[1][3].setIcon(knightW); 
		
		
			
	}
}
