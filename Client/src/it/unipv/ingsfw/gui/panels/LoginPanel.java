package it.unipv.ingsfw.gui.panels;

import javax.swing.*;

import it.unipv.ingsfw.controller.dbobject.User;
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
	private JButton loginButton;
	private JButton signUpButton;
	private JButton back;


	public LoginPanel(JPanel menu, JPanel centerPanel) {

		setLayout(new GridLayout(4, 2, 10, 10));


		this.menu = menu;
		this.centerPanel = centerPanel;
		usernameLabel = new JLabel("Username:");
		usernameField = new JTextField(20);
		passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Accedi");
		signUpButton = new JButton("Registrati");
		back = new JButton("Indietro");

		
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		add(signUpButton);
		add(back);

	}

	public void close() {
		this.setVisible(false);
	}


	public void changeColor () {
		usernameLabel.setForeground(Color.red);
		passwordLabel.setForeground(Color.red);

	}
	

	public JPanel getMenu() {
		return menu;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}
	
	public JButton getSignUpButton() {
		return signUpButton;
	}

	public JPanel getStatsPanel() {
		return statsPanel;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getBack() {
		return back;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	
	

	
}

