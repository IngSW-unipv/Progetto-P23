
package it.unipv.ingsfw.controller;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JPanel;

import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.controller.dbobject.User;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.chess.game.Move;
import it.unipv.ingsfw.chess.game.Square;
import it.unipv.ingsfw.chess.game.Status;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.controller.interfaces.MessageReceivedListener;
import it.unipv.ingsfw.gui.buttons.GameButton;
import it.unipv.ingsfw.gui.panels.LoginPanel;
import it.unipv.ingsfw.gui.panels.StatsPanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GameBoard;
import it.unipv.ingsfw.gui.panels.gamepanels.GamePanel;
import it.unipv.ingsfw.gui.panels.gamepanels.GameToolBar;


public class OnlineController implements MessageReceivedListener ,Runnable{

	private GameModel model;
	private GamePanel view;
	private GameBoard viewBoard;
	private GameToolBar toolBar;
	private GameButton [][] tasti;
	private ChessColor currentPlayer;
	private Status currentStatus;
	private Boolean firstClick;
	private Square startPosition;
	private List <Square> colorThis;
	private Player player = new Player(ChessColor.BLACK);
	private Socket socket = null;
	private PrintWriter out = null;	
	private String line = "";	
	private BufferedReader reader;
	private MessageReceivedListener messageReceivedListener;
	private User user;
	private LoginPanel loginPanel;
	private StatsPanel statsPanel;


