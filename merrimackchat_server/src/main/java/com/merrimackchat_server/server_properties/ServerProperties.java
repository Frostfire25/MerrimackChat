/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.server_properties;

import com.merrimackchat_server.channel.FileManager;
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
 * Manages serverproperties.txt
 *
 * @author Alex
 */
public class ServerProperties {

    private FileManager fileManager;
    
    public String ADRESS = "";
    public int PORT = 0;
    public String SERVER_NAME = ""; 

    private File file;
    
    private final String FILE_NAME = "serverproperties.txt";
    
    public ServerProperties(FileManager fileManager) {
        this.fileManager = fileManager;
        // Creates the server file
        createServerPropertiesFile();
        // Loads the properties
        loadProperties();
    }

    /**
     * Determines if the "serverproperties.txt" file exists
     * If not a new one is generated
     * @return File
     */
    public File createServerPropertiesFile() {
        file = fileManager.getFileInDirectory(FILE_NAME);
        
        // Creates the file if it doesn't exist.
        if(!file.exists()) {
            try {
                file.createNewFile();
                // Initizile Default file 
                initServerPropertiesFile(file);
                throw new FileNotFoundException("The file serverproperties.txt was not created, it must be filled out and created for the server to run.\n Path : " + file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                
                // Closes the program as the file is essential
                System.exit(-1);
            }
        }
        
        return file;
    }
    
    /**
     * Creates the file if done incorrectly
     * @param file File
     */
    public void initServerPropertiesFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Initizizes the default file
            writer.append("address:localhost");
            writer.newLine();
            writer.append("port:5000");
            writer.newLine();
            writer.append("server_name:MerrimackChat");
            
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Loads all the properties
     */
    public void loadProperties() {
        try {
            BufferedReader writer = new BufferedReader(new FileReader(file));
            
            // Reads every line in the file
            while(writer.ready()) {
                String line = writer.readLine();
                
                // Splits every line with a colon
                String[] split = line.split(":");
                
                switch (split[0]) {
                    case "address": {
                        ADRESS = split[1];
                    }; break;
                    case "port": {
                        PORT = Integer.parseInt(split[1]);
                    }; break;
                    case "server_name": {
                        SERVER_NAME = split[1];
                    }; break;
                    default: {
                        initServerPropertiesFile(file);
                        throw new AssertionError();
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
