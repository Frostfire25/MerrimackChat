/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimackchat_client.gui;

import com.merrimackchat_client.ClientDriver;
import com.merrimackchat_client.listeners.KeyListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.undo.UndoManager;



/**
 *
 * @author Mark Case
 */
public class myGUI extends javax.swing.JFrame  implements Runnable {

    private int second, minute, hour;
    

    UndoManager um = new UndoManager();
    
    /**
     * Creates new form myGUI
     */
    public myGUI() {
        initComponents();
        Thread t = new Thread(this);
        t.start(); // start thread for run method
        
        addPlaceHolderStyle(chatText);
        

    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        menuLabel = new javax.swing.JLabel();
        chooseCommComboBox = new javax.swing.JComboBox<>();
        chooseCommLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        cardPanels = new javax.swing.JPanel();
        chatPanel = new javax.swing.JTextArea();
        audioPanel = new javax.swing.JTextArea();
        clockPanel = new javax.swing.JPanel();
        clockLabel = new javax.swing.JLabel();
        chatText = new javax.swing.JTextField();
        undo = new javax.swing.JButton();
        redo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        menuPanel.setBackground(new java.awt.Color(30, 61, 89));

        menuLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menuLabel.setForeground(new java.awt.Color(245, 240, 225));
        menuLabel.setText("Main Menu");

        chooseCommComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chat Function", "Audio Function" }));
        chooseCommComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        chooseCommComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCommComboBoxActionPerformed(evt);
            }
        });

        chooseCommLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chooseCommLabel.setForeground(new java.awt.Color(245, 240, 225));
        chooseCommLabel.setText("Communication Type:");

        jButton1.setText("Screenshot");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Leave");

        jButton4.setText("Join");

        jButton3.setText("Delete");

        jButton2.setText("Create");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 240, 225));
        jLabel1.setText("Functions:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 240, 225));
        jLabel2.setText("Channels:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(245, 240, 225));
        jLabel3.setText("Users:");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Channel 1", "Channel 2", "Channel 3", "Channel 4", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chooseCommLabel))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(menuLabel))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chooseCommComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuLabel)
                .addGap(12, 12, 12)
                .addComponent(chooseCommLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseCommComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        headerPanel.setBackground(new java.awt.Color(255, 110, 64));

        headerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(245, 240, 225));
        headerLabel.setText("Chat App");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(343, Short.MAX_VALUE)
                .addComponent(headerLabel)
                .addGap(319, 319, 319))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanels.setBackground(new java.awt.Color(255, 255, 255));
        cardPanels.setLayout(new java.awt.CardLayout());

        chatPanel.setEditable(false);
        chatPanel.setBackground(new java.awt.Color(245, 240, 225));
        chatPanel.setColumns(20);
        chatPanel.setRows(5);
        cardPanels.add(chatPanel, "card5");

        audioPanel.setBackground(new java.awt.Color(245, 240, 225));
        audioPanel.setColumns(20);
        audioPanel.setRows(5);
        cardPanels.add(audioPanel, "card6");

        clockPanel.setBackground(new java.awt.Color(30, 61, 89));

        clockLabel.setBackground(new java.awt.Color(30, 61, 89));
        clockLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clockLabel.setForeground(new java.awt.Color(245, 240, 225));

        javax.swing.GroupLayout clockPanelLayout = new javax.swing.GroupLayout(clockPanel);
        clockPanel.setLayout(clockPanelLayout);
        clockPanelLayout.setHorizontalGroup(
            clockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );
        clockPanelLayout.setVerticalGroup(
            clockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        chatText.setText("Type your response here...");
        chatText.getDocument().addUndoableEditListener(new javax.swing.event.UndoableEditListener(){
            public void undoableEditHappened(javax.swing.event.UndoableEditEvent evt) {
                um.addEdit(evt.getEdit());
            }
        });
        chatText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                chatTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                chatTextFocusLost(evt);
            }
        });
        chatText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatTextActionPerformed(evt);
            }
        });
        chatText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chatTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chatTextKeyReleased(evt);
            }
        });

        undo.setMnemonic('u');
        undo.setText("Undo");
        undo.setToolTipText("ALT -U");
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });

        redo.setMnemonic('r');
        redo.setText("Redo");
        redo.setToolTipText("ALT-R");
        redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanels, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chatText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardPanels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(redo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(undo)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /*
    These methods set font of jTextField to light gray or black depending 
    on if focus is gained or lost
    */
    public void addPlaceHolderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setText("Type your response here...");
    }
    
    public void removePlaceHolderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
    }
    
    
    
    /*
    Switches panels using cardlayout by identifying item selected with string.
    Clicks switch audio and chat panels
    */
    private void chooseCommComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseCommComboBoxActionPerformed
        if(chooseCommComboBox.getSelectedItem() == "Chat Function") {
            cardPanels.removeAll();
            cardPanels.add(chatPanel);
            cardPanels.repaint();
            cardPanels.revalidate();
        } else {
           cardPanels.removeAll();
            cardPanels.add(audioPanel);
            cardPanels.repaint();
            cardPanels.revalidate();
        }
    }//GEN-LAST:event_chooseCommComboBoxActionPerformed

    
    
    /*
    If jTextField gains focus, set text to normal font
    If lost, set back to ghost text
    */
    private void chatTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chatTextFocusGained
        
            chatText.setText("");
            chatText.requestFocus();
            
            removePlaceHolderStyle(chatText);
            
        
    }//GEN-LAST:event_chatTextFocusGained

    private void chatTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chatTextFocusLost
        if(chatText.getText().equals("")) {
            addPlaceHolderStyle(chatText);
            //chatText.setText("Type your response here...");
        }
    }//GEN-LAST:event_chatTextFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Screenshot screenshotBtn = new Screenshot();
        screenshotBtn.makePanelImage(chatPanel);
        
   
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private KeyListener keyListener = new KeyListener();
    
    /*
    Calls key event in KeyListener class
    */
    private void chatTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatTextKeyReleased
        keyListener.keyReleased(evt);
    }//GEN-LAST:event_chatTextKeyReleased
    
    
    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed
        undo();
        System.out.println(System.getProperty("java.awt.Toolkit getDefaultToolkitd"));
