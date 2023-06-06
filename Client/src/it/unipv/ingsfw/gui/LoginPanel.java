package it.unipv.ingsfw.gui;

import javax.swing.*;

import it.unipv.ingsfw.chess.dbobject.User;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.controller.MessageReceivedListener;
import it.unipv.ingsfw.controller.OnlineController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel() {
    	
        setLayout(new GridLayout(3, 2, 10, 10));
        
        

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Log in");
        JButton singUpButton = new JButton("Sing up");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // Esegui l'autenticazione o l'azione desiderata qui
                OnlineController sandro2 = new OnlineController(new GameModel(), "127.0.0.1", 1234);
				sandro2.setMessageReceivedListener(new MessageReceivedListener() {
		            @Override
		            public void onMessageReceived(String message) {
		                // Gestisci l'evento di ricezione del messaggio
		                sandro2.onMessageReceived(message);
		            }
		        });
				sandro2.setUser(new User(username,new String(password)));
				// roba di ale
                System.out.println("Username: " + username);
                System.out.println("Password: " + new String(password));
            }
        });
        
        singUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // Esegui l'autenticazione o l'azione desiderata qui
                System.out.println("Username: " + username);
                System.out.println("Password: " + new String(password));
            }
        });
        
       

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add (singUpButton);
    }
}

