/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_packet.data;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class PacketEncoder {
    
    public static Packet createUserJoinPacket(byte clientID, String clientName) {
        
        // Encodes nameDecoded
        byte[] nameDecoded;
        try {
            nameDecoded = clientName.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PacketEncoder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return new Packet(PacketType.USER_JOIN_SERVER, nameDecoded, nameDecoded.length, new byte[]{clientID});
    }    
    
    public static Packet createAudioBeingSentPacket(byte clientID, byte channelID, byte len1, byte len2, byte[] sound) {
        return new Packet(PacketType.AUDIO_BEING_SENT, sound, sound.length, new byte[] {clientID, channelID, len1, len2});
    }
    
    public static Packet createChannelJoinPacket(byte clientID, byte channelID) {
        return new Packet(PacketType.USER_JOIN_CHANNEL, new byte[]{}, 0, new byte[]{clientID, channelID});
    }
    
    public static Packet createChannelLeavePacket(byte clientID, byte channelID) {
        return new Packet(PacketType.USER_LEAVE_CHANNEL, new byte[]{}, 0, new byte[]{clientID, channelID});
    }
    
    public static Packet createChannelCreatePacket(String channelName) {
        return new Packet(PacketType.USER_CREATE_CHANNEL, Base64.getDecoder().decode(channelName));
    }
    
    public static Packet createChannelDeletePacket(String channelName) {
        return new Packet(PacketType.USER_DELETE_CHANNEL, Base64.getDecoder().decode(channelName));
    }
    
    public static Packet createResponseToUserConnectToServerAPakcet(byte ID) {
        return new Packet(PacketType.RESPONSE_USER_CONNECT_SERVER, new byte[]{}, 0, new byte[]{ID});
    }
    
}
