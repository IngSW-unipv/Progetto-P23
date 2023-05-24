package it.unipv.ingsfw.controller;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;


import it.unipv.ingsfw.chess.ChessColor;
import it.unipv.ingsfw.chess.game.GameModel;
import it.unipv.ingsfw.chess.game.Move;
import it.unipv.ingsfw.chess.game.Square;
import it.unipv.ingsfw.chess.game.Status;
import it.unipv.ingsfw.chess.pieces.Piece;
import it.unipv.ingsfw.gui.Frame;
import it.unipv.ingsfw.gui.GameBoard;
import it.unipv.ingsfw.gui.GamePanel;
import it.unipv.ingsfw.gui.GameToolBar;
import it.unipv.ingsfw.gui.buttons.GameButton;


public class OnlineController extends MessageReceivedListener {

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
	private DataInputStream in = null;
	private PrintWriter out = null;	
	private String line = "";	
	private BufferedReader reader;
	private MessageReceivedListener messageReceivedListener;


	public OnlineController(GameModel model,String address,int port) {
		super();
		this.model = model;
		//this.view = view;

		try{
			socket = new Socket(address, port);
			
			// reading from server
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// writing to server
			out = new PrintWriter(socket.getOutputStream(), true);

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


			line = in.readLine();

			System.out.println(line);
			if(line.equals("White")) {
				player.setColor(ChessColor.WHITE);
				System.out.println("Sei il bianco, attendi avversario ...");
				view = new GamePanel (ChessColor.WHITE);
				


			}
			else {
				player.setColor(ChessColor.BLACK);
				view = new GamePanel (ChessColor.BLACK);
				System.out.println("Sei il nero, attendi la prima mossa dell'avversario.");

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		viewBoard = view.getGameBoard();
		toolBar = view.getGameToolBar();
		tasti = viewBoard.getTasti();
		inizializeView (model);
		currentPlayer = model.getCurrentPlayer();
		currentStatus = model.getGameStatus();
		startPosition = new Square (0,0);
		firstClick = true;
		view.updateToolBar(currentPlayer, currentStatus);





		toolBar.getButton1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.resetGame();
				inizializeView (model);
				currentPlayer = model.getCurrentPlayer();
				startPosition = new Square (0,0);
				firstClick = true;
				view.updateToolBar(currentPlayer,currentStatus);
			}
		});

		toolBar.getButton2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.resignation();



			}
		});


		tasti = viewBoard.getTasti();

		for (int y = 0 ; y <8;  y++) {
			for (int x = 0 ; x < 8 ;x++) {		
				tasti[x][y].addActionListener(new ActionListener() {


					@Override
					public void actionPerformed(ActionEvent e) {

						//	model.initTurn();
						GameButton pressed = (GameButton)e.getSource();

						Square genericPosition = pressed.getChessPosition();


						if (model.isOccupied(genericPosition) && 
								(model.getBoard().getSquare(genericPosition.getX(),genericPosition.getY()).getPieceColor() == getPlayerColor()) &&
								(firstClick)) {
							//model.initTurn();
							//toolBar.updateStatus(model.getGameStatus());

							Toolkit.getDefaultToolkit().beep();
							colorThis = model.getPositions(genericPosition);

							
							for (Square s : colorThis) {
								tasti[s.getX()][s.getY()].color();
							}
							//		tasti[genericPosition.getX()][genericPosition.getY()]

							startPosition = genericPosition;
							firstClick = false;




						}

						else if (!firstClick && model.isValidMove(new Move (startPosition,genericPosition))) {
							Toolkit.getDefaultToolkit().beep();
							viewBoard.swapIcon(startPosition,genericPosition );
							Move m = new Move (startPosition,genericPosition);
							model.makeMove(m);
							sendMove(m);



							tasti[startPosition.getX()][startPosition.getY()].reColor();
							for (Square s : colorThis) {
								tasti[s.getX()][s.getY()].reColor();
							}
							firstClick = true;
							currentPlayer = model.getCurrentPlayer();
							currentStatus = model.getGameStatus();
							view.updateToolBar(currentPlayer,currentStatus);
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
	
	public GamePanel getGamePanel () {
		return view;
	}





	public void setMessageReceivedListener(MessageReceivedListener listener) {
		this.messageReceivedListener = listener;
	}
	private void fireMessageReceivedEvent(String message) {
		if (messageReceivedListener != null) {
			messageReceivedListener.onMessageReceived(message);
		}

	}
	public ChessColor getPlayerColor () {
		return player.getColor();
	}
	@Override
	public void onMessageReceived(String message) {
		System.out.println("Messaggio ricevuto: " + message);
		if(message.length()!=4) {}
		else {
			Move m2=new Move(message);		                
			model.makeMove(m2);
			
			viewBoard.swapIcon(m2.getInitialPosition() ,m2.getFinalPosition());
			currentPlayer = model.getCurrentPlayer();
			currentStatus = model.getGameStatus();
			view.updateToolBar(currentPlayer,currentStatus);
			inizializeView (model);
		}
	}
	public void sendMove(Move m) {

		line = m.toString();
		out.println(line);			
	}


}