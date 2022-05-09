package com.merrimackchat_server.channel;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_server.Server;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class Channel {
    
    @Getter private String name;
    @Getter private byte id;
    @Getter private ArrayList<Client> clients = new ArrayList<>();

    public Channel(String name, byte id) {
        this.name = name;
        this.id = id;
    }
    
    /**
     * Remove client by ID.
     * 
     * @param clientID client's ID.
     */
    public void remove(byte clientID) {
        System.out.println("Removing user: " + clientID + " from channel: " + id);
        clients.remove(ServerDriver.getClientManager().getClientMap().get(clientID));
    }
    
    /**
     * Add client by ID.
     * 
     * @param clientID client's ID.
     */
    public void add(byte clientID) {
        System.out.println("Adding user: " + clientID + " to channel: " + id);
        clients.add(ServerDriver.getClientManager().getClientMap().get(clientID));
    }
    
    /**
     * Clears clients from the channel.
     */
    public void clear() {
        clients.clear();
    }
    
    /**
     * Broadcasts an audio packet to all clients in this channel.
     * 
     * @param packet audio packet
     * @param senderID sender's ID
     */
    public void broadcastAudio(Packet packet, byte senderID) {       
        
        Set<Client> toRemove = new HashSet<>();
        
        clients.stream().filter(n -> n.getID() != senderID).forEach(n -> {
            
            // Assert that the client is still connected
            try {
                packet.send(n.getOut());
            } catch (IOException ex) {
                if(ex.getMessage().contains("Connection reset by peer")) {
                    toRemove.add(n);
                }
                
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Removes all the clients that aren't connected
        toRemove.forEach(n -> {
            ServerDriver.getClientManager().removeClient(n.getID());
        });
    }
}
