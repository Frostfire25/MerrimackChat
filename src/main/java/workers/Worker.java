/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workers;

import java.util.Timer;

/**
 * A Worker is a Worker class that is a timer and a server
 *  Workers, update, and process data being accepted by a client
 *  Workers are generally ran on a seperate thread.
 *  
 *  
 * @author Alex
 */
public abstract class Worker extends com.merrimackchat_server.util.Timer<Worker>  {

    public Worker(long startDelay, long periodDelay) {
        super(startDelay, periodDelay);
    }
    
    public void kill() {
        
    }
    
}
