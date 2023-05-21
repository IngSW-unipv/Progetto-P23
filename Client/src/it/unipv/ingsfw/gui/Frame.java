package it.unipv.ingsfw.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Board;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.Controller;
import it.unipv.ingsfw.controller.MessageReceivedListener;
import it.unipv.ingsfw.controller.OnlineController;

public class Frame  extends JFrame  {

	
	private GamePanel gamePanel;
	private LoginPanel loginPanel;
	
	private JPanel menu;
	private JButton local;
	private JButton online;
	
	
	
	public Frame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		JPanel centerPanel = new JPanel ();
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
				gamePanel = new GamePanel (ChessColor.WHITE,menu);
				//gamePanel = new GamePanel (ChessColor.BLACK);
				menu.setVisible(false);
				centerPanel.add(gamePanel);
				
				Controller sandro = new Controller (new GameModel (), gamePanel);	
			}
		});		
	
		online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null) {
					remove(gamePanel);
				}
				
				setGamePanel(ChessColor.WHITE,menu);
				OnlineController sandro2 = new OnlineController(new GameModel(), gamePanel, "127.0.0.1", 1234);
				sandro2.setMessageReceivedListener(new MessageReceivedListener() {
		            @Override
		            public void onMessageReceived(String message) {
		                // Gestisci l'evento di ricezione del messaggio
		                sandro2.onMessageReceived(message);
		            }
		        });				
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

		gamePanel = new GamePanel (c,x);
		this.add(gamePanel,BorderLayout.CENTER);
		menu.setVisible(false);


	}
	
	public static void main(String[] args) {
		Frame sandro1 = new Frame ("partita");
		
	}
	
	

}
