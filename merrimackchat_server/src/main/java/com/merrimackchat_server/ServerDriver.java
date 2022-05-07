package com.merrimackchat_server;

import com.merrimackchat_server.channel.ChannelManager;
import com.merrimackchat_server.client.ClientManager;
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
        
        // Message displaying the server has been started correctly.
        System.out.println("Server has been opened on localhost:"+PORT);
        System.out.println("Awating the connection of clients.");
               
    }
}
