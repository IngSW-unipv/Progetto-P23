package it.unipv.ingsfw.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.Board;
import it.unipv.ingsfw.chess.game.Square;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.gui.GameBoard;
import it.unipv.ingsfw.gui.GamePanel;
import it.unipv.ingsfw.gui.GameToolBar;
import it.unipv.ingsfw.gui.buttons.GameButton;

public class Controller {

	private Board model;
	private GamePanel view;
	private GameBoard viewBoard;
	private GameToolBar toolBar;
	private GameButton [][] tasti;




	public Controller(Board model, GamePanel view)  {
		super();
		this.model = model;
		this.view = view;
		
		viewBoard = view.getGameBoard();
		toolBar = view.getGameToolBar();
		tasti = viewBoard.getTasti();
		inizializeView (model);
		



		toolBar.getButton1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ciao");

			}
		});

		tasti = viewBoard.getTasti();

		for (int y = 0 ; y <8;  y++) {
			for (int x = 0 ; x < 8 ;x++) {		
				tasti[x][y].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("suca");

					}
				});
			}
		}


	}






	public void inizializeView (Board model) {

		Square [][] casa = model.getB();
		Piece p;

		for (int y = 0 ; y <8;  y++) {
			for (int x = 0 ; x < 8 ;x++) {		

				p  = casa[x][y].getPiece();

				if (p !=  null)		{

					if (p.getColor() == ChessColor.WHITE) {

						switch (p.getType()) {

						case Pawn :
							tasti[x][y].setIcon(viewBoard.getPawnW());
							break;

						case Rook :
							tasti[x][y].setIcon(viewBoard.getRookW());
							break;
							
						case King :
							tasti[x][y].setIcon(viewBoard.getKingW());
							break;

							
						case Queen :
							tasti[x][y].setIcon(viewBoard.getQueenW());
							break;

							
						case Knight :
							tasti[x][y].setIcon(viewBoard.getKnightW());
							break;

						case Bishop :
							tasti[x][y].setIcon(viewBoard.getBishopW());
							break;


						}

					}
					else {
						
						switch (p.getType()) {

						case Pawn :
							tasti[x][y].setIcon(viewBoard.getPawnB());
							break;

						case Rook :
							tasti[x][y].setIcon(viewBoard.getRookB());
							break;
							
						case King :
							tasti[x][y].setIcon(viewBoard.getKingB());
							break;

							
						case Queen :
							tasti[x][y].setIcon(viewBoard.getQueenB());
							break;

							
						case Knight :
							tasti[x][y].setIcon(viewBoard.getKnightB());
							break;

						case Bishop :
							tasti[x][y].setIcon(viewBoard.getBishopB());
							break;


						}


					}

				}

			}

		}


	}
}







