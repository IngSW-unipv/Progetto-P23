package it.unipv.ingsfw.chess;

public enum ChessColor {
	BLACK ,WHITE;
	
	public static ChessColor oppositeColor(ChessColor c) {
		if(c == ChessColor.WHITE) {
			return ChessColor.BLACK;
		}else {
			return ChessColor.WHITE;
		}
	}
}
