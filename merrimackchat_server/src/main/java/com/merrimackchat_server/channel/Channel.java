package com.merrimackchat_server.channel;

import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.manager.Client;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class Channel {
    
    @Getter private String name;
    @Getter private byte id;
    @Getter private ArrayList<Client> clients = new ArrayList<>();

    Channel(String name, byte id) {
        this.name = name;
        this.id = id;
    }
    
    /**
     * Remove client by ID.
     * 
     * @param clientID client's ID.
     */
    public void remove(byte clientID) {
        clients.remove(ServerDriver.getClientManager().getClientMap().get(clientID));
    }
    
    /**
     * Add client by ID.
     * 
     * @param clientID client's ID.
     */
    public void add(byte clientID) {
        clients.add(ServerDriver.getClientManager().getClientMap().get(clientID));
    }
    
    /**
     * Clears clients from the channel.
     */
    public void clear() {
        clients.clear();
    }
}
