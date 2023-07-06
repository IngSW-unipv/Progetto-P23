package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.MessageReceivedListener;
import it.unipv.ingsfw.controller.OnlineController;

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
	private String n;

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public StatsPanel (JPanel menu,OnlineController oc1,JPanel centerPanel) {

		this.oc =oc1;
		this.centerPanel = centerPanel;


		setLayout(new GridLayout(5, 2, 10, 10));
		wn = new JLabel("leggi da database");
		dn = new JLabel("leggi da database");
		ln = new JLabel("leggi da database");
		name = new JLabel("leggi da database");
		JLabel username = new JLabel ("Username:");
		JLabel wins = new JLabel ("Wins:");
		JLabel draws = new JLabel ("Draws:");
		JLabel loses = new JLabel ("Loses:");


		logout = new JButton ("logout");
		play = new JButton ("play");

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

				//setGamePanel(ChessColor.WHITE,menu);
								oc = new OnlineController(new GameModel(), "127.0.0.1", 1234,null);

								oc.setMessageReceivedListener(new MessageReceivedListener() {
						            @Override
						            public void onMessageReceived(String message) {
						               
					                oc.onMessageReceived(message);
						            }
						        });
								oc.setUsername(n);
				oc.playCall();
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gamePanel = oc.getGamePanel();
				setGamePanel(gamePanel);

				oc.run();


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
	public void setStats(String n,String w,String d,String l) {
		name.setText(n);
		
		wn.setText(w);
		dn.setText(d);
		ln.setText(l);
	}

	//	public void setGamePanel(GamePanel p , JPanel x) {
	//
	//
	//		p.setmenu(x);
	//		this.add(p,BorderLayout.CENTER);
	//		//		this.setVisible(false);
	//
	//
	//	}

	public void setGamePanel(GamePanel gamep ) {


		gamep.setmenu(this);
		centerPanel.add(gamep,BorderLayout.CENTER);
		this.setVisible(false);


	}






}
