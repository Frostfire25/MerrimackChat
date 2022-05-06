/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author Mark Case
 */
public class LoginPage implements ActionListener{
    
    JFrame frame = new JFrame();
    JButton loginBtn = new JButton("Login");
    JButton resetBtn = new JButton("Reset");
    JTextField userID = new JTextField();
    JPasswordField userPass = new JPasswordField();
    JLabel userIdLabel = new JLabel("Username:");
    JLabel userPassLabel = new JLabel("Password:");
    JLabel messageLbl = new JLabel();
    HashMap<String,String> loginInfo = new HashMap<String,String>();
    
    /**
     * Creates new form LoginPage
     */
    public LoginPage(HashMap<String,String> loginOG) {
        loginInfo = loginOG;
        
        userIdLabel.setBounds(50,100,75,25);
        userPassLabel.setBounds(50,150,75,25);

        messageLbl.setBounds(125,100,250,35);
        messageLbl.setFont(new Font(null, Font.ITALIC,25));
        
        userID.setBounds(125,100,200,25);
        userPass.setBounds(125,150,200,25);
        
        loginBtn.setBounds(125,200,100,25);
        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);
        
        resetBtn.setBounds(225,200,100,25);
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);
        
        frame.add(loginBtn);
        frame.add(resetBtn);
        frame.add(userIdLabel);
        frame.add(userPassLabel);
        frame.add(userID);
        frame.add(userPass);
        frame.add(messageLbl);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==resetBtn) {
           userID.setText("");
           userPass.setText("");
       }
       
       if(e.getSource()==loginBtn) {
           String userIDNew = userID.getText();
           String passwordNew = String.valueOf(userPass.getPassword());
           
           if(loginInfo.containsKey(userIDNew)) {
               if(loginInfo.get(userIDNew).equals(passwordNew)) {
                   JOptionPane.showMessageDialog(frame, "Login successful");
                   frame.dispose();
//                   myGUI myGUI = new myGUI(userIDNew);
//                           /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                myGUI myGUI = new myGUI(userIDNew);
                myGUI.setVisible(true);
                //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                myGUI.setTitle("Chat App");
                myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
               } else {
                   JOptionPane.showMessageDialog(frame, "Incorrect Password");
               }
           } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Username");
           }
           
       }
    }

    
    
}
