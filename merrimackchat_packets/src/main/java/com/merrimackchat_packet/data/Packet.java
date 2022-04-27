package com.merrimackchat_packet.data;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * @author Alex
 */
public class Packet implements Sendable {
    
    private PacketType packetType;
    private byte[] buff;
    private int len;
    
    public Packet(PacketType packetType, byte[] buff) {
        // Create a packet with the buff length, no data and an empty buffer 
        this(packetType, buff, buff.length, new byte[] {});
    }
    
    public Packet(PacketType packetType, byte[] buff, int len) {
        // Create a packet with no data and an empty buffer
        this(packetType, buff, len, new byte[] {});
    }
    
    public Packet(PacketType packetType, byte[] buff, int len, byte... args) {
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
        
        // Updates the length of the byte[]
        this.len = buff.length;
    }
    
    /**
     * Gets a copy of {@code buff} with the arguments removed
     * 
     * @return A byte array with no buffs. 
     */
    public byte[] getBuffWithoutArgs() {
        // Returns everything with the ID removed
        if(packetType.getNumOfArgs() <= 0) return Arrays.copyOfRange(buff, 1, buff.length);
        // Returns everything with the ID and arguments removed
        return Arrays.copyOfRange(buff, packetType.getNumOfArgs() + 1, buff.length);
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
    
    /**
     * Gets the datavalue at a certian argument position
     * Returns a position in the byte array
     * 
     * @param i  0 < n <= packetType.getNumOfArgs()
     * @return The argument in a certian position
     */
    public byte getArgs(int i) {
        if(i > packetType.getNumOfArgs()) return -1;
        
        return buff[i];
    }
    
    
    /**
     * Used for sending packets
     * @param out the stream for this packet to be written to.
     */
    @Override
    public void send(OutputStream out) {
        try {
            // Sends the packet
            out.write(buff);
        } catch(IOException e) {
            e.printStackTrace();
        }
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
            if (i == location)
                newarr[i] = value;
            else if (i > location)
                newarr[i] = buff[i - 1];
            else if(i < location)
               newarr[i] = buff[i];
        }
        
        return newarr;
    }
    
    // 0 1 2 3
    // 0 4 1 2 3
    // 0 4
    
}
