/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gui.myGUI;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Mark
 */
public class KeyListener {
        
        myGUI get = new myGUI();
    
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //test
                JOptionPane.showMessageDialog(get.getChatPanel(), "Enter has been hit");

            }
        }
    }
    
