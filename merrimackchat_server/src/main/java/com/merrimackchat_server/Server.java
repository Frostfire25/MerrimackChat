package com.merrimackchat_server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                ClientThread newThread = new ClientThread(connection.getInetAddress().getHostAddress(), connection.getLocalPort());
                newThread.start();
                threads.add(newThread);
            } catch (IOException ex) {
                System.err.println("Connection to client can't be established");
            } catch (NullPointerException ex) {
                System.err.println("Connection to client can't be established");
            }
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
     * Broadcasts audio to all clients connected.
     * 
     * @param input audio input
     */
    private void broadcast(byte[] input) {
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).play(input, i);
        }
    }
    
    public class ClientThread extends Thread {
        
        private InputStream in;
        private OutputStream out;
        
        public ClientThread(String address, int port) {
            try {
                // Get audio input from the client (speaker)
                in = connection.getInputStream();
                // Get audio output from the client (mic)
                out = connection.getOutputStream();
                
                System.out.println("New client thread has been established " + address + " : " + port);
                
            } catch (IOException ex) {
                System.err.println("Error with I/O at server.");
            }
        }
        
        /**
         * While the client's thread is running.
         */
        @Override
        public void run() {
            // Alex's changes
            //Changing things will audio
            
            // Container for our mic input reader
            byte[] readData = null; // -1 is end of line. Normal range is 0-255.
            
            
            
            // Run while the connection is open
            while(true) {
                try { // If data is read
                    // Number here determines the delay, since are waiting for x amount of bytes to come through.
                    // Starting number was 1962
                    // Tested working values are 1962, 1284
                    
                    readData = in.readNBytes(1000);
                    System.out.println(Arrays.toString(readData));
                } catch (IOException e) {
                    
                }
                // And it is not 'nothing'
                if(readData != null) {
                    broadcast(readData); // Broadcast the message
                    readData = null; // And set readData container to our default number
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
                ex.printStackTrace();
                System.err.println("Cannot write audio out to socket " + threadNum);
            }
        }
        
        /**
         * Writes the audio input to the socket's output line. This will play
         * the audio on the client's speakers.
         * 
         * @param input audio input
         * @param threadNum number of thread running (troubleshooting)
         */
        public void play(byte[] input, int threadNum) {
            try {
                out.write(input);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Cannot write audio out to socket " + threadNum);
            }
        }
    }
    
}
