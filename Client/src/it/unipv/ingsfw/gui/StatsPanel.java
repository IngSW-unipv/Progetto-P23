package it.unipv.ingsfw.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel  {
	
	private JLabel name;
	private JLabel wn ;
	private JLabel dn ;
	private JLabel ln ;
	private JButton logout;
	private JButton play;
	
	public StatsPanel (JPanel menu) {
		
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
	

	
	

}
