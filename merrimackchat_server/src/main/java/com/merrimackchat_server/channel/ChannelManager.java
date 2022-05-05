package com.merrimackchat_server.channel;

import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_server.exceptions.*;
import com.merrimackchat_server.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class ChannelManager {
    
    @Getter
    private HashMap<Byte, Channel> channels = new HashMap<>();
    private ArrayList<Pair<Boolean, Byte>> ids; // True = available; False = unavailable
    
    public ChannelManager() {
        ids = new ArrayList<>();
        // Add all possible IDs for channels and set "true" for availability
        for(byte b = 0; b < 127; b++) {
            ids.add(new Pair(true, b));
        }
        
        // TEST
        try {
            // Creates a test chanel for this purpose
            createChannel("TestChanel");
        } catch (NoIDAvailableException ex) {
            Logger.getLogger(ChannelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns true if a channel with the ID exists.
     * 
     * @param id ID we are checking existence for
     * @return true if one exists, false otherwise
     */
    public boolean exists(byte id) {
        return channels.containsKey(id);
    }
    
    /**
     * Creates channel by channel name.
     * 
     * @param name channel name
     * @throws NoIDAvailableException if there are no available IDs
     */
    public void createChannel(String name) throws NoIDAvailableException {
        
        
        byte id = getAvailableID();
        if(id != -1)
            channels.put(id, new Channel(name, id));
        else throw new NoIDAvailableException("No available IDs to create the new channel. Reduce the number of channels.");
    }
    
    /**
     * Deletes channel by name.
     * 
     * @param name name of channel
     * @throws ChannelNotFoundException if no such channel exists
     */
    public void deleteChannel(String name) throws ChannelNotFoundException {
        byte id = findIDByName(name);
        if(id != -1) {
            // Remove users
            channels.get(id).clear();
            // Remove channel
            channels.remove(id);
        } else throw new ChannelNotFoundException("Channel " + name + " is not found, so it cannot be deleted.");
    }
    
    /**
     * Adds a user to a channel by ID.
     * 
     * @param channelID channel ID
     * @param userID client being added
     */
    public void userJoinChannel(byte userID, byte channelID) {
        channels.get(channelID).remove(userID);
    }
    /**
     * Removes a user from a channel by ID.
     * 
     * @param channelID channel ID
     * @param userID client being removed
     */
    public void userLeaveChannel(byte userID, byte channelID) {
        channels.get(channelID).add(userID);
    }
    
    /**
     * Broadcasts audio to all clients connected.
     * 
     * @param input audio input
     * @param senderID ID of the user sending the audio.
     * @param channelID ID of the channel the user is sending the audio to.
     * @param len1 Length of the audio stream * {@code len2}
     * @param len2 Length of the audio stream * {@code len1}
     */
    public void broadcastAudio(byte[] input, byte senderID, byte channelID, byte len1, byte len2 ) {
        channels.get(channelID).broadcastAudio(PacketEncoder.createAudioBeingSentPacket(senderID, channelID, len1, len2, input));
    }
    
    
    /*
    Private Methods
    */
    
    /**
     * Gets the next available ID for a channel.
     * 
     * @return available ID or -1 if unavailable
     */
    private byte getAvailableID() {
        for(int i = 0; i < ids.size(); i++) {
            Pair<Boolean, Byte> pair = ids.get(i);
            if(pair.getValue1()) { // If one is found
                pair.setValue1(false); // Set unavailable
                return pair.getValue2();
            }
        }
        
        return -1; // If one is not available
    }
    
    /**
     * Get the channel's ID by its name.
     * 
     * @param name name of channel
     * @return ID of channel or -1 if not found
     */
    private byte findIDByName(String name) {
        ArrayList<Channel> ch = new ArrayList<>(channels.values());
        // Find the channel's id
        for(int n = 0; n < ch.size(); n++) {
            Channel c = ch.get(n);
            if(c.getName().equals(name))
                return c.getId();
        }
        
        return -1; // If the channel cannot be found
    }
}
