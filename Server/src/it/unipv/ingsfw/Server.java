package it.unipv.ingsfw;

import java.io.*;
import java.net.*;
  
// Server class
class Server {
    public static void main(String[] args)
    {
        ServerSocket server = null;
  
        try {
  
            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
  
            // running infinite loop for getting
            // client request
            while (true) {
  
                // socket object to receive incoming client
                // requests
                Socket client1 = server.accept();
  
                // Displaying that new client is connected
                // to server
                System.out.println("New client1 connected"
                                   + client1.getInetAddress()
                                         .getHostAddress());
                
                String line = "White";
                DataOutputStream output2 = new DataOutputStream(client1.getOutputStream());
    			PrintStream os2 = new PrintStream(output2, false);
    			//mando info ai client
    			os2.println(line);
               
                Socket client2 = server.accept();
                System.out.println("New client2 connected"
                        + client2.getInetAddress()
                              .getHostAddress());
                DataInputStream in2 = new DataInputStream(new BufferedInputStream(client2.getInputStream()));
    			//

                DataOutputStream output = new DataOutputStream(client2.getOutputStream());
    			PrintStream os = new PrintStream(output, false);
    			

    			String line2 = "Black";

    			//mando info ai client
    			os.println(line2);
    			// inizio della partita
    			os2.println("Inizia");
  
                // create a new thread object
                ClientHandler clientSock
                    = new ClientHandler(client1,client2);
  
                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
  