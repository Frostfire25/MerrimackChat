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
        
        // Decodes clientName and encodes array
        byte[] array = Util.getByteArrayFromString(clientName);
        if(array == null) return null;
        
        return new Packet(PacketType.USER_JOIN_SERVER, array, array.length, new byte[]{clientID});
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
        // Decodes channelName and encodes array
        byte[] array = Util.getByteArrayFromString(channelName);
        if(array == null) return null;
        
        return new Packet(PacketType.USER_CREATE_CHANNEL, array);
    }
    
    public static Packet createChannelDeletePacket(byte channelID) {
        return new Packet(PacketType.USER_DELETE_CHANNEL, new byte[]{}, 0, new byte[]{channelID});
    }
    
    public static Packet createRequestChannelUsersPacket(byte channelID) {
        return new Packet(PacketType.REQUEST_USERS_IN_CHANNEL, new byte[]{}, 0, new byte[]{channelID});
    }
    
    public static Packet createSendChannelUserPacket(String name) {
        byte[] array = Util.getByteArrayFromString(name);
        if(array == null) return null;
        
        return new Packet(PacketType.SEND_USERS_IN_CHANNEL, array);
    }
    
    public static Packet createErrorMessagePacket(String message) {
        byte[] array = Util.getByteArrayFromString(message);
        if(array == null) return null;
        
        return new Packet(PacketType.ERROR_MESSAGE, array);
    }
    
    public static Packet createChannelInfoPacket(String name, byte id, byte operation) {
        byte[] array = Util.getByteArrayFromString(name);
        if(array == null) return null;
        
        return new Packet(PacketType.CHANNEL_INFO, array, 0, new byte[]{id, operation});
    }
        
    public static Packet createResponseToUserConnectToServerAPakcet(byte ID) {
        return new Packet(PacketType.RESPONSE_USER_CONNECT_SERVER, new byte[]{}, 0, new byte[]{ID});
    }
    
}
