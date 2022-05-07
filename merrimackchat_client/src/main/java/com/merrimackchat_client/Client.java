package com.merrimackchat_client;

import com.merrimackchat_client.gui.IdAndPasswords;
import com.merrimackchat_client.gui.LoginBrowser;
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
    private static final String IP = "10.0.118.2"/*"73.249.253.64"*/;
    private static final int PORT = 5000;

    // ID refrence of this Client default is -128 which is min;
    private byte ID;
     
    // Name that needs to be set through the GUI
    public static String clientName;

    // Does not allow this client to send any packets until 
    //private boolean waitingForPacketResponse = false;
    /**
     * Constructor initializing the mic and speaker for the client's audio I/O.
     */
    public Client() {
        mic = new Microphone();
        speaker = new Speaker();

        // Assigns the name
        clientName = "Alex";
        
        // Sets the ID to the empty value
        ID = Byte.MIN_VALUE;
    }

   /**
     * Reads a packet and manages the packet correctly.
     * @param packet pack to be read
     */
    public void readPacket(Packet packet) {
        try {
            switch (packet.getPacketType()) {
                case AUDIO_BEING_SENT: {
                    byte[] speakerBuffer = PacketDecoder.getAudioStreamFromAnAudioPacket(packet);
                    System.out.println(Arrays.toString(Arrays.copyOfRange(packet.getBuff(), 0, 20)));
                    //System.out.println(audioPacket.getBuff()[10] + " " + audioPacket.getBuff()[audioPacket.getBuff().length-1]);
                    System.out.println(String.format("RECEVING: First in buffer : [%s]  Last in Buffer : [%s]\n\n", speakerBuffer[0], speakerBuffer[speakerBuffer.length - 1]));
                    speaker.write(speakerBuffer, 0, speakerBuffer.length);
                }
                ;
                break;
                case RESPONSE_USER_CONNECT_SERVER: {
                    byte serverID = packet.getArgs(1);
                    this.ID = serverID;

                    // Handle if the server is full.
                    if (ID == Byte.MIN_VALUE) {
                        System.out.println("Server is full, disconnecting.");
                        forceDisconect(); return;
                    }

                    // prints out the users ID
                    System.out.println(this.ID);
                    
                    // Now we want to send the user join packet that contains the users name
                    sendPacket(PacketEncoder.createUserJoinPacket(ID, clientName));
                }
                ;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {

    }

    public void forceDisconect() throws IOException {
        System.out.println("Force Disconecting...");
        socket.close();
        System.exit(-1);
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
         * Reads incoming packets and manages those correctly.
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

                            Packet packet = PacketDecoder.decodeByteArray(readData);

                            // Packet was null
                            if (packet == null) {
                                throw new IOException("Packet with ID " + readData[0] + " was null when the Client was reading.");
                            }

                            // Reads the packet in
                            readPacket(packet);

                        } catch(SocketException e) {
                            // If the server diconnected we want to force disconnect.
                            System.out.println(e.getMessage());
                            if(e.getMessage().contains("Connection reset")) {
                                try {forceDisconect();} catch (IOException ex) {Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);}
                            } 
                            e.printStackTrace();

                        } catch (IOException e) {
                            System.err.println("Could not read audio data from server: " + e.getMessage());
                            e.printStackTrace();
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
                // Starts the TargetDataLine
                try {
                    if (mic.open()) {
                        mic.start();
                    } else {
                        throw new IOException("Error opening up micropone line");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(!socket.isConnected()) {
                    System.out.println("Socket is not connected, closing down.");
                    disconnect();
                    return;
                }
                
                // Determines if the user is talking
                while (socket.isConnected()) { // While a connection is still established
                    if (mic.isSending()) {
                        System.out.println("Sending audio");
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
                            System.out.println(String.format("SENDING: First in buffer : [%s]  Last in Buffer : [%s]", buffer[0], buffer[buffer.length - 1]));

                            Packet audioPacket = PacketEncoder.createAudioBeingSentPacket((byte) -128, (byte) 0, value1, value2, buffer);
                            audioPacket.send(socket.getOutputStream());
                            System.out.println("Audio sent");

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
        if (value) {
            mic.startSending();
        } else {
            mic.stopSending();
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
            if (socket.isConnected()) {
                packet.send(socket.getOutputStream());
            } else {
                throw new IOException("Socket is closed");
            }
        } catch (IOException e) {
            System.err.println("Could not write packet microphone audio data to server: " + e.getMessage());
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

}
