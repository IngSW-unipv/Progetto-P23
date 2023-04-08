package it.unipv.ingsfw.chess.pieces;

import java.util.ArrayList;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.Direction;
import it.unipv.ingsfw.chess.board.Square;

public abstract class Piece {
	
	/* ogni pezzo ha un indicazione sulle direzioni e numero di passi dette RULES
	 * ogni przzo ha un elenco di mosse possibili da calcolare VALIDMOVES
	 * ogni pezzo ha un elenco di direzioni che può seguire VALIDDIRECTIONS
	 * ogni pezzo ha un colore a cui appartiene;
	 *  */

	 protected ArrayList <Rules> rules;
	 protected ArrayList <Square> validMoves;
	 protected ArrayList <Direction> validDirections;
	 protected ChessColor color;
	 protected boolean attacker = false;
	
	 
	 
	 
	 public Piece (ChessColor color) {
		 this.color = color; 
		 rules = new  ArrayList <Rules> ();
		 validMoves = new ArrayList <Square>();
		 validDirections = new ArrayList <Direction> ();
	 }
	 
	 
	 public ChessColor getColor () {
		 return color;
	 }
	 
	 public ArrayList <Square> getValidMoves (){	 
		 return validMoves;
	 }
	 
	 public ArrayList <Direction>  getValidDirections () {
		 return validDirections;
	 }
	 
	 public ArrayList <Rules>  getRules () {
		 return rules;
	 }
	 
	 public void addValidMoves (ArrayList <Square> s) {
		 validMoves.addAll(s);
		  
	 }
	 
	 public void addValidMove (Square s) {
		 validMoves.add(s);
		 
	 }
	 
	 public void clearValidMoves () {
		 validMoves.clear();
	 }
	 
	 public void setAttacker (boolean x) {
		 attacker = x;
		 
	 }
	 
	 public abstract boolean isKing();
	 public abstract boolean isPawn();
	 public abstract boolean isKnight();
	 
	 public void printmoves () {
		 for (Square a : validMoves) {
			System.out.println(a);
		}
	 }
	  

}
