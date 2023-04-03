package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;


public class GameButton extends JButton implements ActionListener  {
	
	private int chessX , chessY ;
	private JLabel idAlto;
	private JLabel idBasso;
	

	public GameButton (Color C,int chessX , int chessY) {

		super();
		
		idAlto = new JLabel();
		idBasso = new JLabel();
		this.chessX = chessX;
		this.chessY = chessY;
		
		
		add(idAlto);
		add(idBasso);
		addActionListener(this);
		setOpaque(true);
		setBackground(C);
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

	@Override
	  public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }

	

	





}
