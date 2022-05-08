/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.channel;

import java.util.*;
import lombok.Getter;

/**
 *
 * @author Derek
 */
public class ChannelManager {
    
    @Getter
    HashMap<String, Channel> channelMap = new HashMap<>();
    
    public ChannelManager() {
        
    }
    
    public void add(Channel c) {
        channelMap.put(c.getName(), c);
    }
    
    public void remove(String name) {
        channelMap.remove(name);
    }
    
    /**
     * Returns a channel by its name.
     * 
     * @param name name of the channel
     * @return channel object
     */
    public Channel get(String name) {
        return channelMap.get(name);
    }
    
    /**
     * @return Collection of channels
     */
    public Collection<Channel> getChannels() {
        return channelMap.values();
    }
    
    /**
     * @return set of channel names
     */
    public Set<String> getChannelNames() {
        return channelMap.keySet();
    }
    
}
