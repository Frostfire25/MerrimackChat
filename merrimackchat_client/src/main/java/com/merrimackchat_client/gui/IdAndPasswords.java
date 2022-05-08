/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Mark Case
 */
public class IdAndPasswords {
    
    public static HashMap<String, String> loginInfo = new HashMap<String,String>();
    
    public IdAndPasswords() {
        
        try 
        {
            Scanner data_store = new Scanner(new File("C:\\Users\\Mark Case\\Documents\\NetBeansProjects\\elguezbal_case_costello_5\\credentials.txt"));           

            while (data_store.hasNextLine())
            {
                String[] split_string = data_store.nextLine().split(",");
                UserInfo u = new UserInfo(split_string);
                loginInfo.put(u.username, u.password);
            }                   
        } 

        catch (FileNotFoundException e) 
        {
            System.out.println(e.getMessage());
        }
        
       
        //loginInfo.put("burger", "fries");
    }
    
    public  HashMap getInfo() {
        return loginInfo;
    }
    
}
