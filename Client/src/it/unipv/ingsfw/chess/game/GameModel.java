package it.unipv.ingsfw.chess.game;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.PieceType;
import it.unipv.ingsfw.chess.pieces.Queen;

public class GameModel {

	private Board board;
	private ChessColor currentPlayer;
	private Status gameStatus;
	private List<Move> currentPlayerMoves;
	private List<Move> movesDone;
	


	public GameModel () {
		this.board = new Board();
		this.currentPlayer = ChessColor.WHITE;
		this.gameStatus = Status.PLAY;
		this.currentPlayerMoves = new ArrayList<Move>();
		this.movesDone = new ArrayList<Move>();
		initTurn();
	}
	
	
	public void switchCurrentPlayer() {
		this.currentPlayer = ChessColor.oppositeColor(currentPlayer);
		initTurn();
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

	
	public List<Move> getmovesDone() {
		return movesDone;
	}
	
	
	public Status getGameStatus() {
		return gameStatus;
	}
	
	public void setGameStatus(Status s) {
	gameStatus = s;
		
	}
	
	
	public void resetGame() {
		this.currentPlayer = ChessColor.WHITE;
		this.gameStatus = Status.PLAY;
		this.currentPlayerMoves = new ArrayList<Move>();
		this.movesDone = new ArrayList<Move>();
		this.board.resetBoard();
		initTurn();
	}
	
	
	public boolean isMakeCheck(Move m) {
		Square king = this.board.getKingSquare(currentPlayer);
		if(king.equals(m.getFinalPosition())) {
			return true;
		}
		return false;
	}
	
	
	public boolean isCheckMate() {
		if(isCheck() && this.currentPlayerMoves.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public void initTurn() {
		this.currentPlayerMoves = calcolatorMoves(currentPlayer);
		castle();
		enPassant();
		checkCurrentMoves();
		changeStatus();
	}
	
	
	public boolean isCheck() {
		for(Move m: calcolatorMoves(ChessColor.oppositeColor(currentPlayer))) {
			if(isMakeCheck(m)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void changeStatus() {
		if(isCheck()) {
			if(isCheckMate()) {
				this.gameStatus=Status.CHECK_MATE;
			}else {
				this.gameStatus=Status.CHECK;
			}
		}else if(this.currentPlayerMoves.isEmpty()){
			this.gameStatus=Status.STALEMATE;
		}else {
			this.gameStatus=Status.PLAY;
		}
	}
	
	
	public void resignation() {
		this.gameStatus = Status.winner(ChessColor.oppositeColor(currentPlayer));
		
	}
	
	
	public boolean isPromotion(Square square) {
		Piece p = square.getPiece();
		if(p == null) {
			return false;
		}
		if(p.getType()==PieceType.Pawn) {
			if(p.getColor()==ChessColor.WHITE && square.getY() == 0) {
				return true;
			}else if(p.getColor()==ChessColor.BLACK && square.getY() == 7){
				return true;
			}
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
							}else {
								break;
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
							}else {
								break;
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
			board.emulateMove(m1);
			for(Move m2: calcolatorMoves(ChessColor.oppositeColor(currentPlayer))) {
				if(isMakeCheck(m2)) {
					this.currentPlayerMoves.remove(m1);
					break;
				}
			}
			board.undoMove(m1);
		}
	}
	
	
	public void makeMove(Move m) {
		for(Move possible: currentPlayerMoves) {
			if(m.equals(possible)) {
				if(isCastling(possible)) {
					board.makeCastle(possible);
				}else {
					board.makeMove(possible);
					if(isEnPassant(possible)) {
						board.getSquare(possible.getFinalPosition().getX(),possible.getInitialPosition().getY()).releasePiece();
					}
					Square fin = board.getSquare(possible.getFinalPosition().getX(), possible.getFinalPosition().getY());
					if(isPromotion(fin)) {
						board.setPiece(fin, new Queen(this.currentPlayer,PieceType.Queen));
					}
				}
				movesDone.add(possible);
				switchCurrentPlayer();
				break;
			}
		}
	}
	

	public ArrayList<Move> movesPerPiece(Square init){
		ArrayList<Move> movesPerPiece = new ArrayList<Move>();
		Piece currentPiece = init.getPiece();
		if(currentPiece != null) {
			Piece p;
			for(Move m: currentPlayerMoves){
				p = m.getpInit();
				if(currentPiece == p){
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
	
	public boolean isValidMove (Move m) {
		for(Move possible: currentPlayerMoves) {
			if(m.equals(possible)) {
				return true;
			}
			
		}
		return false;
	}
	
	public List <Square> getPositions  (Square square){		
		List <Square> possibleSquare = new ArrayList <Square> ();
		for (Move move : currentPlayerMoves) {
			if (square.equals(move.getInitialPosition())) {
				Square boardSquare = board.getSquare(move.getFinalPosition().getX(),move.getFinalPosition().getY());
				possibleSquare.add(boardSquare);
			}
		}
		return possibleSquare;
		
	}
	

		
	public void castle() {
		Square king = this.board.getKingSquare(currentPlayer);
		Square s;
		Square rook;
		if(!isCheck()) {
			if((king.getPiece().isFirstMove())) {
				for(int i=0;i<8;i++) {
					s = board.getSquare(i,king.getY());
					if(s.isOccupied() && s.getPieceType()==PieceType.Rook && s.getPieceColor()==currentPlayer && s.getPiece().isFirstMove()) {
						rook = s;
						if(king.getX()<rook.getX()) {
							if(isClearPath(king,rook)) {
								this.currentPlayerMoves.add(new Move(king,rook));
							}
						}else {
							if(isClearPath(rook,king)) {
								this.currentPlayerMoves.add(new Move(king,rook));
							}
						}
					}
				}	
			}
		}
	}

	

	public boolean isClearPath(Square s1, Square s2) {
		Square path;
		if(s1.getY() != s2.getY()) {
			return false;
		}
		for(int i= s1.getX()+1;i<s2.getX();i++) {
			path=board.getSquare(i, s1.getY());
			if(path.isOccupied()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isCastling(Move m) {
		Square init = board.getSquare(m.getInitialPosition().getX(),m.getInitialPosition().getY());
		Square fin = board.getSquare(m.getFinalPosition().getX(),m.getFinalPosition().getY());
		if(init.isKing() && fin.isOccupied()) {
			if(fin.getPieceType()==PieceType.Rook) {
				return true;
			}else {
				return false;
			}

		}
		return false;
	}
	
	
	public void enPassant() {
		if(!movesDone.isEmpty()) {
			Move m = movesDone.get(movesDone.size()-1);
			if(m.getpInit().getType() == PieceType.Pawn && Math.abs(m.getFinalPosition().getY() - m.getInitialPosition().getY()) == 2) {
				Square s = m.getFinalPosition();
				Square s1,s2;
				
				if(board.isInBoard(new Square(s.getX()+1,s.getY()))) {
					s1 = board.getSquare(s.getX()+1,s.getY());
					if(s1.isOccupied()) {
						if(s1.getPieceColor() == currentPlayer && s1.getPieceType() == PieceType.Pawn) {
							if(currentPlayer == ChessColor.WHITE) {
								currentPlayerMoves.add(new Move(s1,board.getSquare(s.getX(),s.getY()-1)));
							}else {
								currentPlayerMoves.add(new Move(s1,board.getSquare(s.getX(),s.getY()+1)));
							}
						}
					}
				}
				
				if(board.isInBoard(new Square(s.getX()-1,s.getY()))) {
					s2 = board.getSquare(s.getX()-1,s.getY());
					if(s2.isOccupied()) {
						if(s2.getPieceColor() == currentPlayer && s2.getPieceType() == PieceType.Pawn) {
							if(currentPlayer == ChessColor.WHITE) {
								currentPlayerMoves.add(new Move(s2,board.getSquare(s.getX(),s.getY()-1)));
							}else {
								currentPlayerMoves.add(new Move(s2,board.getSquare(s.getX(),s.getY()+1)));
							}
						}
					}

				}
			}
		}		
	}
	
	public boolean isEnPassant(Move m){
		Piece init = m.getpInit();
		if(init.getType() == PieceType.Pawn) {
			if(m.getpFin() == null && m.getInitialPosition().getX() != m.getFinalPosition().getX()) {
				return true;
			}
		}
		return false;
	}
	
	public ChessColor getPieceColor (int x , int y) {
		return board.getSquare(x,y).getPieceColor();
	}

	

}



