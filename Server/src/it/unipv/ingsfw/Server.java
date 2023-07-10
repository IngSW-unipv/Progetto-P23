
package it.unipv.ingsfw;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import it.unipv.ingsfw.classi.User;
import it.unipv.ingsfw.database.UserDAO;
import it.unipv.ingsfw.exception.AccountNotFoundException;
import it.unipv.ingsfw.exception.ExistingAccountException;
import it.unipv.ingsfw.exception.WrongPasswordException;


// Server class
public class Server implements MessageReceivedListener{
	private Map<Socket,User> onlineUsers;
	private MessageReceivedListener messageReceivedListener;
	private ServerSocket server;
	private Socket client,client1,client2;
	private boolean waiting;
	private DataOutputStream output ;
	private OutputStream outputStr;
	private Map<Socket, Thread> childThreads;
	private Map<Thread,BlockingQueue<String>> queueMap;
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
			onlineUsers =new HashMap<>();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessageReceived(String message) {

		System.out.println(message);

		switch (message) {
		case "login":

			loginThread();

			break;

		case "signup":

			signupThread();
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
				gameThread(client1,client2);

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

	public void loginThread() {
		BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

		Thread loginThread = new Thread(() -> {
			try {
				System.out.println("loginThread");
				boolean x = true;
				PrintStream oss = getOs();
				oss.println("username_password");
				while (x) {

					String message = messageQueue.take();
					System.out.println(message);
					if (message.equals("stats pls")) {
						x=false;

					}
					else {
						String[] uspsw = message.split("-",-1);
						User user = new User(uspsw[0],uspsw[1]);

						System.out.println("Username: "+user.getUsername());
						//metodo userDAO per check password
						checkLogin(uspsw[0], uspsw[1]);
						//esito positivo
						user = getStats(uspsw[0]);

						
						//onlineUsers.put(client, user);

						oss.println("login accepted-"+user.getWin()+"-"+user.getDraw()+"-"+user.getLose());

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

	private synchronized void signupThread() {

		BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

		Thread signupThread = new Thread(() -> {
			try {
				System.out.println("signupThread");
				boolean x = true;
				PrintStream oss = getOs();
				oss.println("username_password");
				while (x) {
					try {
						String message = messageQueue.take();
						System.out.println(message);
						if (message.equals("stats pls")) {
							x=false;

						}
						else {
							String[] uspsw = message.split("-",-1);
							User user = new User(uspsw[0],uspsw[1]);

							System.out.println("Username: "+user.getUsername());
							//metodo userDAO per check password
							if(insertUser(uspsw[0], uspsw[1])) {
								//esito positivo
								oss.println("registration completed");
							}
							else {		
								throw  new ExistingAccountException(os);
							}
						}
					}catch(ExistingAccountException e) {
						e.printStackTrace();
					}

				}
			}catch (InterruptedException e) {

				e.printStackTrace();
			}
		});
		childThreads.put(client, signupThread); 
		queueMap.put(signupThread, messageQueue);
		signupThread.start();

	}

	private void gameThread(Socket client1, Socket client2) {
		BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
		Thread gameThread = new Thread(() -> {
			try {
				System.out.println("gameThread");

				
				PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
				PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
				String line1 = "",line2 = "";
				boolean gameW = true;
				boolean gameB = true;
				try {
					while (gameW || gameB){



						line1 = messageQueue.take();

						System.out.println(line1); 

						gameW = endCheck(line1, out2,out1,client1);



						line2 = messageQueue.take();
						System.out.println(line2);

						gameB = endCheck(line2, out1,out2,client2);


					}
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}

				finally {

					if (out1 != null) {
						out1.close();
					}

					if (out2 != null) {
						out2.close();
					}
				}

			}
			catch (IOException e) {
				e.printStackTrace();
			}	

		});
		childThreads.put(client, gameThread); 
		queueMap.put(gameThread, messageQueue);
		gameThread.start();

	}

	
	private void fireMessageReceivedEvent(String message) {
		if (messageReceivedListener != null) {
			messageReceivedListener.onMessageReceived(message);
		}

	}

	public OutputStream getOutputStr() {
		return outputStr;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStr = outputStream;
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


	public User getStats(String username) {
		User u = new UserDAO().setStats(username);

		return u;		
	}


	public void checkLogin(String username, String password) throws AccountNotFoundException, WrongPasswordException {
		User u = new UserDAO().validUser(username);
		String pass = new String(password);

		if (u == null)
			throw new AccountNotFoundException(os);
		if (!u.getPsw().equals(pass))
			throw new WrongPasswordException(os);

		this.setUser(u);
	}



	public boolean endCheck(String line,PrintWriter out2,PrintWriter out1 ,Socket client) {
		String[] line1 = line.split("-",-1);
		if(line1[0].equals("Done")) {
			new UserDAO().addLose(line1[1]);
			user = getStats(line1[1]);
			out1.println("stats-"+user.getWin()+"-"+user.getDraw()+"-"+user.getLose());

			out2.println("forfait"); 
			return false;

		}
		else if(line1[0].equals("vittoria")){
			new UserDAO().addWin(line1[1]);
			user = getStats(line1[1]);
			out1.println("stats-"+user.getWin()+"-"+user.getDraw()+"-"+user.getLose());
			return false;

		}
		else if(line1[0].equals("sconfitta")){
			new UserDAO().addLose(line1[1]);
			user = getStats(line1[1]);
			out1.println("stats-"+user.getWin()+"-"+user.getDraw()+"-"+user.getLose());
			return false;
		}
		else if(line1[0].equals("pareggio")){
			new UserDAO().addDraw(line1[1]);
			user = getStats(line1[1]);
			out1.println("stats-"+user.getWin()+"-"+user.getDraw()+"-"+user.getLose());
			return false;
		}
		else {
			out2.println(line1[0]);
			return true;
		}
	}

	

	public boolean insertUser(String username, String password){
		return (new UserDAO().insertUser(new User(username,password)));		

	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
						BufferedReader reader = server.getReader();
						while (true) {

							String message = reader.readLine();
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
}




