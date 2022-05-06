package com.merrimackchat_packet.data;

import java.util.Arrays;

/**
 * Determines the type of packet being used
 * @author Alex
 */
public enum PacketType {

    /*
    ** This Packet is basically the "Set Name" packet**
    Used for when a client joins a server
       - Has 1 argument 
         - User Sending ID
       - Expects all the channels as packets to be returned
    */
    USER_JOIN_SERVER((byte) 0, 1, true),
    
    /*
    Used for when a user leaves a server
    Has one argument which is Client.ID
    */
    USER_LEFT_SERVER((byte) 1, 1, false),
    
    /**
     * Used for when a user joins a channel
     * Has 2 arguments
     *  - User Sending ID
     *  - Destination Channel ID
     */
    USER_JOIN_CHANNEL((byte) 2, 2, false),
    
    /**
     * Used for when a user leaves a channel
     * Has 2 arguments
     *  - User Sending ID
     *  - Destination Channel ID
     */
    USER_LEAVE_CHANNEL((byte) 3, 2, false),
    
    /**
     Used for audio being sent from a client to a server
     * Has three arguments
     * - User Sending ID
     * - Destination Channel ID
     * - Length of Audio Buffer
     */
    AUDIO_BEING_SENT((byte) 4, 4, true),
    
    /**
     * Used for when a user creates a channel
     * Has 2 arguments
     *  - User Sending ID
     *  - Created Channel ID
     */
    USER_CREATE_CHANNEL((byte) 5, 2, true),
    
    /**
     * Used for when a user deletes a channel
     * Has 2 arguments
     *  - User Sending ID
     *  - Deleted Channel ID
     */
    USER_DELETE_CHANNEL((byte) 6, 2, true),
    
    /**
     * OUT from Server -> Client
     * Response from the server to a client when a client joins the server 
     * Has 1 argument 
     *  - User ID to be displayed
     * 
     * Expects a Response
     *  - User sends their display name back
     */
    RESPONSE_USER_CONNECT_SERVER((byte) 12, 1, true);
    
    
    
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
