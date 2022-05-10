package com.merrimackchat_client.gui;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            File file = new File(System.getProperty("user.dir") + File.separator + "credentials.txt");

            // If the file doesn't exist
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // File that holds strings
            Scanner data_store = new Scanner(file);  // adds users directory so absolute path does not need to be changed         

            while (data_store.hasNextLine()) {
                String line = data_store.nextLine();

                // If the line is empty, continie to the next password
                if (line.isEmpty() || !line.contains(",")) {
                    continue;
                }

                String[] split_string = line.split(","); // split strings on comma
                UserInfo u = new UserInfo(split_string);
                loginInfo.put(u.username, u.password); // username set to first half, paqssword to second half
            } // End while                   
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } // End try-catch

    }

    // Getter
    public HashMap getInfo() {
        return loginInfo;
    }

} // End Class
