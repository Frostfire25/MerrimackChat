/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.gui;

import java.util.HashMap;

/**
 *
 * @author Mark Case
 */
public class IdAndPasswords {
    
    HashMap<String, String> loginInfo = new HashMap<String,String>();
    
    public IdAndPasswords() {
        loginInfo.put("Mark", "123");
    }
    
    public HashMap getInfo() {
        return loginInfo;
    }
    
}
