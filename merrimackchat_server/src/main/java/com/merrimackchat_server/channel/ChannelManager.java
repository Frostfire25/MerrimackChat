package com.merrimackchat_server.channel;

import java.util.HashMap;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class ChannelManager {
    
    @Getter
    private HashMap<Byte, Channel> channels = new HashMap<>();
    
    public ChannelManager() {
        
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
     * Creates channel by ID and channel name.
     * 
     * @param name channel name
     * @param id ID
     */
    public void createChannel(String name, byte id) {
        channels.put(id, new Channel(name, id));
    }
    
    /**
     * Deletes channel by ID.
     * 
     * @param id ID
     */
    public void deleteChannel(byte id) {
        // Remove users fist;
        channels.get(id).clear();
        // Remove channel
        channels.remove(id);
    }
    
    /**
     * Removes a user from a channel by ID.
     * 
     * @param channelID channel ID
     * @param userID client being removed
     */
    public void userJoinChannel(byte userID, byte channelID) {
        channels.get(channelID).remove(userID);
    }
    
    /**
     * Adds a user to a channel by ID.
     * 
     * @param channelID channel ID
     * @param userID client being added
     */
    public void userLeaveChannel(byte userID, byte channelID) {
        channels.get(channelID).add(userID);
    }
    
}
