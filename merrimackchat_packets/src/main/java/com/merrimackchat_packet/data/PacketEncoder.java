/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_packet.data;

import java.util.Base64;

/**
 *
 * @author Alex
 */
public class PacketEncoder {
    
    public static Packet createUserJoinPacket(String clientName) {
        return new Packet(PacketType.USER_JOIN_SERVER, Base64.getDecoder().decode(clientName));
    }    
    
    public static Packet createAudioBeingSentPacket(byte userID, byte channelID, byte len1, byte len2, byte[] sound) {
        return new Packet(PacketType.AUDIO_BEING_SENT, sound, sound.length, new byte[] {userID, channelID, len1, len2});
    }
    
    public static Packet createChannelJoinPacket(byte userID, byte channelID) {
        return new Packet(PacketType.USER_JOIN_CHANNEL, new byte[]{}, 0, new byte[]{userID, channelID});
    }
    
    public static Packet createChannelLeavePacket(byte userID, byte channelID) {
        return new Packet(PacketType.USER_LEAVE_CHANNEL, new byte[]{}, 0, new byte[]{userID, channelID});
    }
    
    public static Packet createChannelCreatePacket(String channelName) {
        return new Packet(PacketType.USER_CREATE_CHANNEL, Base64.getDecoder().decode(channelName));
    }
    
    public static Packet createChannelDeletePacket(byte channelID) {
        return new Packet(PacketType.USER_DELETE_CHANNEL, new byte[]{}, 0, new byte[]{channelID});
    }
    
    public static Packet createRequestChannelUsersPacket(byte channelID) {
        return new Packet(PacketType.REQUEST_USERS_IN_CHANNEL, new byte[]{}, 0, new byte[]{channelID});
    }
    
    public static Packet createSendChannelUserPacket(String name) {
        return new Packet(PacketType.SEND_USERS_IN_CHANNEL, Base64.getDecoder().decode(name));
    }
    
    public static Packet createErrorMessagePacket(String message) {
        return new Packet(PacketType.ERROR_MESSAGE, Base64.getDecoder().decode(message));
    }
    
}
