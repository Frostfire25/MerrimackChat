package com.merrimackchat_server;

/**
 * Class driving the functionality of our server.
 *
 * @author Alex, Derek
 */
public class ServerDriver {
    
    private static final int PORT = 5000;
    
    public static void main(String[] args) {
        
        Server server = new Server(PORT);
        server.start();
        
    }
}
