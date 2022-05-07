package com.merrimackchat_server.util;

import java.util.TimerTask;

/**
 *
 * @author Alex
 * @param <T> Type of class for this class to receive
 */
public abstract class Timer <T extends Timer> extends TimerTask {
    
    private long startDelay;
    private long periodDelay;
    
    // Timer
    private java.util.Timer timer = new java.util.Timer();
        
    /**
     * Default Constructor
     * 
     * @param startDelay Delay for the timer to start 
     * @param periodDelay 
     */
    public Timer(long startDelay, long periodDelay) {
        // Adding the extra 100 so that anything that needs to be defined in the sub class can be.
        this.startDelay = startDelay + 100;
        this.periodDelay = periodDelay;
        
        // Starts the timer
        startTimer();
    }
    
    /**
     * Starts the task timer
     */
    private void startTimer() {              
        this.timer.scheduleAtFixedRate(this, startDelay, periodDelay);
    }
    
    /**
     * Gets the sub instance of this class
     * @return instance of class T type.
     */
    public abstract T get();
    
}
