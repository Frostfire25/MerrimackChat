/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_packet.data;

import java.util.Arrays;

/**
 *
 * @author Alex
 */
public class PacketDecoder {
    
    /**
     * Creates a packet from the byte[] buff
     * 
     * @param buff Array that is taken in.
     * @return Packet that is created
     */
    public static Packet decodeByteArray(byte[] buff) {
        // ID of the packet
        byte ID = buff[0];
        
        System.out.println("ID: " + ID);
        
        // Gets the packet type and asserts that it exists
        PacketType packetType = PacketType.getByID(ID);
        if(packetType == null) { System.out.println("Error! Null Packet Type ID:(" + ID + ")"); return null; }
        
        byte[] args = new byte[0];
        // Creates the args array
        if(packetType.getNumOfArgs() > 0) {
           args = new byte[packetType.getNumOfArgs()];
           
           int pos = 0;
           for(int i = 1; i < packetType.getNumOfArgs() + 1; i++) {
               args[pos++] = buff[i];
           }
        }
        
        // Returns the new packet
        return new Packet(packetType, buff, buff.length, args);
    }
    
    
    public static byte[] getAudioStreamFromAnAudioPacket(Packet packet) {
        if(packet == null || packet.getPacketType() == null || packet.getPacketType() != PacketType.AUDIO_BEING_SENT) return null;
        
        System.out.println("Packet Argument 3:" + packet.getArgs(3));
        
        int toValue1 = (int) packet.getArgs(3);
        int toValue2 = (int) packet.getArgs(4);

        System.out.println("Packet Updated : " + Arrays.toString(Arrays.copyOfRange(packet.getBuff(), 0, 6)));
        
        return Arrays.copyOfRange(packet.getBuff(), 4, toValue1 * toValue2);
        
    }
}
