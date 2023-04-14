package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unipv.ingsfw.gui.buttons.GameButton;



public class GameBoard extends JPanel implements ActionListener  {



	private GameButton [][] tasti;
	private int xPrimo , yPrimo , xSecondo , ySecondo ;
	private boolean primosetted = false;
	private Color darkGreen;
	private Color lightGreen;
	private Color originalColor;
	private ImageIcon rookB = new ImageIcon(getClass().getResource("img/rookB.png"));
	private ImageIcon rookW = new ImageIcon(getClass().getResource("img/rookW.png"));
	private ImageIcon queenB = new ImageIcon(getClass().getResource("img/queenB.png"));
	private ImageIcon queenW = new ImageIcon(getClass().getResource("img/queenW.png"));
	private ImageIcon bishopB = new ImageIcon(getClass().getResource("img/bishopB.png"));
	private ImageIcon bishopW = new ImageIcon(getClass().getResource("img/bishopW.png")); 
	private ImageIcon kingB = new ImageIcon(getClass().getResource("img/kingB.png"));
	private ImageIcon kingW = new ImageIcon(getClass().getResource("img/kingW.png"));
	private ImageIcon pawnB = new ImageIcon(getClass().getResource("img/pawnB.png"));
	private ImageIcon pawnW = new ImageIcon(getClass().getResource("img/pawnW.png"));
	private ImageIcon knightB = new ImageIcon(getClass().getResource("img/knightB.png"));
	private ImageIcon knightW = new ImageIcon(getClass().getResource("img/knightW.png")); 

	


	public GameBoard () {
		super();
		

	
		
		
		setPreferredSize(new Dimension (800,800));
		darkGreen = new Color (8,115,0);
		lightGreen = new Color (223,253,214);		
		tasti = new GameButton [8][8];
		
		


		setLayout(new GridLayout(8,8,0,0));
		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));



		boolean white = true;
		for (int y = 0 ; y <8;  y++) {
			white = !white;
			for (int x = 0 ; x < 8 ;x++) {
				white = !white;
				if (!white) {
					tasti[x][y] = new GameButton(darkGreen,x,y);
					tasti[x][y].addActionListener(this);
					
				}
				else {
					tasti[x][y] = new GameButton(lightGreen,x,y);
					tasti[x][y].addActionListener(this);
					
				}
				add(tasti [x][y]);

			}
		}




		tasti[0][0].setIdAlto("8");
		tasti[0][1].setIdAlto("7");
		tasti[0][2].setIdAlto("6");
		tasti[0][3].setIdAlto("5");
		tasti[0][4].setIdAlto("4");
		tasti[0][5].setIdAlto("3");
		tasti[0][6].setIdAlto("2");
		tasti[0][7].setIdAlto("1");
		
		tasti[0][7].setIdBasso("A");
		tasti[1][7].setIdBasso("B");
		tasti[2][7].setIdBasso("C");
		tasti[3][7].setIdBasso("D");
		tasti[4][7].setIdBasso("E");
		tasti[5][7].setIdBasso("F");
		tasti[6][7].setIdBasso("G");
		tasti[7][7].setIdBasso("H");
		

	}
	
	




	// usata dal controller

	@Override
	public void actionPerformed(ActionEvent e) {

		
		Icon moveThis;
		GameButton p =(GameButton)e.getSource();
		
		
		if (!primosetted ) {
			xPrimo = p.getChessX();
			yPrimo = p.getChessY();
			

			originalColor = p.getBackground();
	
			p.setBackground(new Color (255,172,172));
			
			primosetted =  true ;
		}
		else {
			xSecondo = p.getChessX();
			ySecondo = p.getChessY();
			
			
			moveThis = tasti[xPrimo][yPrimo].getIcon();
			tasti[xPrimo][yPrimo].setIcon(null);
			tasti[xPrimo][yPrimo].setBackground(originalColor);
			tasti[xSecondo][ySecondo].setIcon(moveThis);
			
			moveThis = null;
			primosetted = false;


		}

	}


	public GameButton[][] getTasti() {
		return tasti;
	}






	public ImageIcon getRookB() {
		return rookB;
	}






	public ImageIcon getRookW() {
		return rookW;
	}






	public ImageIcon getQueenB() {
		return queenB;
	}






	public ImageIcon getQueenW() {
		return queenW;
	}






	public ImageIcon getBishopB() {
		return bishopB;
	}






	public ImageIcon getBishopW() {
		return bishopW;
	}






	public ImageIcon getKingB() {
		return kingB;
	}






	public ImageIcon getKingW() {
		return kingW;
	}






	public ImageIcon getPawnB() {
		return pawnB;
	}






	public ImageIcon getPawnW() {
		return pawnW;
	}






	public ImageIcon getKnightB() {
		return knightB;
	}






	public ImageIcon getKnightW() {
		return knightW;
	}






	public void setRookB(ImageIcon rookB) {
		this.rookB = rookB;
	}






	public void setRookW(ImageIcon rookW) {
		this.rookW = rookW;
	}






	public void setQueenB(ImageIcon queenB) {
		this.queenB = queenB;
	}






	public void setQueenW(ImageIcon queenW) {
		this.queenW = queenW;
	}






	public void setBishopB(ImageIcon bishopB) {
		this.bishopB = bishopB;
	}






	public void setBishopW(ImageIcon bishopW) {
		this.bishopW = bishopW;
	}






	public void setKingB(ImageIcon kingB) {
		this.kingB = kingB;
	}






	public void setKingW(ImageIcon kingW) {
		this.kingW = kingW;
	}






	public void setPawnB(ImageIcon pawnB) {
		this.pawnB = pawnB;
	}






	public void setPawnW(ImageIcon pawnW) {
		this.pawnW = pawnW;
	}






	public void setKnightB(ImageIcon knightB) {
		this.knightB = knightB;
	}






	public void setKnightW(ImageIcon knightW) {
		this.knightW = knightW;
	}

	
	


	
	

}

