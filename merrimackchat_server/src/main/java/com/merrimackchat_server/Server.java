package com.merrimackchat_server;

import java.io.*;
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
                // Spawn a new thread for each client (person using chatting software)
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
        
        private InputStream in;
        private OutputStream out;
        
        public ClientThread() {
            try {
                // Get audio input from the client (speaker)
                in = connection.getInputStream();
                // Get audio output from the client (mic)
                out = connection.getOutputStream();
                
            } catch (IOException ex) {
                System.err.println("Error with I/O at server.");
            }
        }
        
        /**
         * Broadcasts audio to all clients connected.
         * 
         * @param input audio input
         */
        private void broadcast(int input) {
            for(int i = 0; i < threads.size(); i++) {
                threads.get(i).play(input, i);
            }
        }
        
        /**
         * While the client's thread is running.
         */
        @Override
        public void run() {
            // Container for our mic input reader
            int readData = -2; // -1 is end of line. Normal range is 0-255.
            
            // Run while the connection is open
            while(true) {
                try { // If data is read
                    readData = in.read();
                } catch (IOException e) {
                    
                }
                // And it is not 'nothing'
                if(readData != -2) {
                    broadcast(readData); // Broadcast the message
                    readData = -2; // And set readData container to our default number
                }
            }
            
        }
        
        /**
         * Writes the audio input to the socket's output line. This will play
         * the audio on the client's speakers.
         * 
         * @param input audio input
         * @param threadNum number of thread running (troubleshooting)
         */
        public void play(int input, int threadNum) {
            try {
                out.write(input);
            } catch (IOException ex) {
                System.err.println("Cannot write audio out to socket " + threadNum);
            }
        }
    }
    
}
