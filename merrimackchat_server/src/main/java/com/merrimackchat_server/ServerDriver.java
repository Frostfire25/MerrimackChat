package com.merrimackchat_server;

import com.merrimackchat_server.channel.ChannelFileManager;
import com.merrimackchat_server.terminal.Console;
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
    public static final String ADDR = "127.0.0.1";//"10.0.118.2";
    
    @Getter      
    private static ClientManager clientManager;
    
    @Getter  
    private static ChannelManager channelManager;
    
    @Getter
    private static ChannelFileManager channelFileManager;
    
    @Getter 
    private static Server server;
    
    @Getter 
    private static Console console;
    
    public static void main(String[] args) {
               
        // Initilizes the channel manager and client manager and channelFileManager
        clientManager = new ClientManager();
        channelManager = new ChannelManager();
        channelFileManager = new ChannelFileManager(channelManager);
        
        // Starts the server
        server = new Server(PORT, ADDR);
        server.start();
        // Server has been sarted
        System.out.println(String.format("\n[Server Launched]: The Server has been launched on, IP: %s (%s)", server.getServer().getInetAddress().getHostAddress(), server.getServer().getLocalPort()));
        System.out.println("[Server Socket] " + server.getServer());
        
        try {
            // Message displaying the server has been started correctly.
            System.out.println("Server has been opened on " + InetAddress.getLocalHost() + ":"+PORT);
        } catch (UnknownHostException ex) {
            System.out.println("Unknown host");
        }
        System.out.println("Awating the connection of clients.");
               
        // Loads in All channels
        channelFileManager.loadChannels();
        
        // Opens the terminal
        System.out.println("Opening Command Terminal:");
        console = new Console();
        
    }
}
