package it.unipv.ingsfw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.gui.Frame;
import it.unipv.ingsfw.gui.panels.LoginPanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;

public class MenuController {
	
	private Frame frame;
	private GamePanel gamePanel;
	private LoginPanel loginPanel;
	private JPanel centerPanel;
	private JPanel menu;
	private JButton local;
	private JButton online;

	public MenuController() {
		super();
		this.frame = new Frame();
		this.gamePanel = frame.getGamePanel();
		this.loginPanel = frame.getLoginPanel();
		this.centerPanel = frame.getCenterPanel();
		this.menu = frame.getMenu();
		this.local = frame.getLocal();
		this.online = frame.getOnline();
		init();
		
	}

	public void init() {

		local.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null) {
					frame.remove(gamePanel);
				}
				frame.setGamePanel(ChessColor.WHITE, menu);
				gamePanel = frame.getGamePanel();
				GameController localGame = new GameController(new GameModel (), gamePanel, menu);	
			}
		});	
		
		online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			    frame.setLoginPanel(new LoginPanel ( menu, centerPanel));
			    loginPanel = frame.getLoginPanel();
				menu.setVisible(false);
				centerPanel.add(frame.getLoginPanel());
				LoginController controller = new LoginController(loginPanel);
			}
		});
		
	}
}
