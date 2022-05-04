package com.merrimackchat_server.channel;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_server.Server;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.manager.Client;
import java.io.IOException;
import java.util.ArrayList;
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
    
    /**
     * Broadcasts an audio packet to all clients in this channel.
     * 
     * @param packet audio packet
     */
    public void broadcast(Packet packet) {
        clients.stream()/*.filter(n -> n.getID() != senderID && n.getChannel() == channelID)*/.forEach(n -> {
            try {
                packet.send(n.getOut());
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
