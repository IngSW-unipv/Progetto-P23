package it.unipv.ingsfw.chess.board;

import it.unipv.ingsfw.chess.color.ChessColor;
import it.unipv.ingsfw.chess.piece.pieces.Bishop;
import it.unipv.ingsfw.chess.piece.pieces.King;
import it.unipv.ingsfw.chess.piece.pieces.Knight;
import it.unipv.ingsfw.chess.piece.pieces.Pawn;
import it.unipv.ingsfw.chess.piece.pieces.Queen;
import it.unipv.ingsfw.chess.piece.pieces.Rook;

public class Board {

	private final static int MAXSQUARE = 8;
	private Square[][] squares;
	
	
	public Board () {
		squares = new Square[MAXSQUARE][MAXSQUARE];
		
		squares[0][0] = new Square (0,0,new Rook (ChessColor.BLACK));
		squares[1][0] = new Square (1,0,new Knight (ChessColor.BLACK));
		squares[2][0] = new Square (2,0,new Bishop (ChessColor.BLACK));
		squares[3][0] = new Square (3,0,new Queen (ChessColor.BLACK));
		squares[4][0] = new Square (4,0,new King (ChessColor.BLACK));
		squares[5][0] = new Square (5,0,new Bishop (ChessColor.BLACK));
		squares[6][0] = new Square (6,0,new Knight (ChessColor.BLACK));
		squares[7][0] = new Square (7,0,new Rook (ChessColor.BLACK));
		
		squares[0][7] = new Square (0,7,new Rook (ChessColor.WHITE));
		squares[1][7] = new Square (1,7,new Knight (ChessColor.WHITE));
		squares[2][7] = new Square (2,7,new Bishop (ChessColor.WHITE));
		squares[3][7] = new Square (3,7,new Queen (ChessColor.WHITE));
		squares[4][7] = new Square (4,7,new King (ChessColor.WHITE));
		squares[5][7] = new Square (5,7,new Bishop (ChessColor.WHITE));
		squares[6][7] = new Square (6,7,new Knight (ChessColor.WHITE));
		squares[7][7] = new Square (7,7,new Rook (ChessColor.WHITE));
		
		for (int i = 0; i < MAXSQUARE ;i++) {
			squares[i][1] = new Square (i,1,new Pawn (ChessColor.BLACK));
			squares[i][6] = new Square (i,6,new Pawn(ChessColor.WHITE));
		}
		
		for (int i = 2; i < 6 ; i++) {
			for (int j = 0; j < MAXSQUARE ; j++) {
				squares[j][i] = new Square (j,i);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		Board sandro = new Board ();		
		for ( int i = 0; i < MAXSQUARE ; i++ ) {
			for (int j = 0; j < MAXSQUARE ; j++) {
				System.out.println(sandro.squares[j][i].getPiece());
			}
		}
	}
}
