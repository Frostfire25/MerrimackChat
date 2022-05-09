package com.merrimackchat_server;

import com.merrimackchat_server.channel.FileManager;
import com.merrimackchat_server.terminal.Console;
import com.merrimackchat_server.channel.ChannelManager;
import com.merrimackchat_server.client.ClientManager;
import com.merrimackchat_server.server_properties.ServerProperties;
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
    private static FileManager fileManager;
    
    @Getter
    private static Server server;
    
    @Getter
    private static Console console;
    
    @Getter
    private static ServerProperties serverProperties;
    
    public static void main(String[] args) {
               
        // Initilizes the channel manager and client manager and channelFileManager
        clientManager = new ClientManager();
        channelManager = new ChannelManager();
        fileManager = new FileManager(channelManager);
        
        // Creates the server properties
        serverProperties = new ServerProperties(fileManager);
        
        // Starts the server
        server = new Server(serverProperties.PORT, serverProperties.ADRESS);
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
        fileManager.loadChannels();
        
        // Loads the sounds for channels, temporarily disabaling.
        //channelManager.assignJoinAndLeaveSoundBuffer();
        
        // Opens the terminal
        System.out.println("Opening Command Terminal:");
        console = new Console();
        
    }
}
