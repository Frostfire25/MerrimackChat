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
    private byte[] args;

    public static final int BUFFER_LENGTH = 4600; // Was 2400

    public Packet(PacketType packetType, byte[] buff) {
        // Create a packet with the buff length, no data and an empty buffer 
        this(packetType, buff, buff.length, new byte[]{});
    }

    public Packet(PacketType packetType, byte[] buff, int len) {
        // Create a packet with no data and an empty buffer
        this(packetType, buff, len, new byte[]{});
    }

    public Packet(PacketType packetType, byte[] buff, int len, byte... args) {
        if (args.length != packetType.getNumOfArgs()) {
            System.out.println("Issue sending packet " + packetType.name());
            return;
        }

        this.buff = buff;
        this.len = len;
        this.args = args;
        this.packetType = packetType;

        idPacket();
    }

    /**
     * Adds the necessary identification to a packet before sending it out.
     */
    private void idPacket() {
        // Sets the ID number
        buff = insertIntoSpace(buff, 0, packetType.getID());
        //System.out.println(Arrays.toString(buff));

        if (args.length > 0) {

            /**
             * Inserts all arguments into the front of the array if there are
             * any.
             */
            int pos = 1;
            for (Byte n : args) {
                buff = insertIntoSpace(buff, pos++, n);
            }

        }

        // Updates the length of the byte[]
        this.len = buff.length;
    }

    /**
     * Gets a copy of {@code buff} with the arguments removed
     *
     * @return A byte array with no buffs.
     */
    public byte[] getBuffWithoutArgsAndTrailingFillers() {
        // Returns everything with the ID removed
        if (packetType.getNumOfArgs() <= 0) {
            return removeTrailingFillers(Arrays.copyOfRange(buff, 2, buff.length));
        }
        // Returns everything with the ID and arguments removed
        return removeTrailingFillers(Arrays.copyOfRange(buff, (packetType.getNumOfArgs() * 2) + 2, buff.length));
    }
    
    public byte[] removeTrailingFillers(byte[] array) {
        int upTo = 0;
        for(int i = array.length -1; i > 0; i--) {
            if(array[i] != Byte.MIN_VALUE) {
                upTo = i;
                break;
            }
        }
        
        return Arrays.copyOfRange(array, 0, upTo+1);
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
     * Gets the datavalue at a certian argument position Returns a position in
     * the byte array
     *
     * @param i 0 < n <= packetType.getNumOfArgs() @return The argument in a
     * certian position
     */
    public byte getArgs(int i) {
        if (i > packetType.getNumOfArgs()) {
            return -1;
        }

        return buff[i];
    }

    /**
     * Used for sending packets
     *
     * @param out the stream for this packet to be written to.
     */
    @Override
    public void send(OutputStream out) throws IOException {

        // Sends the packet
        // Fixes the length to be to sending protocol
        //idPacket();
        //System.out.println(Arrays.toString(buff));
                
        this.buff = fixLength();
        out.write(buff);

    }

    /*
    Private methods
     */
    /**
     * Fixes the length of this buffer so it can be sent over a stream for
     * packets Uses BUFFER_LENGTH as the setting length
     *
     * @return
     */
    private byte[] fixLength() {
        if (buff.length > BUFFER_LENGTH) {
            //System.out.println("here 1");
            return Arrays.copyOfRange(buff, 0, BUFFER_LENGTH);
        }
        if (buff.length == 1400) {
            //System.out.println("here 2");
            return buff;
        }

        //System.out.println("here 3");
        // Fills the return array
        byte[] ret = new byte[BUFFER_LENGTH];

        // Fills the first part of the ret with the values in buff
        for (int i = 0; i < buff.length; i++) {
            ret[i] = buff[i];
        }
        // Fills the rest of ret with Byte.MIN_VALUE
        Arrays.fill(ret, buff.length, ret.length, Byte.MIN_VALUE);

        return ret;
    }

    /**
     * Inserts a byte into a specific location in a byte[]
     *
     * @param buff Byte[] to be edited
     * @param value byte value to be inserted
     * @param location location to be insterted in
     *
     * Method from
     * (https://stackoverflow.com/questions/11638123/how-to-add-an-element-to-array-and-shift-indexes/)
     */
    /*
    public byte[] insertIntoSpace(byte[] buff, int index, byte location) {
        byte[] result = new byte[buff.length];
        System.arraycopy(buff, 0, result, 0, index);
        System.arraycopy(buff, index, result, index + 1, buff.length - index - 1);
        result[index] = location;
        return result;
    }
    */
    
    public static byte[] insertIntoSpace(byte buff[], int location, byte value)
    {
        int i;
 
        int loc = location + 1;
        
        // create a new array of size n+1
        byte newarr[] = new byte[buff.length + 1];
 
        // insert the elements from
        // the old array into the new array
        // insert all elements till pos
        // then insert x at pos
        // then insert rest of the elements
        for (i = 0; i < newarr.length; i++) {
            if (i < loc - 1)
                newarr[i] = buff[i];
            else if (i == loc - 1)
                newarr[i] = value;
            else
                newarr[i] = buff[i - 1];
        }
        return newarr;
    }

    // 0 1 2 3
    // 0 4 1 2 3
    // 0 4
}
