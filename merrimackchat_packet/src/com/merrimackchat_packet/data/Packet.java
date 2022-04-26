package com.merrimackchat_packet.data;

import com.sun.jdi.ByteType;

/**
 *
 * @author Alex
 */
public class Packet {
    
    private PacketType packetType;
    private byte[] buff;
    private int len;
    
    public Packet(PacketType packetType, byte[] buff, int len) {
        this(packetType, buff, len, new Byte[] {});
    }
    
    public Packet(PacketType packetType, byte[] buff, int len, Byte... args) {
        if(args.length != packetType.getNumOfArgs()) {
            System.out.println("Issue sending packet " + packetType.name());
            return;
        }
        
        this.buff = buff;
        this.len = len;
        
        // Sets the ID number
        buff = insertIntoSpace(buff, 0, packetType.getID());
                
        if(args.length <= 0) return;
        
        /**
         * Inserts all arguments into the front of the array if there are any.
         */
        int pos = 1;
        for(Byte n : args) {
            buff = insertIntoSpace(buff, pos++, n);
        }
    }
    
    /**
     * @return the packetType
     */
    public PacketType getPacketType() {
        return packetType;
    }

    /**
     * @return the buff
     */
    public byte[] getBuff() {
        return buff;
    }
    
    /*
    Private methods
    */
    
    /**
     * Inserts a byte into a specific location in a byte[]
     * 
     * @param buff Byte[] to be edited
     * @param value byte value to be inserted
     * @param location location to be insterted in
     * 
     * Method from (https://www.geeksforgeeks.org/how-to-insert-an-element-at-a-specific-position-in-an-array-in-java/)
     */
    private byte[] insertIntoSpace(byte[] buff, int location, byte value) {
        int i;
 
        // create a new array of size n+1
        byte newarr[] = new byte[buff.length + 1];
 
        // insert the elements from
        // the old array into the new array
        // insert all elements till pos
        // then insert x at pos
        // then insert rest of the elements
        for (i = 0; i < buff.length + 1; i++) {
            if (i < location - 1)
                newarr[i] = buff[i];
            else if (i == location - 1)
                newarr[i] = value;
            else
                newarr[i] = buff[i - 1];
        }
        
        return newarr;
    }

    
}
