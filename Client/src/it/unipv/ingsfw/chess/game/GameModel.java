package it.unipv.ingsfw.chess.game;

import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.PieceType;

public class GameModel {

	private Board board;
	private ChessColor currentPlayer;
	private List<Move> currentPlayerMoves;
	private List<Move> opponentPlayerMoves;


	public GameModel () {
		this.board = new Board();
		this.currentPlayer = ChessColor.WHITE;
		this.currentPlayerMoves = new ArrayList<Move>();
		this.opponentPlayerMoves = new ArrayList<Move>();
	}
	


	/*
	public void switchCurrentPlayer() {
		if(this.currentPlayer == ChessColor.WHITE) {
			this.currentPlayer = ChessColor.BLACK;
			this.opponentPlayerMoves = this.currentPlayerMoves;
			this.currentPlayerMoves = new ArrayList<Move>();
		}else {
			this.currentPlayer = ChessColor.WHITE;
			this.opponentPlayerMoves = this.currentPlayerMoves;
			this.currentPlayerMoves = new ArrayList<Move>();
		}
	}
	*/
	
	public void switchCurrentPlayer() {
		//calcolateMove(currentPlayer);
		this.currentPlayer = ChessColor.oppositeColor(currentPlayer);
		this.opponentPlayerMoves = this.currentPlayerMoves;
		this.currentPlayerMoves = new ArrayList<Move>();
		
	}


	public Board getBoard() {
		return board;
	}


	public ChessColor getCurrentPlayer() {
		return currentPlayer;
	}
	
	
	public List<Move> getCurrentPlayerMoves() {
		return currentPlayerMoves;
	}

	
	public List<Move> getOpponentPlayerMoves() {
		return opponentPlayerMoves;
	}
	
	
	public boolean isCheck(Move m) {
		Square king = this.board.getKingSquare(currentPlayer);
		/*
		for(Move m: this.opponentPlayerMoves) {
			if(king.equals(m.getFinalPosition())) {
				return true;
			}
		}
		*/
		if(king.equals(m.getFinalPosition())) {
			return true;
		}
		return false;
	}
	
