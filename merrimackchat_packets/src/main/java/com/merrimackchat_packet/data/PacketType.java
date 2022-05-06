package com.merrimackchat_packet.data;

import java.util.Arrays;

/**
 * Determines the type of packet being used
 * @author Alex
 */
public enum PacketType {
// OPERATIONS:
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
    
    
// CHANNELS:    
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
     * Used for when a user creates a channel
     * Has 1 argument
     *  - Created Channel ID
     */
    USER_CREATE_CHANNEL((byte) 4, 1, false),
    
    /**
     * Used for when a user deletes a channel
     * Has 1 argument
     *  - Deleted Channel ID
     */
    USER_DELETE_CHANNEL((byte) 5, 1, false),
    
    /**
     * Used for when a user requests to view the list of users in a selected channel
     * Has 1 argument
     *  - Channel ID
     */
    REQUEST_USERS_IN_CHANNEL((byte) 6, 1, false),
    
    /**
     * Used to send user information to client for when channel info is requested
     * Has 1 argument
     *  - Channel's user name (singular -- sent one at a time)
     */
    SEND_USERS_IN_CHANNEL((byte) 7, 1, false),
    
    
// DATA TYPES:    
    /**
     Used for audio being sent from a client to a server
     * Has three arguments
     * - User Sending ID
     * - Destination Channel ID
     * - Length of Audio Buffer
     */
    AUDIO_BEING_SENT((byte) 8, 4, true),
    
    /**
     * Used for sending Errors to clients
     * Has 1 argument
     *  - Error message
     */
    ERROR_MESSAGE((byte) 10, 1, false)
    
    
    
    
    
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
