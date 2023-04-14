package it.unipv.ingsfw.chess.game;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Bishop;
import it.unipv.ingsfw.chess.pieces.King;
import it.unipv.ingsfw.chess.pieces.Knight;
import it.unipv.ingsfw.chess.pieces.Pawn;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.PieceType;
import it.unipv.ingsfw.chess.pieces.Queen;
import it.unipv.ingsfw.chess.pieces.Rook;

public class Board {
	
	private static final int MAXDIM = 8;

	private Square[][] board;

	public Board() {

		board = new Square [MAXDIM][MAXDIM];

		for (int i = 0 ; i < MAXDIM ; i++) {
			for (int j = 0 ; j < MAXDIM ; j++) {
				board[j][i] = new Square (j,i);
			}
		}
		
		setWhitePiece();
		setBlackPiece();
	}
	
	public void setWhitePiece() {
		this.board[0][0].setPiece(new Rook(ChessColor.WHITE, PieceType.Rook));
		this.board[1][0].setPiece(new Knight(ChessColor.WHITE, PieceType.Knight));
		this.board[2][0].setPiece(new Bishop(ChessColor.WHITE, PieceType.Bishop));
		this.board[3][0].setPiece(new Queen(ChessColor.WHITE, PieceType.Queen));
		this.board[4][0].setPiece(new King(ChessColor.WHITE, PieceType.King));
		this.board[5][0].setPiece(new Bishop(ChessColor.WHITE, PieceType.Bishop));
		this.board[6][0].setPiece(new Knight(ChessColor.WHITE, PieceType.Knight));
		this.board[7][0].setPiece(new Rook(ChessColor.WHITE, PieceType.Rook));
		
		for(int i = 0; i < MAXDIM; i++) {
			this.board[i][1].setPiece(new Pawn(ChessColor.WHITE, PieceType.Pawn));
		}
	}
	
	public void setBlackPiece() {
		this.board[0][7].setPiece(new Rook(ChessColor.BLACK, PieceType.Rook));
		this.board[1][7].setPiece(new Knight(ChessColor.BLACK, PieceType.Knight));
		this.board[2][7].setPiece(new Bishop(ChessColor.BLACK, PieceType.Bishop));
		this.board[3][7].setPiece(new Queen(ChessColor.BLACK, PieceType.Queen));
		this.board[4][7].setPiece(new King(ChessColor.BLACK, PieceType.King));
		this.board[5][7].setPiece(new Bishop(ChessColor.BLACK, PieceType.Bishop));
		this.board[6][7].setPiece(new Knight(ChessColor.BLACK, PieceType.Knight));
		this.board[7][7].setPiece(new Rook(ChessColor.BLACK, PieceType.Rook));
		
		for(int i = 0; i < MAXDIM; i++) {
			this.board[i][6].setPiece(new Pawn(ChessColor.BLACK, PieceType.Pawn));
		}
	}
	
	public Square getSquare(int x, int y) {
		return board[x][y];
	}
	
	
	public Square getKingSquare(ChessColor color) {
		Square king;
		for(int i = 0;i < MAXDIM;i++) {
			for(int j = 0;j < MAXDIM;j++) {
				king = getSquare(i,j);
				if(king.isOccupied() && king.isKing() && king.getPiece().getColor() == color) {
					return king;
				}
			}
		}
		return null;
	}
	
	
	public boolean isInBoard(Square s) {
		if(s.getX()>=0 && s.getX()<MAXDIM && s.getY()>=0 && s.getY()<MAXDIM) {
			return true;
		}
		return false;
	}
	
	public void setPiece(Square s, Piece p) {
		s.setPiece(p);
	}
	
	public void capturePiece(Square s) {
		if(s.isOccupied()) {
			Piece p = s.getPiece();
			s.releasePiece();
		}
	}
	
	public void makeMove(Move move) {
		Square init = move.getInitialPosition();
		Square fin = move.getFinalPosition();
		Piece p = init.getPiece();
		init.releasePiece();
		capturePiece(fin);
		setPiece(fin,p);
	}
	
	  public static void  main(String[] args) {
			Board b1 = new Board();
			for(int i=0; i < 8; i++) {
				for(int j=0; j < 8; j++) {
					System.out.println(b1.getSquare(j, i).getPieceString()+" "+b1.getSquare(j, i).toString());
				}
			}
	  }
	  
	  public Square[][] getB () {
		  return board;
	  }
	

}