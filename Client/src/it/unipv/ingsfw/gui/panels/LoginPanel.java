package it.unipv.ingsfw.gui.panels;

import javax.swing.*;

import it.unipv.ingsfw.chess.dbobject.User;
import it.unipv.ingsfw.controller.OnlineController;
import it.unipv.ingsfw.controller.interfaces.MessageReceivedListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPanel menu;
	private JPanel centerPanel;
	private JPanel statsPanel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private OnlineController oc;
	private User user;


	public LoginPanel(JPanel menu, JPanel centerPanel) {

		setLayout(new GridLayout(4, 2, 10, 10));


		this.menu = menu;
		this.centerPanel = centerPanel;
		usernameLabel = new JLabel("Username:");
		usernameField = new JTextField(20);
		passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(20);
		JButton loginButton = new JButton("Accedi");
		JButton singUpButton = new JButton("Registrati");
		JButton back = new JButton("Indietro");

		//azione tasto login
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				exist(username, new String(password));

			}
		});

		//azione tasto signup
		singUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				registration(username, new String(password));

			}
		});

		//azione tasto back
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				menu.setVisible(true);

			}
		});

		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		add(singUpButton);
		add(back);

	}

	private void close () {
		this.setVisible(false);
	}

	public JPanel createStats() {
		statsPanel = new StatsPanel(menu,oc,centerPanel);
		((StatsPanel) statsPanel).setUser(user);
		centerPanel.add(statsPanel);
		close();
		return statsPanel;
	}

	private void exist (String id , String psw) {
		oc = new OnlineController (null , "127.0.0.1", 1234 ,this );
		user = new User (id , psw);
		oc.setUser(user);


		oc.setMessageReceivedListener(new MessageReceivedListener() {
			@Override
			public void onMessageReceived(String message) {
				// Gestisci l'evento di ricezione del messaggio
				oc.onMessageReceived(message);
			}
		});	
		oc.loginCall();
	}

	private void registration(String id , String psw) {
		oc = new OnlineController (null , "127.0.0.1", 1234,this );
		oc.setUser(new User (id , psw));

		oc.setMessageReceivedListener(new MessageReceivedListener() {
			@Override
			public void onMessageReceived(String message) {
				// Gestisci l'evento di ricezione del messaggio
				oc.onMessageReceived(message);
			}
		});	
		oc.signupCall();
	}

	public void changeColor () {
		usernameLabel.setForeground(Color.red);
		passwordLabel.setForeground(Color.red);

	}


}

