/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.channel;

import com.merrimackchat_server.exceptions.NoIDAvailableException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ChannelFileManager {

    public ChannelManager channelManager;

    /**
     * Default constructor
     *
     * @param channelManager Channel manager for this file manager to manage
     */
    public ChannelFileManager(ChannelManager channelManager) {
        this.channelManager = channelManager;

        initDirectory();
    }

    // Directory for all the server files
    private final String SERVER_DIRECTORY = System.getProperty("user.dir") + "\\server";

    private final String FILE_CHANNEL_NAME = "channels.txt";

    /**
     * Initializes the directory for this project
     */
    private void initDirectory() {
        File file = new File(SERVER_DIRECTORY);

        if (!file.exists()) {
            System.out.println("Initilized the server directory at PATH(" + SERVER_DIRECTORY + ").");
            file.mkdirs();
        }
        
        // Creates the default files
        createDefaultFiles();
    }

    /**
     * Creates default files
     */
    private void createDefaultFiles() {
        // Channel file
        File channelFile = new File(SERVER_DIRECTORY + "\\" + FILE_CHANNEL_NAME);

        try {
            // Creates the channel file
            System.out.println(channelFile.exists() + " " + channelFile.getPath());
            if (!channelFile.exists()) {
                channelFile.createNewFile();
                System.out.println("Initizlied the Channel file at directory " + channelFile.getPath());
            }
           
            
        } catch (IOException ex) {
            Logger.getLogger(ChannelFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean loadChannels() {
        initDirectory();

        File channelFile = new File(SERVER_DIRECTORY + "\\" + FILE_CHANNEL_NAME);
                
        // Reads the files and creates the channels
        try {
            BufferedReader reader = new BufferedReader(new FileReader(channelFile));
            
            // Reads each line and creates a channel
            while(reader.ready()) {
                String line = reader.readLine();
                
                // There are no arguments in the file format yet
                // Just the channel name, so no need to split arguments
                channelManager.createChannel(line);
                Logger.getLogger(ChannelFileManager.class.getName()).log(Level.INFO, String.format("Channel [%s] has been loaded in.", line));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChannelFileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        } catch (IOException ex) {
            Logger.getLogger(ChannelFileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        } catch (NoIDAvailableException ex) {
            System.out.println("No more channels can be created, there are no Channel ID's left.");
            Logger.getLogger(ChannelFileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        }
        
        return true;
    }

    public boolean saveChannels() {
        initDirectory();

        File channelFile = new File(SERVER_DIRECTORY + "\\" + FILE_CHANNEL_NAME);        
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(channelFile));
            
            for(Channel n : channelManager.getChannels().values()) {
                // Since we are only storing the name current, no arguments
                // We don't need to add any paramaters, we can just send over the channel name
                // For easy of access
                writer.append(n.getName());
                writer.newLine();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ChannelFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

}
