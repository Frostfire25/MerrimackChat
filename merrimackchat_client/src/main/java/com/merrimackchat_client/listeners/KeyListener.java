/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import com.merrimackchat_client.ClientChat;
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
    
        myGUI get = new myGUI();
        ClientChat cc = new ClientChat();
    
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //test
                JOptionPane.showMessageDialog(get.getChatPanel(), "Enter has been hit");
                ClientChat.getOut().println(ClientChat.name + ": " + myGUI.getChatText().getText());
//                // NECESSARY:
                ClientChat.getOut().flush();
//                // Clear the text in the text field
                myGUI.getChatText().setText("");
            } 
            // Person is pressing their press to talk key
            else if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY) {
                
            }
            
            
            
        }
    }
    
