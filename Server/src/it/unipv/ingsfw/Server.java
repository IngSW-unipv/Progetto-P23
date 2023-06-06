package it.unipv.ingsfw;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;



import com.google.gson.Gson;

import it.unipv.ingsfw.Exception.AccountNotFoundException;
import it.unipv.ingsfw.classi.Stats;
import it.unipv.ingsfw.classi.User;
import it.unipv.ingsfw.database.UserDAO;


// Server class
class Server implements MessageReceivedListener{
	private ArrayList<Socket> onlineUsers;
	private MessageReceivedListener messageReceivedListener;
	private ServerSocket server;
	private Socket client,client1,client2;
	private boolean waiting;
	private DataOutputStream output ;
	private OutputStream outputStr;
	private Map<Socket, Thread> childThreads;

	private Map<Thread,BlockingQueue<String>> queueMap;
	public OutputStream getOutputStr() {
		return outputStr;
	}





	public void setOutputStream(OutputStream outputStream) {
		this.outputStr = outputStream;
	}

	private PrintStream os,os2;
	private BufferedReader reader;
	private User user;







	public Server() {
		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);
			this.waiting=false;
			childThreads = new HashMap<>();
			queueMap = new HashMap<>();


		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}





	public static void main(String[] args) {
		Server server = new Server();
		server.setMessageReceivedListener(new MessageReceivedListener() {

			@Override
			public void onMessageReceived(String message) {

				server.onMessageReceived(message);

			}
		});
		try {
			while (true) {

				// socket object to receive incoming client
				// requests
				server.setClient(server.getServer().accept());

				server.setReader(new BufferedReader(new InputStreamReader(server.getClient().getInputStream())));

				server.setOutputStream(server.getClient().getOutputStream());
				// Displaying that new client is connected
				// to server
				System.out.println("New client1 connected"
						+ server.getClient().getInetAddress()
						.getHostAddress());


				//String line = "Connected to Server, make your request";
				String line = "???";
				server.setOutput(new DataOutputStream(server.getClient().getOutputStream()));
				server.setOs(new PrintStream(server.getOutput(), false));
				//mando info ai client
				server.getOs().println(line);

				Thread messageListenerThread = new Thread(() -> {
					try {
						while (true) {

							String message = server.getReader().readLine();
							if (message != null) {
								server.fireMessageReceivedEvent(message);
							}
						}
					} catch (IOException e) {

						e.printStackTrace();
					}
				});
				messageListenerThread.start();

			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server.getServer() != null) {
				try {
					server.getServer().close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void fireMessageReceivedEvent(String message) {
		if (messageReceivedListener != null) {
			messageReceivedListener.onMessageReceived(message);
		}

	}





	public DataOutputStream getOutput() {
		return output;
	}





	public void setOutput(DataOutputStream output) {
		this.output = output;
	}





	public PrintStream getOs() {
		return os;
	}





	public void setOs(PrintStream os) {
		this.os = os;
	}





	public Socket getClient() {
		return client;
	}


	public void setClient(Socket client) {
		this.client = client;
	}

	public ArrayList<Socket> getOnlineUsers() {
		return onlineUsers;
	}


	public void setOnlineUsers(ArrayList<Socket> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}


	public ServerSocket getServer() {
		return server;
	}


	public void setServer(ServerSocket server) {
		this.server = server;
	}


	public Socket getClient1() {
		return client1;
	}


	public void setClient1(Socket client1) {
		this.client1 = client1;
	}


	public Socket getClient2() {
		return client2;
	}


	public void setClient2(Socket client2) {
		this.client2 = client2;
	}


	public boolean isWaiting() {
		return waiting;
	}


	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public MessageReceivedListener getMessageReceivedListener() {
		return messageReceivedListener;
	}


	public void setMessageReceivedListener(MessageReceivedListener messageReceivedListener) {
		this.messageReceivedListener = messageReceivedListener;
	}
	public PrintStream getOs2() {
		return os2;
	}

	public void setOs2(PrintStream os2) {
		this.os2 = os2;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public void loginThread() {
		BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

		Thread loginThread = new Thread(() -> {
			try {
				System.out.println("thread");
				boolean x = true;
				PrintStream oss = getOs();
				BufferedReader reader = getReader();
				oss.println("username-password");
				while (x) {

					String message = messageQueue.take();
					System.out.println(message);
					if (message.equals("stats pls")) {
						Stats stats = user.getStats();
						Gson gson = new Gson();	
						String jsonString = gson.toJson(stats);
						oss.println(jsonString);
						x=false;

					}
					else {
						String[] uspsw = message.split("-",-1);
						User user = new User(uspsw[0],uspsw[1]);

						System.out.println("Username: "+user.getUsername());
						//metodo userDAO per check password
						checkLogin(uspsw[0], uspsw[1]);
						//esito positivo
						oss.println("login accepted");

					}
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (AccountNotFoundException e) {
				
				e.printStackTrace();
			} catch (WrongPasswordException e) {
				
				e.printStackTrace();
			}
		});
		childThreads.put(client, loginThread); 
		
		
		queueMap.put(loginThread, messageQueue);
		loginThread.start();

	}
	
	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	public void checkLogin(String username, String password) throws AccountNotFoundException, WrongPasswordException {
		User u = new UserDAO().validUser(username);
		String pass = new String(password);
		
		if (u == null)
			throw new AccountNotFoundException(username);
		if (!u.getPassword().equals(pass))
			throw new WrongPasswordException(username);
		
		this.setUser(u);
	}

	@Override
	public void onMessageReceived(String message) {
		
		System.out.println(message);
		switch (message) {
		case "login":
			System.out.println("qui");
			// fai login
			//gestire richiesta login database
			// new DBConnection, query check username e password + exception, 
			// esito posivo mandare al client statistiche legate username(query)
			loginThread();

			break;

		case "gioca":



			if(waiting == false) {
				waiting=true;
				this.client1=this.client;
				String line = "White";
				//mando info al client1
				os.println(line);
				os2=os;

			}
			else {
				String line2 = "Black";

				//mando info ai client
				os.println(line2);
				// inizio della partita
				os2.println("Inizia");
				this.client2=this.client;
				waiting=false;
				GameHandler clientSock
				= new GameHandler(client1,client2);


				new Thread(clientSock).start();
			}
			break;


		default:
			try {
				queueMap.get(childThreads.get(client)).put(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}
}




//	public static void main(String[] args)
//	{
//		ServerSocket server = null;
//
//		try {
//
//			// server is listening on port 1234
//			server = new ServerSocket(1234);
//			server.setReuseAddress(true);
//			MessageReceivedListener mrl = new MessageReceivedListener() {
//				
//				@Override
//				public void onMessageReceived(String message) {
//					this.onMessageReceived(message);
//					
//				}
//			};
//
//			
//			// running infinite loop for getting
//			// client request
//			while (true) {
//
//				// socket object to receive incoming client
//				// requests
//				Socket client1 = server.accept();
//
//				// Displaying that new client is connected
//				// to server
//				System.out.println("New client1 connected"
//						+ client1.getInetAddress()
//						.getHostAddress());
//				
//
//				String line = "White";
//				DataOutputStream output2 = new DataOutputStream(client1.getOutputStream());
//				PrintStream os2 = new PrintStream(output2, false);
//				//mando info ai client
//				os2.println(line);
//
//				//gestire richiesta login database
//				// new DBConnection, query check username e password + exception, 
//				// esito posivo mandare al client statistiche legate username(query)
//
//				Socket client2 = server.accept();
//				System.out.println("New client2 connected"
//						+ client2.getInetAddress()
//						.getHostAddress());
//				DataInputStream in2 = new DataInputStream(new BufferedInputStream(client2.getInputStream()));
//				//
//
//				DataOutputStream output = new DataOutputStream(client2.getOutputStream());
//				PrintStream os = new PrintStream(output, false);
//
//
//				String line2 = "Black";
//
//				//mando info ai client
//				os.println(line2);
//				// inizio della partita
//				os2.println("Inizia");
//
//				// create a new thread object
//				GameHandler clientSock
//				= new GameHandler(client1,client2);
//
//				// This thread will handle the clients
//				// separately
//				new Thread(clientSock).start();
//			}
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			if (server != null) {
//				try {
//					server.close();
//				}
//				catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

