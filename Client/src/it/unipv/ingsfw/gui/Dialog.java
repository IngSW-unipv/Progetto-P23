package it.unipv.ingsfw.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import it.unipv.ingsfw.chess.ChessColor;

public class Dialog extends JDialog {

	public Dialog (ChessColor c , JButton back , JPanel gameBoard , JPanel gameToolBar ,JPanel mainMenu) {
		super();
        setSize(300, 300);
        setLocationRelativeTo(null);

        // Crea una label per visualizzare il testo nel JDialog
        JLabel label = new JLabel(c.oppositeColor(c).toString() + " wins.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(label);
        
        
        //crea il bottone 
        
        getContentPane().add(back, BorderLayout.SOUTH);

        // Mostra il JDialog
        setVisible(true);
        
        // modifica del bottone di chiusura 
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
				mainMenu.setVisible(true);
				gameBoard.setVisible(false);
				gameToolBar.setVisible(false);
				dispose();
            }
        });
        
      

	}
}
