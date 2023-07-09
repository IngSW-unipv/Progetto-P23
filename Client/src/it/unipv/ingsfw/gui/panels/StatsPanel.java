package it.unipv.ingsfw.gui.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unipv.ingsfw.controller.dbobject.User;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.OnlineController;
import it.unipv.ingsfw.controller.interfaces.MessageReceivedListener;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;

public class StatsPanel extends JPanel  {

	private JLabel name;
	private JLabel wn ;
	private JLabel dn ;
	private JLabel ln ;
	private JButton logout;
	private JButton play;
	private GamePanel gamePanel;
	private OnlineController oc;
	private JPanel centerPanel;
	private WaitingPanel wait;
	private User user;



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public StatsPanel (JPanel menu,OnlineController oc1,JPanel centerPanel) {

		this.oc =oc1;
		this.centerPanel = centerPanel;


		setLayout(new GridLayout(5, 2, 10, 10));
		wn = new JLabel("leggi da database");
		dn = new JLabel("leggi da database");
		ln = new JLabel("leggi da database");
		name = new JLabel("leggi da database");
		wait = new WaitingPanel();
		JLabel username = new JLabel ("Username:");
		JLabel wins = new JLabel ("Vittorie:");
		JLabel draws = new JLabel ("Pareggi:");
		JLabel loses = new JLabel ("Sconfitte:");


		logout = new JButton ("Esci");
		play = new JButton ("Gioca");

		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				menu.setVisible(true);

			}
		});

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null) {
					remove(gamePanel);
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


		add(username);
		add(name);
		add(wins);
		add(wn);
		add(draws);
		add(dn);
		add(loses);
		add(ln);
		add(logout);
		add(play);




	}

	private void close () {
		this.setVisible(false);
	}
	public StatsPanel getStatsPanel() {
		return this;
	}
	public void setStats(String n,String w,String d,String l) {
		name.setText(n);

		wn.setText(w);
		dn.setText(d);
		ln.setText(l);
	}

	public void setGamePanel(GamePanel gamep ) {


		gamep.setmenu(this);
		centerPanel.add(gamep,BorderLayout.CENTER);
		this.setVisible(false);
		if (wait != null) {
			wait.setVisible(false);
		}


	}
	
	public void setWaiting() {


		wait.setmenu(this);
		centerPanel.add(wait,BorderLayout.CENTER);
		this.setVisible(false);


	}
	
	public void setGame () {
		gamePanel = oc.getGamePanel();
		setGamePanel(gamePanel);
		oc.run();
	}






}
