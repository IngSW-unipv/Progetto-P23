package it.unipv.ingsfw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.dbobject.User;
import it.unipv.ingsfw.controller.interfaces.MessageReceivedListener;
import it.unipv.ingsfw.gui.panels.LoginPanel;
import it.unipv.ingsfw.gui.panels.StatsPanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;

public class StatsController {
	
	private StatsPanel statsPanel;
	private LoginPanel loginPanel;
	private OnlineController oc;
	private User user;
	private JButton logout, play;
	private GamePanel gamePanel;

	public StatsController(LoginPanel loginPanel, OnlineController oc, User user) {
		super();
		this.oc = oc;
		this.user = user;
		this.loginPanel = loginPanel;
		this.statsPanel = new StatsPanel(loginPanel.getMenu(),oc,loginPanel.getCenterPanel());
		this.logout = statsPanel.getLogout();
		this.play = statsPanel.getPlay();
		this.gamePanel = statsPanel.getGamePanel();
		init();

	}
	
	public void init() {
		statsPanel.setUser(user);
		loginPanel.getCenterPanel().add(statsPanel);
		loginPanel.close();
		
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				statsPanel.close();
				oc.closeConnection();
				loginPanel.getMenu().setVisible(true);

			}
		});

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null) {
					statsPanel.remove(gamePanel);
				}


				oc = new OnlineController(new GameModel(), "127.0.0.1", 1234,null);

				oc.setMessageReceivedListener(new MessageReceivedListener() {
					@Override
					public void onMessageReceived(String message) {

						oc.onMessageReceived(message);
					}
				});
				oc.setUser(user);
				oc.setStatsPanel(getStatsPanel());

				oc.playCall();


			}
		});
	}

	public StatsPanel getStatsPanel() {
		return statsPanel;
	}
	

}
