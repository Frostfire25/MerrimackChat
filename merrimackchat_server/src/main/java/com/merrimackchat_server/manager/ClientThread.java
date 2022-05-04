/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_server.manager;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketDecoder;
import com.merrimackchat_server.ServerDriver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import lombok.Getter;

/**
 *
 * @author Alex
 */
public abstract class ClientThread extends Thread implements Identifiable {

    @Getter
    private InputStream in;
    @Getter
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
        Packet packet = null;      
        
        // Run while the connection is open
        while (true) {
            try { 
                // If data is read
                // Number here determines the delay, since are waiting for x amount of bytes to come through.
                // Starting number was 1962
                // Tested working values are 1962, 1284
                // Using a 1400 Packet byte size
                
                byte[] readData = in.readNBytes(4600);
                //Packet packet = PacketDecoder.
                
                packet = PacketDecoder.decodeByteArray(readData);
                                

                // If we have any errors, we want to assign readData to null.    
                readData = null;
              
            } catch (IOException e) {
                packet = null;
            }
            catch (NullPointerException e) {
                packet = null;
            }
            
            // And it is not 'nothing'
            // Broadcasting th packet out
            if (packet != null && packet.getPacketType() != null) {
                
                switch(packet.getPacketType()) {
                    
                    // Sends out the packet if it is an Audio Packet type.
                    case AUDIO_BEING_SENT: {
                        
                        byte[] toBroadCast = PacketDecoder.getAudioStreamFromAnAudioPacket(packet);
                        //System.out.println("Broadcasting out : " + Arrays.toString(toBroadCast));
                        ServerDriver.getServer().broadcastAudio(toBroadCast, packet.getArgs(1), packet.getArgs(2), packet.getArgs(3), packet.getArgs(4));
                        
                    }; break;
                    case USER_JOIN_CHANNEL: {
                        byte[] info = PacketDecoder.getDataFromAChannelJoinPacket(packet);
                        ServerDriver.getClientManager().channelJoined(info[0], info[1]);
                    }; break;
                }
                
                packet = null; // And set readData container to our default number
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
