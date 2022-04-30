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
    
    public static Packet createAudioBeingSentPacket(byte userID, byte channelID, byte[] sound) {
        return new Packet(PacketType.AUDIO_BEING_SENT, sound, sound.length, new byte[] {userID, channelID, (byte) sound.length});
    }
    
}
