package com.merrimackchat_server;

import com.merrimackchat_server.channel.ChannelManager;
import com.merrimackchat_server.client.ClientManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 * Class driving the functionality of our server.
 *
 * @author Alex, Derek
 */
public class ServerDriver {
    
    public static final int PORT = 5000;
    
    @Getter
    private static ClientManager clientManager;
    
    @Getter
    private static ChannelManager channelManager;
    
    @Getter
    private static Server server;
    
    public static void main(String[] args) {
               
        // Initilizes the channel manager and client manager
        clientManager = new ClientManager();
        channelManager = new ChannelManager();
      
        // Starts the server
        server = new Server(PORT);
        server.start();
        
        try {
            // Message displaying the server has been started correctly.
            System.out.println("Server has been opened on " + InetAddress.getLocalHost() + ":"+PORT);
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host");
        }
        System.out.println("Awating the connection of clients.");
               
    }
}
