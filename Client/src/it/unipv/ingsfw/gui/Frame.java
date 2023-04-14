package it.unipv.ingsfw.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import it.unipv.ingsfw.chess.game.Board;
import it.unipv.ingsfw.controller.Controller;

public class Frame  extends JFrame implements ActionListener {

	
	private GamePanel gamePanel;
	private JPanel menu;
	private JButton local;
	/* momentaneo così bisogna gestire multi buttons*/
	
	
	public Frame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		menu = new JPanel ();
		local = new JButton("LOCALE");
		local.addActionListener(this);
		menu.add(local);
		
		
	
		
		
		
		
		add(menu,BorderLayout.CENTER);
	
		
		
		
		
		setSize (800,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	//public static void main(String[] args) {
	//	Frame sandro = new Frame ("partita");
	//}
	
	public static void main(String[] args) {
		Frame sandro1 = new Frame ("partita");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		gamePanel = new GamePanel ();
		menu.setVisible(false);
		add(gamePanel,BorderLayout.CENTER);
		
		Controller sandro = new Controller (new Board (), gamePanel);
		
	}

	

}
