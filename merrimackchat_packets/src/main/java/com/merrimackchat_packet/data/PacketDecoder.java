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
    
    
    /**
     * Creates an audio line from a audio packet
     * 
     * @param packet Packet
     * @return a byte[] that can be read by the Speaker.class on a client
     */
    public static byte[] getAudioStreamFromAnAudioPacket(Packet packet) {
        if(packet == null || packet.getPacketType() == null || (packet.getPacketType() != PacketType.AUDIO_BEING_SENT && packet.getPacketType() != PacketType.SERVER_SENDING_AUDIO)) return null;
        
        return packet.getBuffWithoutArgsAndTrailingFillers() /*Arrays.copyOfRange(packet.getBuff(), 15, ((int) packet.getArgs(3) * (int) packet.getArgs(4)) + 1)*/;
        
    }
    
    // First paramater is the user name, second is the text
    private static final String textFormat = "[%s] : %s";
    
    /**
     * Creates a formatted string with the user name to text
     * Should be called from the server instance, not the client
     * 
     * @param packet Packet
     * @param userName Name of the sending client
     * @return String formatted with {@code textFormat}
     */
    public static String getFormattedTextFromATextPacket(Packet packet, String userName) {
        return String.format(textFormat, userName, Util.getStringFromByteArray(packet.getBuffWithoutArgsAndTrailingFillers()));
    }
}
