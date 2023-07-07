package it.unipv.ingsfw.gui.panels;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WaitingPanel extends JPanel{
	private JPanel mainMenu;
	
	public WaitingPanel () {
		
		
		JLabel attendi = new JLabel ("attendi il secondo giocatore.");
		Cursor customCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
		setCursor(customCursor);
		add(attendi);
		
	}
	
	public void setmenu (JPanel menu) {
		mainMenu = menu ;
	}

}
