package it.unipv.ingsfw.chess.board;

import java.util.HashMap;
import java.util.Map;

public class Board {

	private final static int MAXSQUARE = 8;
	private Map<Integer,Square> squares;
	
	
	public Board () {
		this.squares = new HashMap<Integer,Square>();
		
		init();
	}
	
	private void init() {
		Integer count = new Integer(0);
		for(int i=0;i<MAXSQUARE;i++) {
			for(int j=0;j<MAXSQUARE;j++) {
				this.squares.put(count, new Square(i,j));
				count++;
			}
		}
		System.out.println(count);
	}
	
	public Map<Integer, Square> getSquares() {
		return squares;
	}

	public void setSquares(Map<Integer, Square> squares) {
		this.squares = squares;
	}
	
	public static void main(String[] args) {
		Board b1 = new Board();
		for(int i=0;i<64;i++) {
			System.out.println(b1.getSquares().get(i).getX() + " " + b1.getSquares().get(i).getY());
		}
	}


}
