package com.merrimackchat_client.gui;

import java.io.*;
import java.util.*;

/**
 *
 * @author Mark Case
 */
public class IdAndPasswords {
    
    // HashMap data structure to store two strings statements: username, password
    public static HashMap<String, String> loginInfo = new HashMap<>();
    
    // Constructor
    public IdAndPasswords() {
        
        try {
            // File that holds strings
            Scanner data_store = new Scanner(new File(System.getProperty("user.dir") + File.separator + "credentials.txt"));  // adds users directory so absolute path does not need to be changed         

            while (data_store.hasNextLine()) {
                String line = data_store.nextLine();   
                
                // If the line is empty, continie to the next password
                if(line.isEmpty() || !line.contains(",")) continue;
                
                String[] split_string = line.split(","); // split strings on comma
                UserInfo u = new UserInfo(split_string);
                loginInfo.put(u.username, u.password); // username set to first half, paqssword to second half
            } // End while                   
        }  
        
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } // End try-catch
        
    }
    
    // Getter
    public  HashMap getInfo() {
        return loginInfo;
    }
    
} // End Class
