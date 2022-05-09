package com.merrimackchat_server.client;

import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.channel.ChannelManager;
import com.merrimackchat_server.exceptions.ChannelNotFoundException;
import com.merrimackchat_server.util.Pair;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * @param connection connection of this client
     * @return If the client was created correctly
     */
    public Pair<Boolean, Client> clientJoined(String name, String address, int port, Socket connection) {
        byte id = getID();
        
        // If the id is greater than the max allowed value, we must return false
        if(id == Byte.MAX_VALUE) return new Pair(false, null);
        
        // Creates and assigns a client; -1 implies no channle is joined
        Client client = new Client(name, address, port, id, ((byte) -1), connection);
        clientMap.put(id, client);
        
        // Send back the ID to the client Here
        // ToDo..
        
        // User was successfully assigned
        return new Pair(true, client);
    }
    
    /**
     * Joins the client to the specified channel.
     * 
     * @param userID ID of the client
     * @param channelID ID of the channel being joined
     * @return true if the channel exists and the client was added, false otherwise
     * @throws com.merrimackchat_server.exceptions.ChannelNotFoundException
     */
    public boolean joinChannel(byte userID, byte channelID) throws ChannelNotFoundException {
        ChannelManager cm = ServerDriver.getChannelManager();
        if(cm.exists(channelID)) {
            // Remove from old channel (if possible)
            leaveChannel(userID);
            // Add to new channel
            cm.userJoinChannel(userID, channelID);
            // Set the client's channel ID to the new one
            Client client = clientMap.get(userID);
            client.setChannel(channelID);
            try {
                PacketEncoder.createUpdateUserChannelInfoPacket(channelID).send(client.getOut());
            } catch (IOException ex) {
                System.out.println("Could not retrieve Output stream for client " + client.getID());
            }
            return true;
        }
        return false;
    }
    
    /**
     * Removes the client from the specified channel.
     * 
     * @param userID ID of the client
     * @return true if the channel exists and the client was removed, false otherwise
     * @throws com.merrimackchat_server.exceptions.ChannelNotFoundException
     */
    public boolean leaveChannel(byte userID){
        ChannelManager cm = ServerDriver.getChannelManager();
        byte currChannelID = clientMap.get(userID).getChannel();
        if(cm.exists(currChannelID)) {
            try {
                // Remove from current channel
                cm.userLeaveChannel(userID, currChannelID);
                Client client = clientMap.get(userID);
                client.setChannel((byte) -1);
                try {
                    PacketEncoder.createUpdateUserChannelInfoPacket((byte) -1).send(client.getOut());
                } catch (IOException ex) {
                    
                }
            } catch (ChannelNotFoundException ex) {
                System.out.println("Channel not found to leave (ClientManager.java).");
            }
            return true;
        }
        return false;
    }
    
    /**
     * Gets a user id
     * Goes from the Byte.MIN_VALUE up to the Byte.MAX_VALUE
     */
    private byte getID() {
        if(clientMap.isEmpty()) return Byte.MIN_VALUE + 1;
        
        // Gets the next byte value to be used
        for(byte b = Byte.MIN_VALUE + 1; b <= Byte.MAX_VALUE; b++) {
            if(!clientMap.containsKey(b)) return b;
        }        
        
        return Byte.MAX_VALUE;
    }

    public void removeClient(byte ID) {
        if(clientMap.containsKey(ID)) {
            Client client = clientMap.remove(ID);
            
            // Leaves channel
            ServerDriver.getChannelManager().userUnpreviewChannel(client.getID());
            leaveChannel(client.getID());
            
            
            // Closes the clients connection.
            client.closeSocket();
            
            // Stop thread (UNSAFE MAY HAVE TO REMOVE)
            client.stop();
        }
    }
    
}
