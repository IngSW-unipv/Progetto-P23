package it.unipv.ingsfw.chess.board;


import java.util.ArrayList;
import java.util.Iterator;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;
import it.unipv.ingsfw.chess.pieces.Bishop;
import it.unipv.ingsfw.chess.pieces.King;
import it.unipv.ingsfw.chess.pieces.Knight;
import it.unipv.ingsfw.chess.pieces.Pawn;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.Queen;
import it.unipv.ingsfw.chess.pieces.Rook;
import it.unipv.ingsfw.chess.pieces.Rules;


public class Board {

	/* la scacchiera ha una matrice detta board di squares
	 * ovvero di contenitori di pezzi;
	 */

	private static final int MAXDIM = 8;
	private Square[][] b;
	private ArrayList <Square> possibleMoves;
	private int identificatoreSquareX,identificatoreSquareY;


	public Board () {


		possibleMoves = new ArrayList <Square> ();
		b = new Square [MAXDIM][MAXDIM];

		for (int y = 0 ; y < MAXDIM ; y++) {
			for (int x = 0 ; x < MAXDIM ; x++) {
				b[x][y] = new Square (x,y);
			}
		}




		/* SET UP della board il punto 00  e in alto a sinistra
		 * il punto 77  in basso a destra
		 * in questo caso i bianchi sono verso il basso*/


		for (int a = 0; a < MAXDIM ; a ++) {
			b[a][6].setPiece(new Pawn(ChessColor.WHITE));
			b[a][1].setPiece(new Pawn(ChessColor.BLACK));
		} 


		b[0][0].setPiece(new Rook(ChessColor.BLACK));
		b[7][0].setPiece(new Rook(ChessColor.BLACK));

		b[0][7].setPiece(new Rook(ChessColor.WHITE));
		b[7][7].setPiece(new Rook(ChessColor.WHITE));

		b[1][0].setPiece(new Bishop(ChessColor.BLACK));
		b[6][0].setPiece(new Bishop(ChessColor.BLACK));

		b[1][7].setPiece(new Bishop(ChessColor.WHITE));
		b[6][7].setPiece(new Bishop(ChessColor.WHITE));

		b[2][0].setPiece(new Knight(ChessColor.BLACK));
		b[5][0].setPiece(new Knight(ChessColor.BLACK));

		b[2][7].setPiece(new Knight(ChessColor.WHITE));
		b[5][7].setPiece(new Knight(ChessColor.WHITE));

		b[4][0].setPiece(new King(ChessColor.BLACK));
		b[3][0].setPiece(new Queen(ChessColor.BLACK));

		b[4][7].setPiece(new King(ChessColor.WHITE));
		b[3][7].setPiece(new Queen(ChessColor.WHITE));


	}

	/* questo metodo setta le mosse iniziali di ogni pezzo*/

	public void inizializeMoves () {

		// inizializzazione pedoni bianchi

		for (int i = 0; i < MAXDIM ; i ++) {
			b[i][6].getPiece().addValidMove(b [i][5]);
			b[i][6].getPiece().addValidMove(b [i][4]);
		}

		// inizializzazione pedoni neri

		for (int i = 0; i < MAXDIM ; i ++) {
			b[i][1].getPiece().addValidMove(b [i][2]);
			b[i][1].getPiece().addValidMove(b [i][3]);
		}

		// inizializzazione cavalli neri 

		b[2][0].getPiece().addValidMove(b[1][2]);
		b[2][0].getPiece().addValidMove(b[3][2]);

		b[5][0].getPiece().addValidMove(b[4][2]);
		b[5][0].getPiece().addValidMove(b[6][2]);

		// inizializzazione cavalli bianchi


		b[2][7].getPiece().addValidMove(b[1][5]);;
		b[2][7].getPiece().addValidMove(b[3][5]);;

		b[5][7].getPiece().addValidMove(b[4][5]);;
		b[5][7].getPiece().addValidMove(b[6][5]);;


	}

	/* metodo per calcolare le mosse dato il colore di chi si muove 
	 * il metodo guarda tutte le case della scacchiera quando trova un pezzo del giusto colore si ferma.
	 * ottiene dal pezzo trovato le direzioni in cui questo può muoversi.
	 * ottiene dal pezzo le regole di movimento.
	 * 
	 * per ogni regola se questa contiene una direzione valida allora calcolo le possibili caselle 
	 * su cui andare senza uscire dalla scacchiera e le metto nelle possiblemoves.
	 * 
	 * 
	 * */

