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
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

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

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginPanel loginPanel = new LoginPanel();
        frame.getContentPane().add(loginPanel);

        frame.pack();
        frame.setVisible(true);
    }
}

