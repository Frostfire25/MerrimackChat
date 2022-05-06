/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import com.merrimackchat_client.ClientChat;
import com.merrimackchat_client.ClientDriver;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.merrimackchat_client.ClientDriver;
import com.merrimackchat_client.gui.myGUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.lang.*;
/**
 *
 * @author Mark
 */
public class KeyListener {
        
      
     myGUI get = new myGUI();
    ClientChat cc = new ClientChat();
    
        public void keyReleasedEnter(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println(ClientChat.getPrintWriter());
               // JOptionPane.showMessageDialog(get.getChatPanel(), "Enter has been hit");
                ClientChat.getPrintWriter().println(ClientChat.name + ": " + myGUI.getChatText().getText());
                // NECESSARY:
                ClientChat.getPrintWriter().flush();
                // Clear the text in the text field
                get.getChatText().setText("");
            }  // Person is releasing their press to talk key
//             else if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY) {
//                  ClientDriver.getClient().sendAudio(false);
//             
//             }
        }
        
                
//        public void keyPressed(KeyEvent e) {
//            // Person is pressing their push to talk key
//            if (e.getExtendedKeyCode() == PUSH_TO_TALK_KEY) {
//               ClientDriver.getClient().sendAudio(true);
//            }
//        }
        
        
    }
    
