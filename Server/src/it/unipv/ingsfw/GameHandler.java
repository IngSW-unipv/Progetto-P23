package it.unipv.ingsfw;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameHandler implements Runnable {
    private Socket clientSocket1;
    private Socket clientSocket2;
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    // Constructor
    public GameHandler(Socket socket1, Socket socket2)
    {
        this.clientSocket1 = socket1;
        this.clientSocket2 = socket2;
    }

    public void run()
    {
        PrintWriter out1 = null;
        BufferedReader in1 = null;
        PrintWriter out2 = null;
        BufferedReader in2 = null;
        try {
                
              // get the outputstream of client1
            out1 = new PrintWriter(clientSocket1.getOutputStream(), true);

              // get the inputstream of client1
            in1 = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            
              // get the outputstream of client2
            out2 = new PrintWriter(clientSocket2.getOutputStream(), true);
            
            // get the inputstream of client2
            //in2 = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

            String line1 = "WHITE";
            String line2 = "BLACK";
            
            while (!line1.equals("Done") && !line2.equals("Done")) {
				try {

					line1 = in1.readLine();//messageQueue.take();
					System.out.println(line1);         
					out2.println(line1);
					
					//if(line1.equals("Done")) break;
					

					line2 = in2.readLine();//messageQueue.take();
					System.out.println(line2);
					out1.println(line2);
					
					//if(line2.equals("Done")) break;

				} catch (IOException i) {
					System.out.println(i);
				}
			}
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out1 != null) {
                    out1.close();
                }
                if (in1 != null) {
                    in1.close();
                    clientSocket1.close();
                }
                if (out2 != null) {
                    out2.close();
                }
                if (in2 != null) {
                    in2.close();
                    clientSocket2.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

