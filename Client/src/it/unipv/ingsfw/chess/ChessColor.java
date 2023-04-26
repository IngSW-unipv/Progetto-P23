package it.unipv.ingsfw.chess;

public enum ChessColor {
	BLACK("B") ,WHITE("W");
	
	private String value;
	
	
	ChessColor(String value) {
		this.value=value;
	}

	
	@Override
	public String toString() {
		return this.value;
	}
	

	public static ChessColor oppositeColor(ChessColor c) {
		if(c == ChessColor.WHITE) {
			return ChessColor.BLACK;
		}else {
			return ChessColor.WHITE;
		}
	}
}
