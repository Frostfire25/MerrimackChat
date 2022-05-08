/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.merrimackchat_client.gui;

import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mark Case
 */
public class Login extends javax.swing.JPanel {

     HashMap<String,String> loginInfo = new HashMap<String,String>();
             
    /**
     * Creates new form LoginBrowser
     * @param loginOG
     */
    public Login(HashMap<String,String> loginOG) {

        initComponents();
        this.loginInfo = loginOG;
        
        
 
    }
    
    
    
    public String test = "test";
    
    public void login() {
        usernameText2.grabFocus();
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public JTextField getUsernameText2() {
        return usernameText2;
    }

    public void addEventRegister(ActionListener event) {
        hi.addActionListener(event);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound3 = new DesignPanels.PanelRound();
        usernameLbl2 = new javax.swing.JLabel();
        PasswordLbl2 = new javax.swing.JLabel();
        usernameText2 = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        hi = new javax.swing.JButton();

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setForeground(new java.awt.Color(255, 255, 255));

        usernameLbl2.setText("Username:");

        PasswordLbl2.setText("Password:");

        usernameText2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameText2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 215, 0));
        jLabel3.setText("Login");

        hi.setBackground(new java.awt.Color(255, 255, 255));
        hi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hi.setForeground(new java.awt.Color(255, 215, 0));
        hi.setText("No account? Sign up here");
        hi.setBorder(null);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(usernameLbl2)
                        .addGap(18, 18, 18)
                        .addComponent(usernameText2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(PasswordLbl2)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel3))
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(passwordText)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(hi)
                .addGap(46, 46, 46))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLbl2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordLbl2))
                .addGap(18, 18, 18)
                .addComponent(hi)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameText2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameText2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameText2ActionPerformed

    public void setTest(String value) {
        this.test = value;
    }
    
    public String getTest() {
        return test;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PasswordLbl2;
    private javax.swing.JButton hi;
    private javax.swing.JLabel jLabel3;
    private DesignPanels.PanelRound panelRound3;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JLabel usernameLbl2;
    private javax.swing.JTextField usernameText2;
    // End of variables declaration//GEN-END:variables
}
