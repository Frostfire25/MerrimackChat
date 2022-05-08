/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client;

import com.merrimackchat_client.channel.ChannelManager;
import com.merrimackchat_client.gui.IdAndPasswords;
import com.merrimackchat_client.gui.LoginBrowser;

import com.merrimackchat_client.gui.myGUI;
import javax.swing.JFrame;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Alex
 */


public class ClientDriver {

    @Generated
    private static Thread thread;
    
    @Getter
    private static Client client;
    
    @Getter
    private static myGUI myGUI;
    
    @Getter
    private static ChannelManager channelManager;
    
    @Getter
    private static IdAndPasswords idAndPasswords;
    
    @Getter
    private static LoginBrowser loginBrowser;
    
    public static void main(String[] args) {

        /*Commenting out by alex for simplicity *
        IdAndPasswords s = new IdAndPasswords();        
        LoginPage L = new LoginPage(s.getInfo());*/

        /* Set the Nimbus look and feel 
        * @author Mark
         
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Login
        idAndPasswords = new IdAndPasswords(); 
        loginBrowser = new LoginBrowser(idAndPasswords.getInfo());    
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //myGUI = new myGUI("Derek");
                //myGUI.setVisible(true);
                //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                //myGUI.setTitle("Chat App");
                //myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//*/
                loginBrowser.setVisible(true);
                //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                loginBrowser.setTitle("Chat App");
                loginBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });      
    }
    
    /**
     * Establishes a connection with the server
     */
    public static void establishConnection(String adress, int port) {
        
        System.out.println("Connection being established.");
        
        channelManager = new ChannelManager();
        
        //Assigns the client out
        client = new Client(adress, port);

        thread = new Thread(client);
        thread.start();       
    }
  
}

