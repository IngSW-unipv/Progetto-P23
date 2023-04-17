package it.unipv.ingsfw.gui.buttons;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;


public class GameButton extends JButton   {
	
	private int chessX , chessY ;
	private JLabel idAlto;
	private JLabel idBasso;
	public Color mainColor;
	private Color otherColor ;
	
	

	public GameButton (Color mainColor,int chessX , int chessY) {

		super();
		
		idAlto = new JLabel();
		idBasso = new JLabel();
		this.chessX = chessX;
		this.chessY = chessY;
		this.mainColor = mainColor;
		otherColor = new Color (253,203,211);
		
		
		
		add(idAlto);
		add(idBasso);
		setOpaque(true);
		setBackground(mainColor);
		setBorder(BorderFactory.createEmptyBorder());
		
		
		
	}
	
	public int getChessX () {
		return chessX;
	}
	
	public int getChessY () {
		return chessY;
	}
	
	public void setIdAlto (String strng) {
		
	
		setLayout(null);
		idAlto.setText(strng);
		idAlto.setBounds(1, 1, 10, 10);
		
		
	}

	public void setIdBasso (String strng) {
		
		
		setLayout(null);
		idBasso.setText(strng);
		idBasso.setBounds(89, 78, 10, 10);
		
		
	}
	
	
	public void color () {
		setBackground(otherColor);
	}
	
	public void reColor () {
		setBackground(mainColor);
	}


	

	





}
