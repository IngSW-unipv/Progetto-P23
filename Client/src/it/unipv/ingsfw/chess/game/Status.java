package it.unipv.ingsfw.chess.game;

import it.unipv.ingsfw.chess.ChessColor;

public enum Status {
	CHECK("check"), WHITE_WIN("Bianco Vince"), BLACK_WIN("Nero Vince"), STALEMATE("Stallo"), CHECK_MATE("Scacco Matto"), PLAY("Gioca");
	//FORFEIT
	
	private String value;
	
	
	Status(String value){
		this.value=value;
	}
	
	
	@Override
	public String toString() {
		return this.value;
	}
	
	
	public static Status winner(ChessColor c) {
		if(c==ChessColor.WHITE) {
			return Status.BLACK_WIN;
		}else {
			return Status.WHITE_WIN;
		}
	}
}
