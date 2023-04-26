package it.unipv.ingsfw.chess.pieces;

public enum PieceType {
	Pawn("p"), Knight("k"), Bishop("b"), Rook("r"), King("k"), Queen("q");
	
	private String value;
	
	PieceType(String value) {
		this.value=value;
	}

	
	@Override
	public String toString() {
		return this.value;
	}
}
