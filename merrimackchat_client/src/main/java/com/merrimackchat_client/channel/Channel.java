/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.channel;

import lombok.Getter;

/**
 *
 * @author Derek
 */
@Getter
public class Channel {
    
    private String name;
    private byte id;
    
    public Channel(String name, byte id) {
        this.name = name;
        this.id = id;
        System.out.println("Created channel: " + name);
    }
    
}
