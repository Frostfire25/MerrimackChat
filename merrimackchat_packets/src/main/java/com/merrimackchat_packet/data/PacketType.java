package com.merrimackchat_packet.data;

import java.util.Arrays;

/**
 * Determines the type of packet being used
 * @author Alex
 */
public enum PacketType {

    /*
    Used for when a client joins a server
    No arguments, the name proceeds the id
    Expects the client ID to be returned. If Byte.MAX_VALUE is returned the client couldn't connect.
    */
    USER_JOIN_SERVER((byte) 0, 0, true),
    
    /*
    Used for when a user leaves a server
    Has one argument which is Client.ID
    */
    USER_LEFT_SERVER((byte) 1, 1, false),
    
    /**
     Used for audio being sent from a client to a server
     * Has three arguments
     * - User Sending ID
     * - Destination Chanel ID
     * - Length of Audio Buffer
     */
    AUDIO_BEING_SENT((byte) 4, 3, true)
    
    ;

    private byte ID;
    private int numOfArgs;
    private boolean hasResponse;
    
    private PacketType(byte ID, int numOfArgs, boolean hasResponse) {
        this.ID = ID;
        this.numOfArgs = numOfArgs;
        this.hasResponse = hasResponse;
    }

    /**
     * @return the ID
     */
    public byte getID() {
        return ID;
    }

    /**
     * @return the numOfArgs
     */
    public int getNumOfArgs() {
        return numOfArgs;
    }

    /**
     * @return the hasResponse
     */
    public boolean isHasResponse() {
        return hasResponse;
    }
    
    /**
     * Gets a packet by the ID type
     * 
     * @param id ID type
     * @return PacketType of {@code id}
     */
    public static PacketType getByID(byte id) {
        return Arrays.stream(values()).filter(n -> n.getID() == id).findFirst().orElse(null);
    }
    
}
