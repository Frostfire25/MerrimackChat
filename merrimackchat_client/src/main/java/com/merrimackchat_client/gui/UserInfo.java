package com.merrimackchat_client.gui;

/**
 *
 * @author Mark Case
 */
public class UserInfo {
    
    public String username, password; 

    public UserInfo(String[] parts) {
        username = parts[0]; // username is position 0
        password = parts[1]; // password is position 1
    }
    
}
