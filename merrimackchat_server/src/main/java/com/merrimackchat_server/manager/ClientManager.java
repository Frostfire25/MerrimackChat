package com.merrimackchat_server.manager;

import com.merrimackchat_server.util.Pair;
import java.util.HashMap;
import lombok.Getter;

/**
 *
 * @author Alex
 */
public class ClientManager {
    
    @Getter
    // ID of the User to the Client
    private HashMap<Byte, Client> clientMap = new HashMap<>();
    
    public ClientManager() {
        
    }
    
    /**
     * Allows a client to join the server
     * 
     * @param name Requested name from the client
     * @param address address of the client
     * @param port port of the client
     * @return If the client was created correctly
     */
    public Pair<Boolean, Client> clientJoined(String name, String address, int port) {
        byte id = getID();
        
        // If the id is greater than the max allowed value, we must return false
        if(id == Byte.MAX_VALUE) return new Pair(false, null);
        
        // Creates and assigns a client; -1 implies no channle is joined
        Client client = new Client(name, address, port, id, (byte) -1);
        clientMap.put(id, client);
        
        // Send back the ID to the client Here
        // ToDo..
        
        // User was successfully assigned
        return new Pair(true, client);
    }
    
    public boolean channelJoined(byte userID, byte channelID) {
        // Remove from old channel
        
        // Add to new channel
        
        // Set the client's channel ID
        clientMap.get(userID).setChannel(channelID);
        
        return false;
    }
    
    /**
     * Gets a user id
     * Goes from the Byte.MIN_VALUE up to the Byte.MAX_VALUE
     */
    private byte getID() {
        if(clientMap.isEmpty()) return Byte.MIN_VALUE;
        
        // Gets the next byte value to be used
        for(byte b = Byte.MIN_VALUE; b <= Byte.MAX_VALUE; b++) {
            if(!clientMap.containsKey(b)) return b;
        }        
        
        return Byte.MAX_VALUE;
    }
    
}
