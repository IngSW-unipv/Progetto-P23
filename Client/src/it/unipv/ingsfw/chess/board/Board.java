package it.unipv.ingsfw.chess.board;


import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;
import it.unipv.ingsfw.chess.pieces.Bishop;
import it.unipv.ingsfw.chess.pieces.King;
import it.unipv.ingsfw.chess.pieces.Knight;
import it.unipv.ingsfw.chess.pieces.Pawn;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.Queen;
import it.unipv.ingsfw.chess.pieces.Rook;
import it.unipv.ingsfw.chess.pieces.Move;

public class Board {


	private static final int MAXDIM = 8;
	private boolean lastmove = false; // cambiare se possibile con eccezione 
	private Square[][] squares;
	private ArrayList <Square> opponentMoves;
	private ArrayList <Square> attackers;



	public void calcMoves (ChessColor color ) { // remember neri

		for (int x = 0; x < MAXDIM ; x++) {
			for(int y = 0; y < MAXDIM;  y++) {

				//defendKing();

				if (squares[x][y].getColor() == color) {

					Piece p = squares[x][y].getPiece();
					ArrayList <Square> validMoves = p.getValidMoves(); // ricordarsi di pulire

					for (Move regola : p.getRules()) {
						Direction d = regola.getDirection();
						switch (d) {

						case N : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX(),squares[x][y].getY()+i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case S : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX(),squares[x][y].getY()-i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case W : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()-i,squares[x][y].getY());
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case E : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()+i,squares[x][y].getY());
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case NE : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()+i,squares[x][y].getY()+i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case NW : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()-i,squares[x][y].getY()+i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case SE : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()+i,squares[x][y].getY()-i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case SW : 
							for (int i = 0;i < regola.getMax(); i++  ) {
								Square s = getSquare(squares[x][y].getX()-i,squares[x][y].getY()-i);
								if(isValid(s,squares[x][y])) {
									validMoves.add(s);
								}
								else {
									lastmove = false;
									break;
								}

							}
							break;

						case L : 

							Square s = getSquare(squares[x][y].getX()+1,squares[x][y].getY()-2);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()-1,squares[x][y].getY()+2);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()+2,squares[x][y].getY()-1);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()+2,squares[x][y].getY()+1);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()+1,squares[x][y].getY()+2);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()-2,squares[x][y].getY()+1);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()-1,squares[x][y].getY()-2);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							s = getSquare(squares[x][y].getX()-2,squares[x][y].getY()-1);
							if(isValid(s,squares[x][y])) {
								validMoves.add(s);
							}
							break;

						}
					}


				}

			} // parentesi for grande
		} // parentesi for grande



	} // parentesi metodo


	private boolean isValid (Square s,Square partenza) {	
		if (lastmove) return false;
		Piece p = partenza.getPiece();
		if (s.isInBoard()) {
			if (s.isOccupied()) {
				if (s.getColor() != p.getColor()) {
					if (p.isPawn() && (partenza.getX() != s.getX() )) {
						return true;
					}
					else if (!(p.isPawn())) {
						lastmove = true;
						return true;
					}



				}

			}
		}
		return false;

	}
	
	
	public Square getSquare(int x , int y) {
		return squares [x][y];
	}








	//public void defendKing ()



	public Board () {

		opponentMoves = new  ArrayList <Square> ();
		attackers = new  ArrayList <Square> ();
		squares = new Square [MAXDIM][MAXDIM];


		squares[0][0] = new Square (0,0,new Rook(ChessColor.WHITE));
		squares[1][0] = new Square (1,0,new Bishop(ChessColor.WHITE));
		squares[2][0] = new Square (2,0,new Knight(ChessColor.WHITE));
		squares[3][0] = new Square (3,0,new Queen(ChessColor.WHITE));
		squares[4][0] = new Square (4,0,new King(ChessColor.WHITE));
		squares[5][0] = new Square (5,0,new Knight(ChessColor.WHITE));
		squares[6][0] = new Square (6,0,new Bishop(ChessColor.WHITE));
		squares[7][0] = new Square (7,0,new Rook(ChessColor.WHITE));


		for (int i = 0 ; i < MAXDIM ; i++) {
			squares[i][1] = new Square (i,2,new Pawn(ChessColor.WHITE));

		}

		for (int j = 2; j < 6 ; j ++) {
			for ( int i = 0 ; i< MAXDIM; i++) {
				squares [i][j] = new Square (i,j);
			}


			for (int i = 0 ; i < MAXDIM ; i++) {
				squares[i][6] = new Square (i,6,new Pawn(ChessColor.BLACK));

			}

			squares[0][7] = new Square (0,7,new Rook(ChessColor.BLACK));
			squares[1][7] = new Square (1,7,new Bishop(ChessColor.BLACK));
			squares[2][7] = new Square (2,7,new Knight(ChessColor.BLACK));
			squares[3][7] = new Square (3,7,new Queen(ChessColor.BLACK));
			squares[4][7] = new Square (4,7,new King(ChessColor.BLACK));
			squares[5][7] = new Square (5,7,new Knight(ChessColor.BLACK));
			squares[6][7] = new Square (6,7,new Bishop(ChessColor.BLACK));
			squares[7][7] = new Square (7,7,new Rook(ChessColor.BLACK));


		}


	}

}



