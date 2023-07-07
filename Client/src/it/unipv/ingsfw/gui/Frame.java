package it.unipv.ingsfw.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.Controller;
import it.unipv.ingsfw.gui.panels.LoginPanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;

public class Frame  extends JFrame  {

	
	private GamePanel gamePanel;
	private LoginPanel loginPanel;
	private JPanel centerPanel;
	
	private JPanel menu;
	private JButton local;
	private JButton online;

	
	
	public Frame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel ();
		getContentPane().add(centerPanel,BorderLayout.CENTER);
		menu = new JPanel ();
		local = new JButton("LOCALE");
		online = new JButton("ONLINE");
		menu.add(local);
		menu.add(online);
		
		local.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (gamePanel != null) {
					remove(gamePanel);
				
				}
			
				setGamePanel(ChessColor.WHITE,menu);
				Controller sandro = new Controller (new GameModel (), gamePanel, menu);	
			}
		});	
		
		online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			    loginPanel = new LoginPanel (menu, centerPanel);
				menu.setVisible(false);
				centerPanel.add(loginPanel);
			}
		});
	

		
		
		centerPanel.add(menu);
		setSize (800,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public void setGamePanel(ChessColor c, JPanel x) {

		gamePanel = new GamePanel (c);
		gamePanel.setmenu(x);
		
		this.add(gamePanel,BorderLayout.CENTER);
		menu.setVisible(false);


	}
	
	//il padre
	
	public static void main(String[] args) {
		Frame sandro1 = new Frame ("partita");
		
	}
	
	

}
