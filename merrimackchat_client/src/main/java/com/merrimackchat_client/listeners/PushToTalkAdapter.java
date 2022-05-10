/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import com.merrimackchat_client.ClientDriver;
import java.awt.event.KeyEvent;

/**
 *
 * @author Alex
 */
public class PushToTalkAdapter extends java.awt.event.KeyAdapter {
    
    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {
        
    }

    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {
    
    }
    /**
     * Called when the push to talk key could be pressed
     * @param e 
     */
    public void pushToTalkPressed(KeyEvent e) {
        /*
        // Person pressed enter on their text, to be sent
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() instanceof JTextField) {
            JTextField area = (JTextField) e.getSource();
            String line = area.getText();

            // If the length is too long, don't send it
            if (line.length() > TEXT_SEND_LENGTH) {
                System.out.println("You can not send text with a length greater than " + TEXT_SEND_LENGTH);
                return;
            }
            
            // Connection not established
            if(ClientDriver.getClient() == null) return;
            
            // Sends the text packet to the server
            byte channel = ClientDriver.getClient().getChannel();
            byte userID = ClientDriver.getClient().getID();
            // If the client is in a channel, send the packet
            if (channel != (byte) -1) {
                ClientDriver.getClient().sendPacket(PacketEncoder.createUserSendText(userID, channel, line));
            }

            // Remove the text in the text box
            area.setText("");

        } // Person is releasing their press to talk key
        else */
        
    }
    
    /**
     * Called when the push to talk key could be pressed
     * @param e 
     */
    public void pushToTalkReleased(KeyEvent e) {
        
    }
    
}
