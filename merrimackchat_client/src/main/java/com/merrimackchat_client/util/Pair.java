/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Alex
 * Used for having two objects linked together
 * 
 * @param <T> Type one
 * @param <V> Type two
 */
@AllArgsConstructor
@Data
public class Pair <T, V> {
    
    private T value1;
    private V value2;
    
}