	public boolean isCheckMate(Move m) {
		if(isCheck(m) && this.currentPlayerMoves == null) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Move> calcolatorMoves(ChessColor c){
		Square init;
		ArrayList<Move> moves = new ArrayList<Move>();
		for(int i = 0; i < 8; i++) {
			for(int j=0; j < 8; j++) {
				if(board.getSquare(j, i).isOccupied() && c == board.getSquare(j, i).getPieceColor()) {
					init = board.getSquare(j, i);
					for(Rules r : init.getPiece().getDirections()) {
						moves.addAll(createPath(init,r));
					}
				}
			}
		}
		return moves;
	}
	
	public void initTurn() {
		this.currentPlayerMoves = calcolatorMoves(currentPlayer);
		this.opponentPlayerMoves = calcolatorMoves(ChessColor.oppositeColor(currentPlayer));
		checkCurrentMoves();
	}
	
	

	public ArrayList<Move> createPath(Square init, Rules r) {
		ArrayList<Move> path = new ArrayList<Move>();
		Square fin;
		Piece p = init.getPiece();
		ChessColor c = p.getColor();
		int j=0;
		switch(r.getDirection()) {
			case N:				
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX(), init.getY()+j))) {
						fin = this.board.getSquare(init.getX(), init.getY()+j);
						if(p.getType()==PieceType.Pawn) {
							if(!fin.isOccupied()) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case S:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX(), init.getY()-j))) {
						fin = this.board.getSquare(init.getX(), init.getY()-j);
						if(p.getType()==PieceType.Pawn) {
							if(!fin.isOccupied()) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case E:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()+j, init.getY()))) {
						fin = this.board.getSquare(init.getX()+j, init.getY());
						if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case W:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()-j, init.getY()))) {
						fin = this.board.getSquare(init.getX()-j, init.getY());
						if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case NE:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()+j, init.getY()+j))) {
						fin = this.board.getSquare(init.getX()+j, init.getY()+j);
						if(p.getType()==PieceType.Pawn) {
							if(fin.isOccupied() && fin.getPieceColor() != c) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case NW:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()-j, init.getY()+j))) {
						fin = this.board.getSquare(init.getX()-j, init.getY()+j);
						if(p.getType()==PieceType.Pawn) {
							if(fin.isOccupied() && fin.getPieceColor() != c) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case SE:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()+j, init.getY()-j))) {
						fin = this.board.getSquare(init.getX()+j, init.getY()-j);
						if(p.getType()==PieceType.Pawn) {
							if(fin.isOccupied() && fin.getPieceColor() != c) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case SW:
				for(int i=0;i<r.getMaxdim();i++) {
					j=i+1;
					if(this.board.isInBoard(new Square(init.getX()-j, init.getY()-j))) {
						fin = this.board.getSquare(init.getX()-j, init.getY()-j);
						if(p.getType()==PieceType.Pawn) {
							if(fin.isOccupied() && fin.getPieceColor() != c) {
								path.add(new Move(init, fin));
							}
						}else if(!fin.isOccupied()) {
							path.add(new Move(init, fin));
						}else if(fin.isOccupied() && fin.getPieceColor() != c) {
							path.add(new Move(init, fin));
							break;
						}else {
							break;
						}
					}else {
						break;
					}
				}
				break;
			case L: 
				if(this.board.isInBoard(new Square(init.getX()+1,init.getY()+2))) {
					fin = this.board.getSquare(init.getX()+1, init.getY()+2);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()+2,init.getY()+1))) {
					fin = this.board.getSquare(init.getX()+2, init.getY()+1);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()+2,init.getY()-1))) {
					fin = this.board.getSquare(init.getX()+2, init.getY()-1);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()+1,init.getY()-2))) {
					fin = this.board.getSquare(init.getX()+1, init.getY()-2);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()-2,init.getY()-1))) {
					fin = this.board.getSquare(init.getX()-2, init.getY()-1);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()-1,init.getY()-2))) {
					fin = this.board.getSquare(init.getX()-1, init.getY()-2);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()-2,init.getY()+1))) {
					fin = this.board.getSquare(init.getX()-2, init.getY()+1);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				if(this.board.isInBoard(new Square(init.getX()-1,init.getY()+2))) {
					fin = this.board.getSquare(init.getX()-1, init.getY()+2);
					if(!fin.isOccupied() || (fin.isOccupied() && fin.getPieceColor() != c)) {
						path.add(new Move(init,fin));
					}
				}
				break;
			default:
				break;
		}
		return path;
	}
	
	public void makeMove(Move m) {
		for(Move possible: currentPlayerMoves) {
			if(m.equals(possible)) {
				board.makeMove(m);
			}
		}
	}
	
	public void undoMove(Move m) {
		board.fakeMove(new Move(m.getFinalPosition(),m.getInitialPosition()));
	}
	
	public void checkCurrentMoves() {
		for(int i = 0;i < 8;i++) {
			for(int j = 0;j < 8;j++) {
				if(board.getSquare(j, i).getPieceColor()==currentPlayer) {
					removeMoves(board.getSquare(j, i));
				}
			}
		}
	}
	
	public void removeMoves(Square s) {
		for(Move m1: movesPerPiece(s)) {
			board.fakeMove(m1);
			for(Move m2: calcolatorMoves(ChessColor.oppositeColor(currentPlayer))) {
				if(isCheck(m2)) {
					this.currentPlayerMoves.remove(m1);
				}
			}
			undoMove(m1);
		}
	}
	
	public ArrayList<Move> movesPerPiece(Square init){
		ArrayList<Move> movesPerPiece = new ArrayList<Move>();
		if(init.getPieceColor() == null) {
			return null;
		}else if(init.getPieceColor() == this.currentPlayer) {
			for(Move m: this.currentPlayerMoves) {
				if(init.equals(m.getInitialPosition())) {
					movesPerPiece.add(m);
				}
			}
		}else {
			for(Move m: this.opponentPlayerMoves) {
				if(init.equals(m.getInitialPosition())) {
					movesPerPiece.add(m);
				}
			}
		}
	
		return movesPerPiece;
	}

	
	public boolean isOccupied (Square a) {
		return board.getSquare(a.getX(), a.getY()).isOccupied();
	}
	
	public Square[][] getB () {
		return board.getB();
	}
	
	public boolean xxx (Move m) {
		for(Move possible: currentPlayerMoves) {
			if(m.equals(possible)) {
				return true;
			}
			
		}
		return false;
	}
		

	

}



