/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.merrimackchat_client.gui;

import com.merrimackchat_client.ClientDriver;
import static com.merrimackchat_client.gui.IdAndPasswords.loginInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lombok.Getter;

/**
 *
 * @author Mark Case
 */
public class Main extends javax.swing.JFrame {
    
    @Getter
    private static IdAndPasswords idAndPasswords;
    
    @Getter
    private static Login login;
    
    @Getter
    private static Register register;
    
File file = new File("C:\\Users\\Mark Case\\Documents\\NetBeansProjects\\elguezbal_case_costello_5\\credentials.txt");
    /**
     * Creates new form Main
     */
    public Main() {
        // Login
        idAndPasswords = new IdAndPasswords(); 
        login = new Login(idAndPasswords.getInfo());  
        
        initComponents();
        
        
         register = new Register();
        panelSlide1.setAnimate(5);
        panelSlide1.init(login,register);
        login.addEventRegister(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //  Show register form
                panelSlide1.show(1);
                register.register();
            }
        });
        register.addEventBackLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelSlide1.show(0);
                login.login();
            }
        });
    }


    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradient1 = new DesignPanels.PanelGradient();
        panelRound1 = new DesignPanels.PanelRound();
        panelSlide1 = new DesignPanels.PanelSlide();
        loginBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(400, 400));

        panelGradient1.setColorPrimario(new java.awt.Color(3, 84, 105));
        panelGradient1.setColorSecundario(new java.awt.Color(116, 212, 232));
        panelGradient1.setMaximumSize(new java.awt.Dimension(300, 300));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setForeground(new java.awt.Color(255, 255, 255));
        panelRound1.setMaximumSize(new java.awt.Dimension(300, 300));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        panelSlide1.setBackground(new java.awt.Color(255, 255, 255));
        panelSlide1.setMaximumSize(new java.awt.Dimension(300, 300));
        panelSlide1.setPreferredSize(new java.awt.Dimension(238, 163));

        javax.swing.GroupLayout panelSlide1Layout = new javax.swing.GroupLayout(panelSlide1);
        panelSlide1.setLayout(panelSlide1Layout);
        panelSlide1Layout.setHorizontalGroup(
            panelSlide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );
        panelSlide1Layout.setVerticalGroup(
            panelSlide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSlide1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelSlide1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelGradient1.add(panelRound1);
        panelRound1.setBounds(70, 70, 250, 170);

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        panelGradient1.add(loginBtn);
        loginBtn.setBounds(100, 270, 80, 22);

        resetBtn.setText("Register");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });
        panelGradient1.add(resetBtn);
        resetBtn.setBounds(200, 270, 72, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
                    

// Sets the username
        ClientDriver.getLoginBrowser().setTest(login.getUsernameText2().getText());
                
        System.out.println(loginInfo);
        if(evt.getSource()==loginBtn) {
            String userIDNew = login.getUsernameText2().getText(); // get text of JTextfield
            String passwordNew = String.valueOf(login.getPasswordText().getPassword()); // get text of JPasswordfield and convert


          
            
            if(loginInfo.containsKey(userIDNew)) { // key
                // if entered characters in strings match up,
                // display message and get rid of login browser
                if(loginInfo.get(userIDNew).equals(passwordNew)) {
                    JOptionPane.showMessageDialog(rootPane, "Login successful");
                    //                IdAndPasswords s = new IdAndPasswords();
                    //                LoginBrowser lb = new LoginBrowser(s.getInfo());
                    this.dispose();

                    // Establishes a connection when a succesful login happens.
                    ClientDriver.establishConnection("localhost", 5000);
                    
                    // Once old form is disposed, open main gui form
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            myGUI myGUI = new myGUI(userIDNew);
                            myGUI.setVisible(true);
                            //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                            myGUI.setTitle("Chat App");
                            myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            
                            //ClientDriver.setMyGUI(myGUI);
                        }
                    });
                    
//                                
//            String registerID = register.getRegisterUsername().getText();
//            String registerPass = String.valueOf(register.getRegisterPassword().getPassword());
//            System.out.println(registerID);
//            try{
//                
//      
//            PrintStream bw = new PrintStream(new FileOutputStream(file, true));
//            bw.append("\n" + registerID +  registerPass);
//            bw.close();
//        }catch(FileNotFoundException ex){
//        }
                    // tell user if info entered is incorrect
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Incorrect Password");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Incorrect Username");
            }
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
                                       
            String registerID = register.getRegisterUsername().getText();
            String registerPass = String.valueOf(register.getRegisterPassword().getPassword());
            System.out.println(registerID);
            try{
                
      
            PrintStream bw = new PrintStream(new FileOutputStream(file, true));
            bw.append("\n" + registerID + "," + registerPass);
            bw.close();
        }catch(FileNotFoundException ex){
        }
            
            JOptionPane.showMessageDialog(rootPane, "Register successful");
                    //                IdAndPasswords s = new IdAndPasswords();
                    //                LoginBrowser lb = new LoginBrowser(s.getInfo());
                    this.dispose();

                    // Establishes a connection when a succesful login happens.
                    ClientDriver.establishConnection("localhost", 5000);
                    
                    // Once old form is disposed, open main gui form
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            myGUI myGUI = new myGUI(registerID);
                            myGUI.setVisible(true);
                            //myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                            myGUI.setTitle("Chat App");
                            myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            
                            //ClientDriver.setMyGUI(myGUI);
                        }
                    });
    }//GEN-LAST:event_resetBtnActionPerformed

// public void addEventBackLogin(ActionListener event) {
//        backToLoginBtn.addActionListener(event);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBtn;
    private DesignPanels.PanelGradient panelGradient1;
    private DesignPanels.PanelRound panelRound1;
    private DesignPanels.PanelSlide panelSlide1;
    private javax.swing.JButton resetBtn;
    // End of variables declaration//GEN-END:variables
}
