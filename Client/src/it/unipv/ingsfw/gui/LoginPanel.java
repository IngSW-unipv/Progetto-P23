package it.unipv.ingsfw.gui;

import javax.swing.*;
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

