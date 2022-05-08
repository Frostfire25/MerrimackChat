/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.gui;

/**
 *
 * @author Mark Case
 */
public class UserInfo {
    
    public String username, password; 

    public UserInfo(String[] parts)
    {
        username = parts[0];
        password = parts[1];
    }
    
}
