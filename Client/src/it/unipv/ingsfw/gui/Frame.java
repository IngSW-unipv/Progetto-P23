package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.gui.panels.LoginPanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;

public class Frame  extends JFrame  {

	private GamePanel gamePanel;
	private LoginPanel loginPanel;
	private JPanel centerPanel;
	private JPanel menu;
	private JButton local;
	private JButton online;

	
	
	public Frame () {
		super("PepeChess");
		
		setLayout(new BorderLayout());
		centerPanel = new JPanel ();
		getContentPane().add(centerPanel,BorderLayout.CENTER);
		menu = new JPanel ();
		local = new JButton("LOCALE");
		online = new JButton("ONLINE");
		menu.add(local);
		menu.add(online);
		centerPanel.add(menu);
		
		
		setSize (800,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	
	public JPanel getMenu() {
		return menu;
	}

	public JButton getLocal() {
		return local;
	}

	public JButton getOnline() {
		return online;
	}

	public void setOnline(JButton online) {
		this.online = online;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(ChessColor c, JPanel menu) {

		gamePanel = new GamePanel (c);
		gamePanel.setmenu(menu);
		
		centerPanel.add(gamePanel,BorderLayout.CENTER);
		menu.setVisible(false);
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}
	
	
	
	

}
