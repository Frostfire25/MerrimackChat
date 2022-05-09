package com.merrimackchat_client.gui;

import com.merrimackchat_client.Client;
import com.merrimackchat_client.ClientDriver;
import com.merrimackchat_client.channel.Channel;
import com.merrimackchat_client.channel.ChannelManager;
import com.merrimackchat_client.listeners.KeyListener;
import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketEncoder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.undo.UndoManager;
import lombok.Getter;



/**
 *
 * @author Mark Case
 */
public class myGUI extends javax.swing.JFrame  implements Runnable {
       
    @Getter
    private static IdAndPasswords idAndPasswords;
    
    @Getter
    private static Login loginBrowser;
    
    @Getter
    private static KeyListener keyListener = new KeyListener(); 

    private int second, minute, hour;
    private ArrayList<String> channelNames = new ArrayList<>();
    private ArrayList<String> userNames = new ArrayList<>();

    UndoManager um = new UndoManager();
    
    @Getter
    private String clientName;
    
    static final IdAndPasswords s = new IdAndPasswords(); 
    //static final LoginBrowser lb = new LoginBrowser(s.getInfo());
        
    // Name that needs to be set through the GUI
    
// 
    /**
     * 3.
     * Creates new form myGUI
     */
    public myGUI(String userIDNew) {
        initComponents();
        Thread t = new Thread(this);
        t.start(); // start thread for run method
        
        addPlaceHolderStyle1(chatText);
        addPlaceHolderStyle2(IPText);
        
   
        // Set JFrame to be displayed in center of users screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        // Add selection listener to channelsJList
        channelsJList.addListSelectionListener((ListSelectionEvent e) -> {
            userNames.clear();
            refreshUsersList();
            if(channelsJList.getSelectedValue() != null) {
                byte ID = ClientDriver.getChannelManager().get(channelsJList.getSelectedValue()).getId();
                ClientDriver.getClient().sendPacket(PacketEncoder.createUserPreviewPacket(ID));
            }
        });
        
        // Assigns the name the new name.
        this.clientName = userIDNew;
    }

