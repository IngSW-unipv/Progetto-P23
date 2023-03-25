package it.unipv.ingsfw.gui;

import java.awt.FlowLayout;

import javax.swing.*;

public class ToolBar extends JPanel {

	private JButton button;
	private JLabel label;
	
	
	public ToolBar () {
		super ();
		button = new JButton ("ciao");
		label = new JLabel ("aa");
		
		setLayout(new FlowLayout (FlowLayout.LEFT));
		
		add(button);
		add(label);
		
	}
}
