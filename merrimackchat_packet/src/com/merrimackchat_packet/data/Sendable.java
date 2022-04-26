/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.merrimackchat_packet.data;

import java.io.OutputStream;

/**
 *
 * @author Alex
 */
public interface Sendable {
    
    public void send(OutputStream out);
    
}