	public void calcValidMoves (ChessColor c) {



		for (int y = 0 ; y < MAXDIM ; y++) {
			for (int x = 0 ; x < MAXDIM ; x++) {

				Square s = b[x][y];

				if (s.isOccupied() && (s.getPieceColor() == ChessColor.BLACK)) {
					Piece pieceFound = s.getPiece();
					pieceFound.clear();
					
					
					ArrayList <Direction> validDirections = pieceFound.getValidDirections();
					ArrayList <Rules> pieceRules = pieceFound.getRules();

					for (Rules rule : pieceRules) {

						if (validDirections.contains(rule.getDirection())) {

							int massimoSpostamento = rule.getMax();
							
							switch (rule.getDirection()) {

							case V : 

								// trova le posse possibili senza uscire dalla scacchiera

								
								identificatoreSquareY = y;

								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareY += 1;

									if (identificatoreSquareY <8 && identificatoreSquareY  >= 0) {
										possibleMoves.add(b[x][identificatoreSquareY]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();
								identificatoreSquareY = y;
								

								for ( int i = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareY -= 1;
									
									if (identificatoreSquareY <8 && identificatoreSquareY >= 0) {
										possibleMoves.add(b[x][identificatoreSquareY]);
									}
									else break;
								} 

								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();


								break;

							case H :

								identificatoreSquareX = x;


								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareX += 1;

									if (identificatoreSquareX <8 && identificatoreSquareX >= 0) {
										possibleMoves.add(b[identificatoreSquareX][y]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();
								identificatoreSquareX = x;

								for (int i  = 0 ; i < massimoSpostamento ; i ++) {


									identificatoreSquareX -= 1;
									if (identificatoreSquareX<8 && identificatoreSquareX >= 0) {
										possibleMoves.add(b[identificatoreSquareX][y]);
									}
									else break;
								}

								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();


								break;

							case D1 :

								identificatoreSquareX = x;
								identificatoreSquareY = y;
						
								
								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareX += 1;
									identificatoreSquareY += 1;

									if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
										possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								
								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareX -= 1;
									identificatoreSquareY -= 1;

									if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
										possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();

								

								break;

							case D2 :
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareX += 1;
									identificatoreSquareY -= 1;

									if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
										possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								
								for (int i  = 0 ; i < massimoSpostamento ; i ++) {

									identificatoreSquareX -= 1;
									identificatoreSquareY += 1;

									if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
										possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
									}
									else break;
								}
								
								pieceFound.addValidMoves(valids(possibleMoves,pieceFound,c));
								possibleMoves.clear();


								break;

							case L :
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								
								identificatoreSquareX += 2;
								identificatoreSquareY += 1;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareY -= 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								identificatoreSquareX -= 2;
								identificatoreSquareY += 1;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareY -= 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								identificatoreSquareX += 1;
								identificatoreSquareY += 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareX -= 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareX = x;
								identificatoreSquareY = y;
								
								identificatoreSquareX += 1;
								identificatoreSquareY -= 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								identificatoreSquareX -= 2;
								
								if (identificatoreSquareX <8 && identificatoreSquareX >= 0 && identificatoreSquareY <8 && identificatoreSquareY >= 0)  {
									possibleMoves.add(b[identificatoreSquareX][identificatoreSquareY]);
								}
								
								// qui si deve chiamare un valid che si comporta in modo diverso perchè il cavallo scavalca i pezzi;
								possibleMoves.clear();

								break;
							}
						}
					}
				}
			}
		}
	}



	public ArrayList <Square> valids (ArrayList <Square> lista,Piece pieceFound, ChessColor x) {
		ArrayList <Square> validMoves = new ArrayList <Square> ();
		Iterator <Square> iter = lista.iterator() ;
		Square s;
		

		// guardare bene cosa succede con i pedoni
		do {
			s = iter.next();
			if (!s.isOccupied()) {
				validMoves.add(s);
			}
			else if (s.isOccupied() && s.getPieceColor() != x) {
				validMoves.add(s);
				break;
			}
			else break;
		}
		while (iter.hasNext());
		
		// da riguardare

		do {
			if (iter.hasNext()) {
				s = iter.next();
				if (!s.isOccupied())continue ;
				else if (s.isOccupied() && s.isKing() && s.getPieceColor() != x) {
					pieceFound.setAttacker(true);
				}
				else break;
			}
		}
		while (iter.hasNext());



		return validMoves;
	}

	@Override 
	public String toString () {
		return "scacchiera";
	}



	public Board (String text) {


		possibleMoves = new ArrayList <Square> ();
		b = new Square [MAXDIM][MAXDIM];

		for (int j = 0 ; j < MAXDIM ; j++) {
			for (int i = 0 ; i < MAXDIM ; i++) {
				b[i][j] = new Square (i,j);
			}
		}





		b[3][3].setPiece(new Rook(ChessColor.BLACK));
		
		b[2][3].setPiece(new Pawn (ChessColor.WHITE));
		b[4][3].setPiece(new Pawn (ChessColor.WHITE));
		
		calcValidMoves(ChessColor.BLACK);
		b[3][3].getPiece().printmoves();





	}




}



