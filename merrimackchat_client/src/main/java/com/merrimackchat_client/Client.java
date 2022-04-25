package com.merrimackchat_client;

import java.io.IOException;
import java.net.*;

/**
 * Class used to handle Client I/O to the server.
 * Inspired by : https://github.com/surajkumaruk96/VoIP-Client-Server/blob/master/VoIPClient/src/VoipClient.java
 *
 * @author Derek Costello, Suraj Kumar (original author)
 */
public class Client implements Runnable{
    
    private Microphone mic;
    private Speaker speaker;
    private Socket socket;
    private static final String IP = "127.0.0.1";
    private static final int PORT = 5000;
    
    /**
     * Constructor initializing the mic and speaker for the client's audio I/O.
     */
    public Client(){
        mic = new Microphone();
        speaker = new Speaker();
    }
    
    @Override
    public void run() {
        // Try to connect to the server, if we can't, crash the program
        try {
            socket = new Socket(IP, PORT);
        } catch (IOException e) {
            System.err.println("Error connecting to server " + IP + " on port " + PORT);
            System.err.println(e);
            System.exit(1);
        }
        
        
        /**
         * Play audio from server to client's speakers.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                // If the speakers successfully opened
                if(speaker.open()) {
                    speaker.start(); // Starts the SourceDataLine
                    
                    while(socket.isConnected()) { // While a connection is still established
                        
                        try { // Try writing data to the Client's speakers
                            byte[] buffer = new byte[speaker.getBufferSize() / 5];
                            int read = socket.getInputStream().read(buffer, 0, buffer.length);
                            speaker.write(buffer, 0, read);
                        } catch (IOException e) {
                            System.err.println("Could not read audio data from server: " + e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.err.println("Improper argument exception: " + e.getMessage());
                            e.printStackTrace();
                        }
                        
                    }
                } else {
                    System.err.println("Failed to successfully open speakers");
                }
            }
        }).start();
        
        /**
         * Send mic audio from client out to the server.
         */
        new Thread(new Runnable(){
            @Override
            public void run() {
                // If the mic successfully opened
                if(mic.open()) {
                    mic.start(); // Starts the TargetDataLine
                    
                    while(socket.isConnected()) { // While a connection is still established
                        try { // Try writing mic data to the server's data stream
                                byte[] buffer = new byte[mic.getBufferSize() / 5];
                                int read = mic.read(buffer, 0, buffer.length);
                                socket.getOutputStream().write(buffer, 0, read);
                            } catch (IOException e) {
                                System.err.println("Could not write microphone audio data to server: " + e.getMessage());
                            }
                    }
                } else {
                    System.err.println("Failed to successfully open mic");
                }            
            }
        }).start();
        
        
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        new Thread(client).start();
    }
    
}
