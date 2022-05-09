package com.merrimackchat_client.gui;

import com.merrimackchat_client.ClientDriver;
import static com.merrimackchat_client.gui.IdAndPasswords.loginInfo;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import lombok.*;

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
    
    File file = new File(System.getProperty("user.dir") + File.separator + "credentials.txt"); // File holding credentials
    
    /**
     * Creates new form Main
     * Default Constructor
     */
    public Main() {
        // Login information
        idAndPasswords = new IdAndPasswords(); 
        login = new Login(idAndPasswords.getInfo());  
        
        initComponents();
        
        // Set JFrame to be displayed in center of users screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        // Gray out/set active buttons on start up
        resetBtn.setEnabled(false);
        loginBtn.setEnabled(true);
        
        register = new Register(); // class instantiation 
        panelSlide1.setAnimate(5); 
        panelSlide1.init(login,register); // the two forms switched by animation
        login.addEventRegister(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //  Show register form
                panelSlide1.show(1); // Sign up page displated
                register.register();
                resetBtn.setEnabled(true);
                loginBtn.setEnabled(false);
            }
        });
        register.addEventBackLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelSlide1.show(0); // Login page displayed
                login.login();
                resetBtn.setEnabled(false);
                loginBtn.setEnabled(true);
            }
        });
        
    } // End contructor

    // Getter
    public JButton getResetBtn() {
        return resetBtn;
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
        setLocation(new java.awt.Point(0, 0));
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
            .addGap(0, 158, Short.MAX_VALUE)
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
                .addComponent(panelSlide1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelGradient1.add(panelRound1);
        panelRound1.setBounds(70, 70, 250, 190);

        loginBtn.setText("Login");
        loginBtn.setContentAreaFilled(false);
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        panelGradient1.add(loginBtn);
        loginBtn.setBounds(100, 270, 80, 22);

        resetBtn.setBackground(new java.awt.Color(116, 212, 232));
        resetBtn.setText("Register");
        resetBtn.setBorderPainted(false);
        resetBtn.setContentAreaFilled(false);
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });
        panelGradient1.add(resetBtn);
        resetBtn.setBounds(200, 270, 80, 22);

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
        //ClientDriver.getLoginBrowser().setTest(login.getUsernameText2().getText());
                
        System.out.println(loginInfo);
        if(evt.getSource()==loginBtn) {
            String userIDNew = login.getUsernameText2().getText(); // get text of JTextfield
            String passwordNew = String.valueOf(login.getPasswordText().getPassword()); // get text of JPasswordfield and convert

            if(loginInfo.containsKey(userIDNew)) { // key
                // if entered characters in strings match up,
                // display message and get rid of login browser
                if(loginInfo.get(userIDNew).equals(passwordNew)) {
                    

                    this.dispose(); // close login frame                    
                    // Once old form is disposed, open main gui form
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            myGUI myGUI = new myGUI(userIDNew);
                            myGUI.setVisible(true);
                            myGUI.requestFocusInWindow(); // makes sure textfield or other components don't auto focus on start-up
                            myGUI.setTitle("Chat App");
                            myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            
                            ClientDriver.setMyGUI(myGUI);
                        }
                    });
                    
                    // ToDo Establishes a connection when a succesful login happens.
                    // TESTING WILL BE REMOVED
                    ClientDriver.establishConnection("10.0.0.231", 5000);
                    
                // tell user if info entered is incorrect
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Incorrect Password");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Incorrect Login", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } // end if
    }//GEN-LAST:event_loginBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
                
            String registerID = register.getRegisterUsername().getText(); // get username from sign up page
            String registerPass = String.valueOf(register.getRegisterPassword().getPassword()); // get password from sign up page
            
            if(registerID.isEmpty() || registerPass.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "No username or password detected", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                
            try {
                PrintStream bw = new PrintStream(new FileOutputStream(file, true));
                bw.append("\n" + registerID + "," + registerPass);
                bw.close();
        } catch(FileNotFoundException ex) {
            System.err.println(ex);
        }
            
                    this.dispose(); // dispose login JFrame

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
            }
    }//GEN-LAST:event_resetBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBtn;
    private DesignPanels.PanelGradient panelGradient1;
    private DesignPanels.PanelRound panelRound1;
    private DesignPanels.PanelSlide panelSlide1;
    private javax.swing.JButton resetBtn;
    // End of variables declaration//GEN-END:variables
}
