/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.manager;

import com.merrimackchat_server.ServerDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * @author Alex
 */
public abstract class ClientThread extends Thread implements Identifiable {

    private InputStream in;
    private OutputStream out;

    public ClientThread(String address, int port) {
        try {
            // Get audio input from the client (speaker)
            in = ServerDriver.getServer().getConnection().getInputStream();
            // Get audio output from the client (mic)
            out = ServerDriver.getServer().getConnection().getOutputStream();

            System.out.println("New client thread has been established " + address + " : " + port);

        } catch (IOException ex) {
            System.err.println("Error with I/O at server.");
        }
    }

    /**
     * While the client's thread is running.
     */
    @Override
    public void run() {
        // Alex's changes
        //Changing things will audio

        // Container for our mic input reader
        byte[] readData = null; // -1 is end of line. Normal range is 0-255.

        // Run while the connection is open
        while (true) {
            try { // If data is read
                // Number here determines the delay, since are waiting for x amount of bytes to come through.
                // Starting number was 1962
                // Tested working values are 1962, 1284
                
                readData = in.readNBytes(1000);
                System.out.println(Arrays.toString(readData));
            } catch (IOException e) {

            }
            // And it is not 'nothing'
            if (readData != null) {
                ServerDriver.getServer().broadcast(readData, getID()); // Broadcast the message
                readData = null; // And set readData container to our default number
            }
        }

    }

    /**
     * Writes the audio input to the socket's output line. This will play the
     * audio on the client's speakers.
     *
     * @param input audio input
     * @param threadNum number of thread running (troubleshooting)
     */
    public void play(int input, int threadNum) {
        try {
            out.write(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Cannot write audio out to socket " + threadNum);
        }
    }

    /**
     * Writes the audio input to the socket's output line. This will play the
     * audio on the client's speakers.
     *
     * @param input audio input
     * @param threadNum number of thread running (troubleshooting)
     */
    public void play(byte[] input, int threadNum) {
        try {
            out.write(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Cannot write audio out to socket " + threadNum);
        }
    }
}