	public OnlineController(GameModel model,String address,int port,LoginPanel loginPanel) {
		super();
		this.loginPanel = loginPanel;
		this.model = model;



		try{

			socket = new Socket(address, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);

			// thread ricezione messaggi 
			Thread messageListenerThread = new Thread(() -> {
				try {
					while (true) {

						String message = reader.readLine();
						if (message != null) {
							fireMessageReceivedEvent(message);
						}
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			});
			messageListenerThread.start();


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {

		viewBoard = view.getGameBoard();		
		tasti = viewBoard.getTasti();
		toolBar = view.getGameToolBar();
		inizializeView (model);
		currentPlayer = model.getCurrentPlayer();
		currentStatus = model.getGameStatus();
		startPosition = new Square (0,0);
		firstClick = true;
		view.updateToolBar(currentPlayer, currentStatus);





		// azione tasto resa
		toolBar.getButton1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(player.getColor()==currentPlayer) {
					if(player.getColor()==ChessColor.WHITE)	{
						model.setGameStatus(Status.BLACK_WIN);
						endCall();
						currentPlayer = model.getCurrentPlayer();
						currentStatus = model.getGameStatus();
						view.updateToolBar(currentPlayer,currentStatus);

					}
					else {
						model.setGameStatus(Status.WHITE_WIN);
						endCall();
						currentPlayer = model.getCurrentPlayer();
						currentStatus = model.getGameStatus();
						view.updateToolBar(currentPlayer,currentStatus);
					}

				}
			}
		});

		// azioni tasti scacchiera

		tasti = viewBoard.getTasti();

		for (int y = 0 ; y <8;  y++) {
			for (int x = 0 ; x < 8 ;x++) {		
				tasti[x][y].addActionListener(new ActionListener() {


					@Override
					public void actionPerformed(ActionEvent e) {

						
						GameButton pressed = (GameButton)e.getSource();
						Square genericPosition = pressed.getChessPosition();


						if (model.isOccupied(genericPosition) && 
								(model.getBoard().getSquare(genericPosition.getX(),genericPosition.getY()).getPieceColor() == getPlayerColor()) &&
								(firstClick)) {
						
							Toolkit.getDefaultToolkit().beep();
							colorThis = model.getPositions(genericPosition);


							for (Square s : colorThis) {
								tasti[s.getX()][s.getY()].color();
							}
							

							startPosition = genericPosition;
							firstClick = false;




						}

						else if (!firstClick && model.isValidMove(new Move (startPosition,genericPosition))) {
							Toolkit.getDefaultToolkit().beep();
							viewBoard.swapIcon(startPosition,genericPosition );
							Move m = new Move (startPosition,genericPosition);
							model.makeMove(m);
							
							// invia mossa al server 
							sendMove(m);
							
							tasti[startPosition.getX()][startPosition.getY()].reColor();
							for (Square s : colorThis) {
								tasti[s.getX()][s.getY()].reColor();
							}
							
							firstClick = true;
							currentPlayer = model.getCurrentPlayer();
							currentStatus = model.getGameStatus();
							view.updateToolBar(currentPlayer,currentStatus);
							vittoria();
							inizializeView (model);

						}

						else if (!firstClick) {

							for (Square s : colorThis) {
								tasti[s.getX()][s.getY()].reColor();

							}
							firstClick = true;
							inizializeView (model);

						}


					}
				});
			}
		}


	}
	
	
	public void vittoria() {
		if(currentStatus == Status.CHECK_MATE || currentStatus == Status.BLACK_WIN || currentStatus == Status.WHITE_WIN) {
			if(currentPlayer==player.getColor()) {
				out.println("sconfitta"+"-"+user.getUsername());
				System.out.println("stampa qui "+user.getUsername());
			}else {


				out.println("vittoria"+"-"+user.getUsername());

			}
		}else if(currentStatus == Status.STALEMATE) {
			out.println("pareggio"+"-"+user.getUsername());
		}
	}


	// inizializza la scacchiera posizionando i pezzi nelle giuste case.
	public void inizializeView (GameModel model) {


		Square [][] casa = model.getB();
		Piece p;

		for (int y = 0 ; y <8;  y++) {
			for (int x = 0 ; x < 8 ;x++) {		

				p  = casa[x][y].getPiece();

				if (p !=  null)		{

					if (p.getColor() == ChessColor.WHITE) {

						switch (p.getType()) {

						case Pawn :
							tasti[x][y].setIcon(viewBoard.getPawnW());
							break;

						case Rook :
							tasti[x][y].setIcon(viewBoard.getRookW());
							break;

						case King :
							tasti[x][y].setIcon(viewBoard.getKingW());
							break;

						case Queen :
							tasti[x][y].setIcon(viewBoard.getQueenW());
							break;

						case Knight :
							tasti[x][y].setIcon(viewBoard.getKnightW());
							break;

						case Bishop :
							tasti[x][y].setIcon(viewBoard.getBishopW());
							break;
						}
					}
					else {

						switch (p.getType()) {

						case Pawn :
							tasti[x][y].setIcon(viewBoard.getPawnB());
							break;

						case Rook :
							tasti[x][y].setIcon(viewBoard.getRookB());
							break;

						case King :
							tasti[x][y].setIcon(viewBoard.getKingB());
							break;


						case Queen :
							tasti[x][y].setIcon(viewBoard.getQueenB());
							break;


						case Knight :
							tasti[x][y].setIcon(viewBoard.getKnightB());
							break;

						case Bishop :
							tasti[x][y].setIcon(viewBoard.getBishopB());
							break;
						}
					}
				}
				else {
					tasti[x][y].setIcon(null);
				}
			}
		}
	}


	// gestione messaggi ricevuti 
	
	private void fireMessageReceivedEvent(String message) {
		if (messageReceivedListener != null) {
			messageReceivedListener.onMessageReceived(message);
		}

	}
 

	public void onMessageReceived(String message) {
		System.out.println("Messaggio ricevuto: " + message);
		String[] message2 = message.split("-",-1);
		if(message2[0].equals("???")) {
			System.out.println("dentro");

		}		
		else if(message2[0].equals("White")) {
			player.setColor(ChessColor.WHITE);
			System.out.println("Sei il bianco, attendi avversario ...");
			statsPanel.setWaiting();
			view = new GamePanel (ChessColor.WHITE,1);

			
		}
		else if(message2[0].equals("Inizia")) {
			//	opponent.setUsername(message2[1]);
			statsPanel.setGame();
			

		}
		else if(message2[0].equals("forfait")) {
			if(player.getColor()==ChessColor.WHITE)	{
				model.setGameStatus(Status.WHITE_WIN);
				currentPlayer = model.getCurrentPlayer();
				currentStatus = model.getGameStatus();
				view.updateToolBar(currentPlayer,currentStatus);
				out.println("vittoria"+"-"+user.getUsername());

			}
			else {
				model.setGameStatus(Status.BLACK_WIN);
				currentPlayer = model.getCurrentPlayer();
				currentStatus = model.getGameStatus();
				view.updateToolBar(currentPlayer,currentStatus);
				out.println("vittoria"+"-"+user.getUsername());
			}
		}
		else if(message2[0].equals("Black")) {
			player.setColor(ChessColor.BLACK);
			view = new GamePanel (ChessColor.BLACK,1);
			statsPanel.setGame();

			//		opponent.setUsername(message2[1]);
			System.out.println("Sei il nero, attendi la prima mossa dell'avversario.");
		}		
		else if(message2[0].length()==4){
			Move m2=new Move(message);		                
			model.makeMove(m2);

			viewBoard.swapIcon(m2.getInitialPosition() ,m2.getFinalPosition());
			currentPlayer = model.getCurrentPlayer();
			currentStatus = model.getGameStatus();
			vittoria();
			view.updateToolBar(currentPlayer,currentStatus);

			inizializeView (model);
		}
		else  if(message2[0].equals("username_password")){
			System.out.println(message);

			out.println(user.getUsername()+"-"+user.getPsw());
		}
		else if(message2[0].equals("login denied") || message2[0].equals("account already exist")){
			loginPanel.changeColor();
		}		
		else if(message2[0].equals("login accepted") || message2[0].equals("registration completed")) {

			user.setWin(Integer.parseInt(message2[1])); 
			user.setDraw(Integer.parseInt(message2[2])); 
			user.setLose(Integer.parseInt(message2[3])); 



		

			statsPanel = (StatsPanel) loginPanel.createStats();
			((StatsPanel) statsPanel).setStats(user.getUsername(),message2[1], message2[2], message2[3]);


		}
		else if(message2[0].equals("stats")) {

			user.setWin(Integer.parseInt(message2[1])); 
			user.setDraw(Integer.parseInt(message2[2])); 
			user.setLose(Integer.parseInt(message2[3])); 
			
			((StatsPanel) statsPanel).setStats(user.getUsername(),message2[1], message2[2], message2[3]);


		}

	}



	public void loginCall() {
		out.println("login");
	}
	public void signupCall() {
		out.println("signup");
	}
	public void playCall() {
		out.println("gioca");
	}
	public void endCall() {
		out.println("Done"+"-"+user.getUsername());

	}

	public void sendMove(Move m) {
		line = m.toString();
		out.println(line);
		System.out.println("Messaggio inviato: "+line);
	}
	
	// getters e setters 
	
	public void setMessageReceivedListener(MessageReceivedListener listener) {
		this.messageReceivedListener = listener;
	}
	
	public JPanel getStatsPanel() {
		return statsPanel;
	}
	public void setStatsPanel(JPanel statsPanel) {
		this.statsPanel = (StatsPanel) statsPanel;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public ChessColor getPlayerColor () {
		return player.getColor();
	}

	public GamePanel getGamePanel () {
		return view;
	}


}
