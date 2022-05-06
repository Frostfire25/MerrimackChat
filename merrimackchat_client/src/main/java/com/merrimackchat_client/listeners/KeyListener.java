/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import com.merrimackchat_client.ClientChat;
import com.merrimackchat_client.ClientDriver;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.merrimackchat_client.gui.myGUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Mark
 */
public class KeyListener {
        
     // Default Push to talk key is F1. 
     private static int PUSH_TO_TALK_KEY = KeyEvent.VK_F1;
    
     private boolean isPressed = false;
     
        //myGUI get = new myGUI();
        //ClientChat cc = new ClientChat();
    
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println(ClientChat.getPrintWriter());
                JOptionPane.showMessageDialog(ClientDriver.getMyGUI().getChatPanel(), "Enter has been hit");
                ClientChat.getPrintWriter().println(ClientChat.name + ": " + myGUI.getChatText().getText());
                // NECESSARY:
                ClientChat.getPrintWriter().flush();
                // Clear the text in the text field
                myGUI.getChatText().setText("");
            } 
            // Person is releasing their press to talk key
            else if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY && isPressed) {
                System.out.println("Push to talk key released");
                ClientDriver.getClient().sendAudio(false);
                isPressed = false;
            }
            
            
            
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
    
