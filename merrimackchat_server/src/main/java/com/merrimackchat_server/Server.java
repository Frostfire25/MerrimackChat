package com.merrimackchat_server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Derek Costello
 */
public class Server extends Thread{
    
    private Socket connection = null;
    private ServerSocket server = null;
    private int port;
    private ArrayList<ClientThread> threads = new ArrayList<>();

    // Constructor with port 
    public Server(int p) {
        this.port = p;

        // Attempt to build a server socket to the port
        try {
            server = new ServerSocket(this.port);
        } catch (IOException ex) {
            System.out.println("Problem occurred when creating server on port: " + this.port);
        }
    }

    /**
     * Server's Run method simply spawns a new thread whenever a client connects
     * to the server.
     */
    @Override
    public void run() {
        while (true) {
            
            try {
                // Every time a connection is made, spawn a new client thread
                connection = server.accept();
                // Spawn a new thread for each chatter (client)
                ClientThread newThread = new ClientThread();
                newThread.start();
                threads.add(newThread);
            } catch (IOException ex) {
                System.err.println("Connection to client can't be established");
            } catch (NullPointerException ex) {
                System.err.println("Connection to client can't be established");
            }
        }
    }
    
    public class ClientThread extends Thread {
        
        public ClientThread() {
            try {
                // Get audio input from the client (speaker; connection.getInputStream())
                // Get audio output from the client (mic; connection.getOutputStream())
                
            } catch (IOException ex) {
                System.err.println("Error with I/O at server.");
            }
        }
    }
    
}