System.out.println(System.getProperty("awt.toolkit"));
    }//GEN-LAST:event_undoActionPerformed

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoActionPerformed
        redo();
    }//GEN-LAST:event_redoActionPerformed

    private void chatTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatTextActionPerformed

    private void chatTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatTextKeyPressed
        KeyListener keyListener = new KeyListener();
        
    }//GEN-LAST:event_chatTextKeyPressed



//    private void chatPanelUndoableEdit(javax.swing.event.UndoableEditEvent evt) {                                       
//        JOptionPane.showMessageDialog(chatPanel, "Hi");
//    } 

      /*
    Getter method for JOptionPane messages
    */
    public static JTextArea getChatPanel() {
        return chatPanel;
    }

    public static JTextField getChatText() {
        return chatText;
    }
    
    
    
    /*
    Run method for 24h clock
    */
    @Override
    public void run() {
        while(true) {
            Calendar cal = Calendar.getInstance();
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
            second = cal.get(Calendar.SECOND);
            
            // for 24 hour time
            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
            Date date = cal.getTime();
            String time24 = sdf24.format(date);
            
            // set it to label
            clockLabel.setText(time24);
        }
    } // end run

    /*
    Undo and redo methods using undoManager class
    */
    public void undo() {
        um.undo();
    }
    
    public void redo() {
        um.redo();
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea audioPanel;
    private javax.swing.JPanel cardPanels;
    private static javax.swing.JTextArea chatPanel;
    private static javax.swing.JTextField chatText;
    private javax.swing.JComboBox<String> chooseCommComboBox;
    private javax.swing.JLabel chooseCommLabel;
    private static javax.swing.JLabel clockLabel;
    private javax.swing.JPanel clockPanel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton redo;
    private javax.swing.JButton undo;
    // End of variables declaration//GEN-END:variables
}
