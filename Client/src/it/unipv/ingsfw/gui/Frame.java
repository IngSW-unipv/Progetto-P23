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
	private JButton onlineRapid;
	
	
	
	public Frame (String xxx) {
		super(xxx);
		
		setLayout(new BorderLayout());
		
		JPanel centerPanel = new JPanel ();
		getContentPane().add(centerPanel,BorderLayout.CENTER);
		menu = new JPanel ();
		local = new JButton("LOCALE");
		online = new JButton("ONLINE");
		onlineRapid = new JButton ("veloce");
		menu.add(local);
		menu.add(online);
		menu.add(onlineRapid);
		
		local.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (gamePanel != null) {
					remove(gamePanel);
				
				}
				//gamePanel = new GamePanel (ChessColor.WHITE);
				//gamePanel = new GamePanel (ChessColor.BLACK);
				//menu.setVisible(false);
				//centerPanel.add(gamePanel);
				
				setGamePanel(ChessColor.WHITE,menu);
				Controller sandro = new Controller (new GameModel (), gamePanel);	
			}
		});	
		
		online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			    loginPanel = new LoginPanel (menu);
				menu.setVisible(false);
				centerPanel.add(loginPanel);
			}
		});
	
		onlineRapid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null) {
					remove(gamePanel);
				}
				
			    //setGamePanel(ChessColor.WHITE,menu);
				OnlineController sandro2 = new OnlineController(new GameModel(), "127.0.0.1", 1234);
				gamePanel = sandro2.getGamePanel();
				setGamePanel(gamePanel,menu);
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

		gamePanel = new GamePanel (c);
		gamePanel.setmenu(x);
		
		this.add(gamePanel,BorderLayout.CENTER);
		menu.setVisible(false);


	}
	public void setGamePanel(GamePanel p , JPanel x) {

		
		p.setmenu(x);
		this.add(p,BorderLayout.CENTER);
		menu.setVisible(false);


	}
	
	public static void main(String[] args) {
		Frame sandro1 = new Frame ("partita");
		
	}
	
	

}
