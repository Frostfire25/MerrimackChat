/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client;

/**
 *
 * @author Alex
 */
public class ClientDriver {
    
    private static Thread thread;
    
    public static void main(String[] args) {
        Client client = new Client();
        thread = new Thread(client);
        thread.start();
    }
    
}
