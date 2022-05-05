package com.merrimackchat_client;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketDecoder;
import com.merrimackchat_packet.data.PacketEncoder;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to handle Client I/O to the server. Inspired by :
 * https://github.com/surajkumaruk96/VoIP-Client-Server/blob/master/VoIPClient/src/VoipClient.java
 *
 * @author Derek Costello, Suraj Kumar (original author)
 */
public class Client extends PacketSender implements Runnable {

    private Microphone mic;
    private Speaker speaker;
    private Socket socket;
    private static final String IP = "localhost";
    private static final int PORT = 5000;
    
    // ID refrence of this Client default is -128 which is min;
    private byte ID;

    // Name that needs to be set through the GUI
    private String name = "Alex";
        
    // Does not allow this client to send any packets until 
    //private boolean waitingForPacketResponse = false;
    
    /**
     * Constructor initializing the mic and speaker for the client's audio I/O.
     */
    public Client() {
        mic = new Microphone();
        speaker = new Speaker();
        
        // Sets the ID to the empty value
        ID = Byte.MIN_VALUE;
    }

    @Override
    public void run() {
        // Try to connect to the server, if we can't, crash the program
        try {
            socket = new Socket(IP, PORT);
        } catch (IOException e) {
            System.err.println("Error connecting to server " + IP + " on port " + PORT);
            System.err.println(e);
            System.exit(1);
        }

        /**
         * Play audio from server to client's speakers.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                // If the speakers successfully opened
                if (speaker.open()) {
                    speaker.start(); // Starts the SourceDataLine

                    while (socket.isConnected()) { // While a connection is still established

                        try {
                            // Try writing data to the Client's speakers                            
                            byte[] readData = socket.getInputStream().readNBytes(4600);
                            //Packet packet = PacketDecoder.

                            Packet audioPacket = PacketDecoder.decodeByteArray(readData);
                            byte[] speakerBuffer = PacketDecoder.getAudioStreamFromAnAudioPacket(audioPacket);

                            //System.out.println("AUDIO INTO SPEAKER: " + Arrays.toString(speakerBuffer));

                            // Writing Out
                            System.out.println(Arrays.toString(Arrays.copyOfRange(audioPacket.getBuff(), 0, 20)));
                            //System.out.println(audioPacket.getBuff()[10] + " " + audioPacket.getBuff()[audioPacket.getBuff().length-1]);
                            System.out.println(String.format("RECEVING: First in buffer : [%s]  Last in Buffer : [%s]\n\n", speakerBuffer[0], speakerBuffer[speakerBuffer.length-1]));
                            speaker.write(speakerBuffer, 0, speakerBuffer.length);

                        } catch (IOException e) {
                            System.err.println("Could not read audio data from server: " + e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.err.println("Improper argument exception: " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                } else {
                    System.err.println("Failed to successfully open speakers");
                }
            }
        }).start();

        /**
         * This thread is used for sending audio out.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

                //long nextTimeToRun = System.currentTimeMillis();
                // If the mic successfully opened
                if (mic.isOpen()) {
                    mic.start(); // Starts the TargetDataLine

                    while (socket.isConnected()) { // While a connection is still established
                        try { // Try writing mic data to the server's data stream

                            // Test for sending a packet
                            //Packet packet = PacketEncoder.createUserJoinPacket("Alex");
                            //packet.send(socket.getOutputStream());
                            //System.out.println(Arrays.toString(packet.getBuff()));                                
                            // End of Test.
                            
                            byte value1 = 40;
                            byte value2 = 100;
                            byte[] buffer = new byte[(int) value1 * (int) value2 /* mic.getBufferSize() / 5 */]; // Commented dividing the buffer by 5 so I can take in as much audio as possible.
                            mic.read(buffer, 0, buffer.length);
                            
                            //System.out.println(Arrays.toString(buffer));

                            //if(System.currentTimeMillis() >= nextTimeToRun) {
                            //System.out.println("Buffer length : " + buffer.length + "   Read Length: " + read);
                            
                            
                            System.out.println(String.format("SENDING: First in buffer : [%s]  Last in Buffer : [%s]", buffer[0], buffer[buffer.length-1]));
                            
                            Packet audioPacket = PacketEncoder.createAudioBeingSentPacket((byte) -128, (byte) 0, value1, value2, buffer);
                            audioPacket.send(socket.getOutputStream());
                            
                            // Update the send time
                            // nextTimeToRun += 5000;
                            //}

                            //System.out.println(Arrays.toString(buffer));
                            // UNCOMMENT TO SEND AUDIO!!! socket.getOutputStream().write(buffer);
                            //socket.getOutputStream().
                            //socket.getOutputStream().write(buffer, 0, read);
                        } catch (IOException e) {
                            System.err.println("Could not write an audio packet microphone audio data to server: " + e.getMessage());
                        }
                    }
                } else {
                    System.err.println("Failed to successfully open mic");
                }
            }
        }).start();

    }
    
    /**
     * Determines if this client is ready to send audio.
     * 
     * @param value
     * @return 
     */
    public void sendAudio(boolean value) {
        if(value) {
           mic.open();
        } else {
           mic.stop();
        }   
    }

    /**
     * Sends a packet out to the socket of this client
     * 
     * @param packet Packet to be sent out 
     */
    @Override
    public boolean sendPacket(Packet packet) {
        try {
            packet.send(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Could not write packet microphone audio data to server: " + e.getMessage());
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

}
