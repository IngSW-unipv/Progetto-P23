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

		setSquare();
		setWhitePiece();
		setBlackPiece();
	}
	
	
	public void setSquare() {
		board = new Square [MAXDIM][MAXDIM];

		for (int i = 0 ; i < MAXDIM ; i++) {
			for (int j = 0 ; j < MAXDIM ; j++) {
				board[j][i] = new Square (j,i);
			}
		}
	}


	public void setWhitePiece() {
		this.board[0][7].setPiece(new Rook(ChessColor.WHITE, PieceType.Rook));
		this.board[1][7].setPiece(new Knight(ChessColor.WHITE, PieceType.Knight));
		this.board[2][7].setPiece(new Bishop(ChessColor.WHITE, PieceType.Bishop));
		this.board[3][7].setPiece(new Queen(ChessColor.WHITE, PieceType.Queen));
		this.board[4][7].setPiece(new King(ChessColor.WHITE, PieceType.King));
		this.board[5][7].setPiece(new Bishop(ChessColor.WHITE, PieceType.Bishop));
		this.board[6][7].setPiece(new Knight(ChessColor.WHITE, PieceType.Knight));
		this.board[7][7].setPiece(new Rook(ChessColor.WHITE, PieceType.Rook));
		
		for(int i = 0; i < MAXDIM; i++) {
			this.board[i][6].setPiece(new Pawn(ChessColor.WHITE, PieceType.Pawn));
		}
	}
	
	
	public void setBlackPiece() {
		this.board[0][0].setPiece(new Rook(ChessColor.BLACK, PieceType.Rook));
		this.board[1][0].setPiece(new Knight(ChessColor.BLACK, PieceType.Knight));
		this.board[2][0].setPiece(new Bishop(ChessColor.BLACK, PieceType.Bishop));
		this.board[3][0].setPiece(new Queen(ChessColor.BLACK, PieceType.Queen));
		this.board[4][0].setPiece(new King(ChessColor.BLACK, PieceType.King));
		this.board[5][0].setPiece(new Bishop(ChessColor.BLACK, PieceType.Bishop));
		this.board[6][0].setPiece(new Knight(ChessColor.BLACK, PieceType.Knight));
		this.board[7][0].setPiece(new Rook(ChessColor.BLACK, PieceType.Rook));
		
		for(int i = 0; i < MAXDIM; i++) {
			this.board[i][1].setPiece(new Pawn(ChessColor.BLACK, PieceType.Pawn));
		}
	}
	
	
	public void resetBoard() {
		setSquare();
		setWhitePiece();
		setBlackPiece();
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
	
	
	public void makeCastle(Move m) {
		Square King = m.getInitialPosition();
		Square Rook = m.getFinalPosition();
		Square s1, s2;
		if(Rook.getY()==King.getY()) {
			if(King.getX()<Rook.getX()) {
				s1 = getSquare(King.getX()+2,King.getY());
				s2 = getSquare(s1.getX()-1,King.getY());
			}else {
				s1 = getSquare(King.getX()-2,King.getY());
				s2 = getSquare(s1.getX()+1,King.getY());
			}
			makeMove(new Move(King,s1));
			makeMove(new Move(Rook, s2));
		}
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
	
	
	public void makeMove(Move move) {
		if(isInBoard(move.getInitialPosition()) && isInBoard(move.getFinalPosition())) {
			Square init = getSquare(move.getInitialPosition().getX(),move.getInitialPosition().getY());
			Square fin = getSquare(move.getFinalPosition().getX(),move.getFinalPosition().getY());
			Piece p1 = init.getPiece();
			init.releasePiece();
			setPiece(fin,p1);
			if(p1.isFirstMove()) {
				p1.setFirstMove(false);
			}

		}

	}
	
	
	public void emulateMove(Move move) {
		if(isInBoard(move.getInitialPosition()) && isInBoard(move.getFinalPosition())) {
			Square init = getSquare(move.getInitialPosition().getX(),move.getInitialPosition().getY());
			Square fin = getSquare(move.getFinalPosition().getX(),move.getFinalPosition().getY());
			Piece p1 = init.getPiece();
			init.releasePiece();
			setPiece(fin,p1);
		}
	}

	
	public void undoMove(Move move) {
		if(isInBoard(move.getInitialPosition()) && isInBoard(move.getFinalPosition())) {
			Piece p1 = move.getpInit();
			Piece p2 = move.getpFin();
			Square init = getSquare(move.getInitialPosition().getX(),move.getInitialPosition().getY());
			Square fin = getSquare(move.getFinalPosition().getX(),move.getFinalPosition().getY());
			init.releasePiece();
			fin.releasePiece();
			setPiece(init,p1);
			if(p2 != null) {
				setPiece(fin,p2);
			}

		}
	}


	public Square[][] getB() {
		return board;
	}
	
	
}
