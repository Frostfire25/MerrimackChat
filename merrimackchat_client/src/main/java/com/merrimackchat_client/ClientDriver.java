/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client;

import com.merrimackchat_client.gui.IdAndPasswords;
import com.merrimackchat_client.gui.LoginBrowser;

import com.merrimackchat_client.gui.myGUI;
import javax.swing.JFrame;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Alex
 */


public class ClientDriver {

    private static Thread thread;
    @Getter
    private static Client client;
    
    @Getter
    private static myGUI myGUI;


    
    public static void main(String[] args) {
        
        IdAndPasswords s = new IdAndPasswords(); 
        LoginBrowser lb = new LoginBrowser(s.getInfo());
    
    
        //Assigns the client out
        client = new Client();

        thread = new Thread(client);
        thread.start();


       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*myGUI = new myGUI();
                myGUI.setVisible(true);
                myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                myGUI.setTitle("Chat App");
                myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
                lb.setVisible(true);
                //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                lb.setTitle("Chat App");
                lb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
  
    }
  
}

