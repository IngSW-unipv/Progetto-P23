package it.unipv.ingsfw.chess.game;

import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.chess.pieces.PieceType;

public class GameModel {

	private Board board;
	private ChessColor currentPlayer;
	private List<Move> possibleMoves;


	public GameModel () {
		this.board = new Board();
		this.currentPlayer = ChessColor.WHITE;
		this.possibleMoves = new ArrayList<Move>();

	}
	
	public void switchCurrentPlayer() {
		if(this.currentPlayer == ChessColor.WHITE) {
			this.currentPlayer = ChessColor.BLACK;
			this.possibleMoves = new ArrayList<Move>();
		}else {
			this.currentPlayer = ChessColor.WHITE;
			this.possibleMoves = new ArrayList<Move>();
		}
	}


	public Board getBoard() {
		return board;
	}
	
	public Square[][] getB () {
		return board.getB();
	}


	public ChessColor getCurrentPlayer() {
		return currentPlayer;
	}
	
	public List<Move> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(List<Move> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}


	public void calculateMove(ChessColor c) {
		Square s;
		for(int i = 0; i < 8; i++) {
			for(int j=0; j < 8; j++) {
				if(board.getSquare(j, i).isOccupied() && c == board.getSquare(j, i).getPieceColor()) {
					s = board.getSquare(j, i);
					this.possibleMoves.addAll(calcolator(s,c));
				}
			}
		}
		
	}
	
	
	public ArrayList<Move> calcolator(Square init, ChessColor c) {
		Piece p = init.getPiece();
		ArrayList<Move> pieceMoves = new ArrayList<Move>();
		for(Rules r : p.directions()) {
			pieceMoves.addAll(createPath(init,r,c));
		}
		return pieceMoves;
	}


	public ArrayList<Move> createPath(Square init, Rules r, ChessColor c) {
		ArrayList<Move> path = new ArrayList<Move>();
		Square fin;
		Piece p = init.getPiece();
		int j;
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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
						}else if((fin.isOccupied() && fin.getPieceColor() != c) || !fin.isOccupied()) {
							path.add(new Move(init, fin));
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

	
	public static void main(String[] args) {
		GameModel g1 = new GameModel();
		Square s;
		/*for(int i = 0; i < 8; i++) {
			for(int j=0; j < 8; j++) {
					s = g1.getBoard().getSquare(j, i);
					System.out.println(s.getPieceString()+": "+s.toString()+"\n");
			}
		}*/
		g1.calculateMove(ChessColor.BLACK);
		for(Move m: g1.getPossibleMoves()) {
			System.out.println(m.getInitialPosition().getPieceString()+":\n"+m.getInitialPosition().toString()+"\n"+m.getFinalPosition().toString()+"\n");
		}
		g1.switchCurrentPlayer();
		
		
	}
	

}



