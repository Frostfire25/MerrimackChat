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
import javax.swing.JOptionPane;
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

    /**
     * Called on the release of a key in the chat key in chatTextField
     * @param e 
     */
    public void chatReleased(KeyEvent e) {
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

        }
    }
    
    /**
     * Called when the push to talk key could be released
     * @param e 
     */
    public void pushToTalkKeyReleased(KeyEvent e) {        
        if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY) {
            talkReleased();
        }
    }
    
    /**
     * Called when the push to talk key could be pressed
     * @param e 
     */
    public void pushToTalkKeyPressed(KeyEvent e) {
        // Person is pressing their push to talk key
        if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY ) {
            talkPressed();
        }
    }
    
    /**
     * A talk was pressed
     */
    public void talkPressed() {
        if(!isPressed) {
            System.out.println("[Talk] Push to talk key pressed");
            ClientDriver.getClient().sendAudio(true);
            isPressed = true;
        }
    }
    
    /**
     * A talk was released
     */
    public void talkReleased() {
        if(isPressed) {
            System.out.println("[Talk] Push to talk key released");
            ClientDriver.getClient().sendAudio(false);
            isPressed = false;
        }
    }

    public void connectPressed(ActionEvent evt) {

        // Asserts that their isn't a connection already created
        if (ClientDriver.getClient() != null) {
            if (!ClientDriver.getClient().isConnected()) {
                return;
            }
        }

        // Gets the text fields
        JTextField ipField = ClientDriver.getMyGUI().getIPText();
        String text = ipField.getText();

        // Determines if the regex is correct
        if (!text.isEmpty() && text.matches(".*:.*")) {

            String splitted[] = text.split(":");
            String address = splitted[0];
            int port = 0;

            try {
                port = Integer.parseInt(splitted[1]);
            } catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(null, "The PORT should only contain integers.");
                return;
            }

            // Establishes the connection
            ClientDriver.establishConnection(address, port);
        } // Displays the error diolog box
        else {
            JOptionPane.showInternalMessageDialog(null, "Incorrect format for the text field, please put ADRESS:PORT to connect.");
        }

    }
}
