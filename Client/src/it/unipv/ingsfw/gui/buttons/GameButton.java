package it.unipv.ingsfw.gui.buttons;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import it.unipv.ingsfw.chess.game.Square;


public class GameButton extends JButton   {
	
	private JLabel idAlto;
	private JLabel idBasso;
	public Color mainColor;
	private Color otherColor ;
	private Square position;
	
	
	
	public GameButton (Color mainColor,Square position) {

		super();
		
		idAlto = new JLabel();
		idBasso = new JLabel();
		this.position = position;
		this.mainColor = mainColor;
		
		
		
		add(idAlto);
		add(idBasso);
		setOpaque(true);
		setBackground(mainColor);
		setBorder(BorderFactory.createEmptyBorder());
		
		
		
	}
	

	
	public Square getChessPosition () {
		return position;
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
		Color change = mainColor;
		setBackground(change.darker().darker());
	}
	
	
	public void highlightPiece() {
		Color change = mainColor;
		setBackground(change.brighter().brighter());
	}
	
	
	public void reColor () {
		setBackground(mainColor);
	}


	

	





}
