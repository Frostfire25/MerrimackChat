package com.merrimackchat_server;

import com.merrimackchat_server.exceptions.ServerFullException;
import com.merrimackchat_server.manager.Client;
import com.merrimackchat_server.manager.ClientThread;
import com.merrimackchat_server.util.Pair;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
@Getter
public class Server extends Thread {
    
    private Socket connection = null;
    private ServerSocket server = null;
    private int port;

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
                //ClientThread newThread = new ClientThread(connection.getInetAddress().getHostAddress(), connection.getLocalPort());
                //newThread.start();
                
                // Adds the new client to the client manager
                String name = "Alex-Update";
                Pair<Boolean, Client> value = ServerDriver.getClientManager().clientJoined(name, connection.getInetAddress().getHostAddress(), connection.getLocalPort());
                
                // If the client could not join, ex. server is full
                if(!value.getValue1() || value.getValue2() == null) throw new ServerFullException("Server "+server.getInetAddress().getHostAddress()+" has reached member capacity at this time.");
                
                Client client = value.getValue2();
                client.start();
                
            } catch (IOException ex) {
                System.err.println("Connection to client can't be established");
            } catch (NullPointerException ex) {
                System.err.println("Connection to client can't be established");
            } catch (ServerFullException ex) {
                System.out.println("The server is full, sorry.");
            }
        }
    }
    
    /**
     * Broadcasts audio to all clients connected.
     * 
     * @param input audio input
     */
    public void broadcast(int input) {
        Collection<Client> clients = ServerDriver.getClientManager().getClientMap().values();
        
        // Play for every client
        clients.stream().forEach(n -> n.play(input, n.getID()));
    }
    
    /**
     * Broadcasts audio to all clients connected.
     * 
     * @param input audio input
     * @param senderID ID of the user sending the audio.
     */
    public void broadcast(byte[] input, byte senderID) {
        Collection<Client> clients = ServerDriver.getClientManager().getClientMap().values();
        
        // Play for every client
        // Added input for no audio echo / feedback, if you want feedback just remove the filter statement.
        clients.stream().filter(n -> n.getID() != senderID).forEach(n -> n.play(input, n.getID()));
    }
     
}
