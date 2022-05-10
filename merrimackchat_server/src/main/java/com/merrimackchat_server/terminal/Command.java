/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.terminal;

import lombok.Getter;

/**
 *
 * @author Alex
 */
@Getter
public class Command {
    
    private String command;
    private String[] paramaters;
    private Runnable run;
    
    public Command(Runnable runnable, String command, String... paramaters) {
        this.run = runnable;
        this.command = command;
        this.paramaters = paramaters;
    }
    
    public boolean isssueCommand(String[] args) {
        // Command could not be issued, wrong arguments
        // if(paramaters.length != args.length) return false;
        
        run.run();
        
        return true;
    }   
    
}