    public myGUI() {
        initComponents();
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
        enterIPLabel = new javax.swing.JLabel();
        screenShotButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();
        joinButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        functionsLabel = new javax.swing.JLabel();
        channelsLabel = new javax.swing.JLabel();
        usersLabel = new javax.swing.JLabel();
        jScrollPaneChannels = new javax.swing.JScrollPane();
        channelsJList = new javax.swing.JList<>();
        IPText = new javax.swing.JTextField();
        connectToServerButton = new javax.swing.JButton();
        jScrollPaneChannels1 = new javax.swing.JScrollPane();
        usersJList = new javax.swing.JList<>();
        cardPanels = new javax.swing.JPanel();
        chatPanel = new javax.swing.JTextArea();
        headerLabel = new javax.swing.JLabel();
        clockPanel = new javax.swing.JPanel();
        clockLabel = new javax.swing.JLabel();
        chatText = new javax.swing.JTextField();
        undo = new javax.swing.JButton();
        redo = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                frameClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        menuPanel.setBackground(new java.awt.Color(30, 61, 89));

        menuLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menuLabel.setForeground(new java.awt.Color(245, 240, 225));
        menuLabel.setText("Main Menu");

        enterIPLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        enterIPLabel.setForeground(new java.awt.Color(245, 240, 225));
        enterIPLabel.setText("Enter IP:");

        screenShotButton.setText("Screenshot");
        screenShotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screenShotButtonActionPerformed(evt);
            }
        });

        leaveButton.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        leaveButton.setForeground(new java.awt.Color(245, 240, 225));
        leaveButton.setText("Leave");
        leaveButton.setBorderPainted(false);
        leaveButton.setContentAreaFilled(false);
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        joinButton.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        joinButton.setForeground(new java.awt.Color(245, 240, 225));
        joinButton.setText("Join");
        joinButton.setBorderPainted(false);
        joinButton.setContentAreaFilled(false);
        joinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(245, 240, 225));
        deleteButton.setText("Delete");
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        createButton.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        createButton.setForeground(new java.awt.Color(245, 240, 225));
        createButton.setText("Create");
        createButton.setBorderPainted(false);
        createButton.setContentAreaFilled(false);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        functionsLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        functionsLabel.setForeground(new java.awt.Color(245, 240, 225));
        functionsLabel.setText("Functions:");

        channelsLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        channelsLabel.setForeground(new java.awt.Color(245, 240, 225));
        channelsLabel.setText("Channels:");

        usersLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usersLabel.setForeground(new java.awt.Color(245, 240, 225));
        usersLabel.setText("Users:");

        jScrollPaneChannels.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneChannels.setViewportView(channelsJList);

        IPText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IPTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                IPTextFocusLost(evt);
            }
        });

        connectToServerButton.setText("Connect!");
        connectToServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectToServerButtonActionPerformed(evt);
            }
        });

        jScrollPaneChannels1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        usersJList.setFocusable(false);
        jScrollPaneChannels1.setViewportView(usersJList);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enterIPLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IPText, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectToServerButton))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(menuLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(screenShotButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(functionsLabel)
                            .addComponent(leaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(joinButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(channelsLabel)
                            .addComponent(jScrollPaneChannels, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(usersLabel))
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPaneChannels1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuLabel)
                .addGap(12, 12, 12)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterIPLabel)
                    .addComponent(IPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectToServerButton))
                .addGap(46, 46, 46)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(functionsLabel)
                    .addComponent(channelsLabel)
                    .addComponent(usersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPaneChannels, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(createButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(leaveButton)
                        .addGap(18, 18, 18)
                        .addComponent(joinButton))
                    .addComponent(jScrollPaneChannels1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(screenShotButton)
                .addContainerGap())
        );

        cardPanels.setBackground(new java.awt.Color(255, 255, 255));

        chatPanel.setEditable(false);
        chatPanel.setBackground(new java.awt.Color(245, 240, 225));
        chatPanel.setColumns(20);
        chatPanel.setRows(100);

        headerLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(245, 240, 225));
        headerLabel.setText("Chat App");

        javax.swing.GroupLayout cardPanelsLayout = new javax.swing.GroupLayout(cardPanels);
        cardPanels.setLayout(cardPanelsLayout);
        cardPanelsLayout.setHorizontalGroup(
            cardPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cardPanelsLayout.setVerticalGroup(
            cardPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
            .addComponent(clockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
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

        kGradientPanel1.setkEndColor(new java.awt.Color(245, 240, 225));
        kGradientPanel1.setkGradientFocus(700);
        kGradientPanel1.setkStartColor(new java.awt.Color(30, 61, 89));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanels, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chatText, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(redo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardPanels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chatText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(undo)
                            .addComponent(redo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /*
    These methods set font of jTextField to light gray or black depending 
    on if focus is gained or lost
    */
    public void addPlaceHolderStyle1(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setText("Type your response here...");
    }
    
        public void addPlaceHolderStyle2(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setText("xxx.xx.xx.xx");
    }
    
    public void removePlaceHolderStyle(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
    }
    
    
    
    
    
    /*
    If jTextField gains focus, set text to normal font
    If lost, set back to ghost text
    */
    private void chatTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chatTextFocusGained
        
        channelsJList.setEnabled(false);

        chatText.setText("");
        chatText.requestFocus();

        removePlaceHolderStyle(chatText);
        
    }//GEN-LAST:event_chatTextFocusGained

    private void chatTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chatTextFocusLost
        
        channelsJList.setEnabled(true);
        
        if(chatText.getText().equals("")) {
            addPlaceHolderStyle1(chatText);
            //chatText.setText("Type your response here...");
        }
        
    }//GEN-LAST:event_chatTextFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void screenShotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screenShotButtonActionPerformed
        Screenshot screenshotBtn = new Screenshot();
        screenshotBtn.makePanelImage(chatPanel);
        
   
    }//GEN-LAST:event_screenShotButtonActionPerformed

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

    private void chatTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatTextKeyPressed
        keyListener.keyPressed(evt);
    
    }//GEN-LAST:event_chatTextKeyPressed

    private void connectToServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectToServerButtonActionPerformed
       keyListener.connectPressed(evt);
    }//GEN-LAST:event_connectToServerButtonActionPerformed

    private void IPTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IPTextFocusGained
        IPText.setText("");
            IPText.requestFocus();
            
            removePlaceHolderStyle(IPText);
    }//GEN-LAST:event_IPTextFocusGained

    private void IPTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IPTextFocusLost
        if(IPText.getText().equals("")) {
            addPlaceHolderStyle2(IPText);
        }
    }//GEN-LAST:event_IPTextFocusLost

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
      keyListener.keyPressed(evt);
    }//GEN-LAST:event_formKeyPressed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        String name = JOptionPane.showInputDialog(null, "Please enter a name (under 15 characters preferably) for the new channel:");
        if(name != null && !name.equals(""))
            ClientDriver.getClient().sendPacket(PacketEncoder.createChannelCreatePacket(name));
        else
            System.out.println("No name given. Cancelling operation.");
    }//GEN-LAST:event_createBtnActionPerformed

    private void joinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinBtnActionPerformed
        if(channelsJList.getSelectedIndex() != -1) {
            Channel c = ClientDriver.getChannelManager().get(channelNames.get(channelsJList.getSelectedIndex()));
            ClientDriver.getClient().sendPacket(PacketEncoder.createChannelJoinPacket(ClientDriver.getClient().getID(), c.getId()));
        }
    }//GEN-LAST:event_joinBtnActionPerformed

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveBtnActionPerformed
        ClientDriver.getClient().sendPacket(PacketEncoder.createChannelLeavePacket(ClientDriver.getClient().getID()));
        // Clears text when a client leaves a channel
    }//GEN-LAST:event_leaveBtnActionPerformed

    private void frameClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_frameClosing
        // Called when the frame is closing to disconect the client properly
        ClientDriver.getClient().disconnect();
    }//GEN-LAST:event_frameClosing

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Channel c = ClientDriver.getChannelManager().get(channelNames.get(channelsJList.getSelectedIndex()));
        ClientDriver.getClient().sendPacket(PacketEncoder.createChannelDeletePacket(c.getId()));
    }

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

    private void refreshChannelList() {
        channelsJList.setListData(channelNames.toArray(String[]::new));
        validate();
    }
    
    private void refreshUsersList() {
        usersJList.setListData(userNames.toArray(String[]::new));
        validate();
    }
    
    public void addChannel(String channelName) {
        channelNames.add(channelName);
        refreshChannelList();
    }
    
    public void removeChannel(String channelName) {
        channelNames.remove(channelName);
        refreshChannelList();
    }
    
    public void addUserToPreviewList(String userName) {
        userNames.add(userName);
        refreshUsersList();
    }
    
    public void removeUserFromPreviewList(String userName) {
        userNames.remove(userName);
        refreshUsersList();
    }
    
    public void clearUserList() {
        userNames.clear();
        validate();
    }
        
    /**
     * Clears the user text area
     */
    public void clearText() {
        chatPanel.setText("");
    }    
    
    /**
     * Adds {@code text} to the current text area
     * @param text Text to be added
     */
    public void addTextToTextBox(String text) {
        String currentText = text + "\n" + chatPanel.getText(); 
        chatPanel.setText(currentText);
    }
    
    public JTextField getIPText() {
        return IPText;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IPText;
    private javax.swing.JPanel cardPanels;
    private javax.swing.JList<String> channelsJList;
    private javax.swing.JLabel channelsLabel;
    private static javax.swing.JTextArea chatPanel;
    private static javax.swing.JTextField chatText;
    private static javax.swing.JLabel clockLabel;
    private javax.swing.JPanel clockPanel;
    private javax.swing.JButton connectToServerButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel enterIPLabel;
    private javax.swing.JLabel functionsLabel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JScrollPane jScrollPaneChannels;
    private javax.swing.JScrollPane jScrollPaneChannels1;
    private javax.swing.JButton joinButton;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JButton leaveButton;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton redo;
    private javax.swing.JButton screenShotButton;
    private javax.swing.JButton undo;
    private javax.swing.JList<String> usersJList;
    private javax.swing.JLabel usersLabel;
    // End of variables declaration//GEN-END:variables
}
