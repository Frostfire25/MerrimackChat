package com.merrimackchat_client;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketEncoder;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * Class used to handle Client I/O to the server.
 * Inspired by : https://github.com/surajkumaruk96/VoIP-Client-Server/blob/master/VoIPClient/src/VoipClient.java
 *
 * @author Derek Costello, Suraj Kumar (original author)
 */
public class Client implements Runnable {
    
    private Microphone mic;
    private Speaker speaker;
    private Socket socket;
    private static final String IP = "localhost";
    private static final int PORT = 5000;
    
    private String name = "Alex";
    private byte ID;
    
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
                            
                            System.out.println(Arrays.toString(buffer));
                            
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
                            
                                // Test for sending a packet
                                Packet packet = PacketEncoder.createUserJoinPacket("Alex");
                                packet.send(socket.getOutputStream());
                                
                                // End of Test.
                            
                                byte[] buffer = new byte[mic.getBufferSize() / 5];
                                int read = mic.read(buffer, 0, buffer.length);
                                
                                //System.out.println(Arrays.toString(buffer));
                                
                                // UNCOMMENT TO SEND AUDIO!!! socket.getOutputStream().write(buffer);
                                
                                
                                //socket.getOutputStream().
                                //socket.getOutputStream().write(buffer, 0, read);
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
    
}
