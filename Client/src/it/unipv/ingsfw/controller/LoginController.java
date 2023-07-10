package it.unipv.ingsfw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import it.unipv.ingsfw.controller.interfaces.MessageReceivedListener;
import it.unipv.ingsfw.dbobject.User;
import it.unipv.ingsfw.gui.panels.LoginPanel;

public class LoginController {
	
	private LoginPanel loginPanel;
	private OnlineController oc;
	private JButton loginButton;
	private JButton back;
	private JButton signUpButton;
	private User user;
	
	
	public LoginController(LoginPanel loginPanel) {
		super();
		this.loginPanel = loginPanel;
		this.loginButton = loginPanel.getLoginButton();
		this.signUpButton = loginPanel.getSignUpButton();
		this.back = loginPanel.getBack();
		init();
	}
	

	public void init() {
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginPanel.getUsernameField().getText();
				char[] password = loginPanel.getPasswordField().getPassword();
				if (username.length() > 30 || password.length > 30) {
					loginPanel.changeColor();
				}
				else {
					exist(username, new String(password));
				}
			}
		});
		
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginPanel.getUsernameField().getText();
				char[] password = loginPanel.getPasswordField().getPassword();
				if (username.length() > 30 || password.length > 30) {
					loginPanel.changeColor();
				}
				else {
					registration(username, new String(password));
				}


			}
		});
		
		//azione tasto back
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.close();
				loginPanel.getMenu().setVisible(true);

			}
		});
	}
	
	
	public void exist (String id , String psw) {
		oc = new OnlineController (null , "127.0.0.1", 1234, loginPanel);
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
		oc = new OnlineController (null , "127.0.0.1", 1234, loginPanel);
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
}
