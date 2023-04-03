package it.unipv.ingsfw.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class ToolBar extends JPanel {

	private JButton button1;
	private JButton button2;
	private JLabel label;
	
	
	public ToolBar () {
		super ();
		
		button1 = new JButton ("nuova partita");
		button2 = new JButton ("resa");
		label = new JLabel ("stato partita");
		setLayout(new FlowLayout (FlowLayout.LEFT));
		
		setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
		
		add(button1);
		add(button2);
		add(label);
	
		
		
		
		
	}
}
