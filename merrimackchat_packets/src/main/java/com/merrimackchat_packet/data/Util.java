package com.merrimackchat_packet.data;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Util {
    
    public static String getStringFromByteArray(byte[] buff) {
        
        String output = "";
        try {
            output = new String(buff, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
    
}
