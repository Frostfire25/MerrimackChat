/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import com.merrimackchat_client.ClientChat;
import com.merrimackchat_client.ClientDriver;
import com.merrimackchat_client.gui.myGUI;
import com.merrimackchat_packet.data.PacketEncoder;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.*;
import javax.swing.JTextField;

/**
 *
 * @author Mark
 */
public class KeyListener {

    // Default Push to talk key is F1. 
    private static int PUSH_TO_TALK_KEY = KeyEvent.VK_F1;

    private boolean isPressed = false;
    
    private final int TEXT_SEND_LENGTH = 400;

    //myGUI get = new myGUI();
    //ClientChat cc = new ClientChat();
    public void keyReleased(KeyEvent e) {
        // Person pressed enter on their text, to be sent
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() instanceof JTextField) {
            System.out.println("enter was pressed");

            JTextField area = (JTextField) e.getSource();
            String line = area.getText();
            
            // If the length is too long, don't send it
            if(line.length() > TEXT_SEND_LENGTH) {
                System.out.println("You can not send text with a length greater than " + TEXT_SEND_LENGTH);
                return;
            }
            
            // Sends the text packet to the server
            byte channel = ClientDriver.getClient().getChannel();
            byte userID = ClientDriver.getClient().getID();
            // If the client is in a channel, send the packet
            if(channel != (byte)-1) {
                ClientDriver.getClient().sendPacket(PacketEncoder.createUserSendText(userID, channel, line));
            }
            
            // Remove the text in the text box
            area.setText("");
            
        } // Person is releasing their press to talk key
        else if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY && isPressed) {
            System.out.println("Push to talk key released");
            ClientDriver.getClient().sendAudio(false);
            isPressed = false;
        }  
    }
    
    public void connectPressed(ActionEvent evt) {
        System.out.println("Enter for connect btn pressed");
            
        
    }

    public void keyPressed(KeyEvent e) {
        // Person is pressing their push to talk key
        if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY && !isPressed) {
            System.out.println("Push to talk key Pressed");
            ClientDriver.getClient().sendAudio(true);
            isPressed = true;

        }
    }
}


