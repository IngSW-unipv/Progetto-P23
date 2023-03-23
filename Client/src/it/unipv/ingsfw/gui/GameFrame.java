package it.unipv.ingsfw.gui;

import java.awt.*;
import javax.swing.*;

public class GameFrame  extends JFrame {

	public GameFrame (String xxx) {
		super(xxx);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
