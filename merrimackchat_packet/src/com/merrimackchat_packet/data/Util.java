package com.merrimackchat_packet.data;

import java.util.Base64;

/**
 *
 * @author Alex
 */
public class Util {
    
    public static String getStringFromByteArray(byte[] buff) {
        return Base64.getEncoder().encodeToString(buff);
    }
    
}
