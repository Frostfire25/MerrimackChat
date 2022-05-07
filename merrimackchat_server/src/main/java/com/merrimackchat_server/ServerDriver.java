package com.merrimackchat_server;

import com.merrimackchat_server.terminal.Console;
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
    
    @Getter
    private static Console console;
    
    public static void main(String[] args) {
               
        // Initilizes the channel manager and client manager
        clientManager = new ClientManager();
        channelManager = new ChannelManager();
      
        // Starts the server
        server = new Server(PORT);
        server.start();
        // Server has been sarted
        System.out.println(String.format("\n[Server Launched]: The Server has been launched on, IP: %s (%s)", server.getServer().getInetAddress().getHostAddress(), server.getServer().getLocalPort()));
        System.out.println("[Server Socket] " + server.getServer());
        
        // Message displaying the server has been started correctly.
        System.out.println("Server has been opened on localhost:"+PORT);
        System.out.println("Awating the connection of clients.");
               
        System.out.println("Opening Command Terminal:");
        console = new Console();
    }
}
