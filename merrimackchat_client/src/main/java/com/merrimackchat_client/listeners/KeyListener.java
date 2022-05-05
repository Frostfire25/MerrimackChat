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
        
      
     myGUI get = new myGUI();
    ClientChat cc = new ClientChat();
    
        public void keyReleasedEnter(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println(ClientChat.getPrintWriter());
                JOptionPane.showMessageDialog(get.getChatPanel(), "Enter has been hit");
                ClientChat.getPrintWriter().println(ClientChat.name + ": " + myGUI.getChatText().getText());
                // NECESSARY:
                ClientChat.getPrintWriter().flush();
                // Clear the text in the text field
                myGUI.getChatText().setText("");
            }
        }
        
    }
    
