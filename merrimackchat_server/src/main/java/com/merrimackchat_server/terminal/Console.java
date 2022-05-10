/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.terminal;

import com.merrimackchat_server.ServerDriver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class Console {
    
    private final Scanner scanner = new Scanner(System.in);
    
    private ArrayList<Command> commands = new ArrayList<Command>();
    
    public Console() {
        
        // Registers all commands
        commands.add(new Command(() -> {
            System.exit(-1);
        }, "stop", ""));
        
        init();
    }
    
    private void init() {
        while(ServerDriver.getServer().isAlive()) {
            String input = scanner.nextLine();
                        
            // Split string
            String split[] = input.split(" ");
            
            // Runs the command if one exists
            commands.stream()
                    .filter(n -> n.getCommand().equalsIgnoreCase(split[0]))
                    .findFirst()
                    .ifPresent(n -> n.isssueCommand(Arrays.copyOfRange(split, 1, split.length)));
            
            // Prints out the command
            System.out.println("Command: " + input);
        }
    }
    
}
