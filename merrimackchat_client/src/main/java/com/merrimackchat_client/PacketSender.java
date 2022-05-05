/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client;

import com.merrimackchat_packet.data.Packet;
import java.util.PriorityQueue;

/**
 *
 * @author Alex
 */
public abstract class PacketSender {
    
    // ToDo may be implemented for priority of packets being sent, EX necisary packets over audio.
    private PriorityQueue packetQueue = new PriorityQueue();
    
    public PacketSender() {
        
    }
    
    /**
     * Sends a packet out to a the server
     * @param packer
     * @return 
     */
    public abstract boolean sendPacket(Packet packet);

    
    
}
