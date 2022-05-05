package com.merrimackchat_server.client;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
@Getter
public class Client extends ClientThread {
    
    private String userName;
    private String address;
    private int port;
    private byte ID;
    @Setter private byte channel;
    
    public Client(String userName, String address, int port, byte ID, byte channel) {
        super(address, port);
        
        this.userName = userName;
        this.address = address;
        this.port = port;
        this.ID = ID;
        this.channel = channel;
    }
    
}
