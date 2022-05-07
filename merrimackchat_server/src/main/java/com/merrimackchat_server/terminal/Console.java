/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.terminal;

import com.merrimackchat_server.ServerDriver;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class Console {
    
    private final Scanner scanner = new Scanner(System.in);
    
    public Console() {
        init();
    }
    
    private void init() {
        while(ServerDriver.getServer().isAlive()) {
            String input = scanner.nextLine();
            
            System.out.println("Command: " + input);
        }
    }
    
}
