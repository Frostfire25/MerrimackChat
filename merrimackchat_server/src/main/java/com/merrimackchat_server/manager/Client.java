/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
@Getter
public class Client extends ClientThread {
    
    private String userName;
    private String address;
    private int port;
    private byte ID;
    
    public Client(String userName, String address, int port, byte ID) {
        super(address, port);
        
        this.userName = userName;
        this.address = address;
        this.port = port;
        this.ID = ID;
    }
    
}
