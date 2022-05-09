package com.merrimackchat_server.channel;

import com.merrimackchat_server.exceptions.NoIDAvailableException;
import com.merrimackchat_server.server_properties.ServerProperties;
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
public class FileManager {

    public ChannelManager channelManager;

    /**
     * Default constructor
     *
     * @param channelManager Channel manager for this file manager to manage
     */
    public FileManager(ChannelManager channelManager) {
        this.channelManager = channelManager;

        initDirectory();
    }

    // Directory for all the server files
    private final String SERVER_DIRECTORY = System.getProperty("user.dir") + "\\server";

    private final String FILE_CHANNEL_NAME = "channels.txt";

    public File getFileInDirectory(String name) {
        return new File(SERVER_DIRECTORY + "\\" + name);
    }
    
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
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Loads all channels
     * @return If the operation worked.
     */
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
                Logger.getLogger(FileManager.class.getName()).log(Level.INFO, String.format("Channel [%s] has been loaded in.", line));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        } catch (NoIDAvailableException ex) {
            System.out.println("No more channels can be created, there are no Channel ID's left.");
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex); return false;
        }
        
        return true;
    }

    /**
     * Saves all channels
     * @return If the operation worked.
     */
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
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return true;
    }
    
    public File createServerPropertiesFile(ServerProperties serverProperties) {
        File file = getFileInDirectory("serverproperties.txt");
        
        // Creates the file if it doesn't exist.
        if(!file.exists()) {
            try {
                file.createNewFile();
                // Initizile Default file 
                serverProperties.initServerPropertiesFile(file);
                throw new FileNotFoundException("The file serverproperties.txt was not created, it must be filled out and created for the server to run.\n Path : " + file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                
                // Closes the program as the file is essential
                System.exit(-1);
            }
        }
        
        return file;
    }
               
}
