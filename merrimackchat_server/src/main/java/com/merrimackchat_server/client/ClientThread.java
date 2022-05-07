package com.merrimackchat_server.client;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketDecoder;
import com.merrimackchat_packet.data.Util;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.channel.Channel;
import com.merrimackchat_server.exceptions.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
public abstract class ClientThread extends Thread implements Identifiable {

    @Getter
    private InputStream in;
    @Getter
    private OutputStream out;
    
    @Getter @Setter private Client client;

    public ClientThread(String address, int port) {
        // Assigns this clien to this thread.
        this.client = client;
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
                        // Gets the client
                        //Client client = ServerDriver.getClientManager().getClientMap().get(packet.getArgs(1));
                        
                        // Makes sure the client exists.
                        //if (client == null) { System.out.println("Null Client! In Client Thread, ID: " + packet.getArgs(1)); break; }
                        
                        // Gets the channel
                        //Channel channel = ServerDriver.getChannelManager().getChannels().get(client.getChannel());
                        
                        // Makes sure the channel exists.
                        //if (channel == null) { System.out.println("Null Client! In Client Thread, ID: " + client.getChannel()); break; }
                               
                        //channel.broadcastAudio(packet);
                        ServerDriver.getChannelManager().broadcastAudio(toBroadCast, packet.getArgs(1), packet.getArgs(2), packet.getArgs(3), packet.getArgs(4));
                        
                    }; break;
                    case USER_JOIN_CHANNEL: {
                        ServerDriver.getClientManager().joinChannel(packet.getArgs(0), packet.getArgs(1));
                    }; break;
                    case USER_LEAVE_CHANNEL: {
                        ServerDriver.getClientManager().leaveChannel(packet.getArgs(0), packet.getArgs(1));
                    }; break;
                    case USER_CREATE_CHANNEL: {
                        try {
                            ServerDriver.getChannelManager().createChannel(Base64.getEncoder().encodeToString(packet.getBuff()));
                        } catch (NoIDAvailableException e) {
                            // Send error to client requesting
                        }
                    }; break;
                    case USER_DELETE_CHANNEL: {
                        try {
                            ServerDriver.getChannelManager().deleteChannel(Base64.getEncoder().encodeToString(packet.getBuff()));
                        } catch (ChannelNotFoundException e) {
                            // Send error to client requesting
                        }
                    }; break;
                    case USER_JOIN_SERVER: {
                        //System.out.println("Buff: " + Arrays.toString(packet.getBuff()));
                        //System.out.println("Buff Without Args: " + Arrays.toString(packet.getBuffWithoutArgsAndTrailingFillers()));
                        // Update the clients name
                        String clientsName = Util.getStringFromByteArray(packet.getBuffWithoutArgsAndTrailingFillers());
                        byte clientID = packet.getArgs(1);
                        
                        ServerDriver.getClientManager().getClientMap().get(clientID).setName(clientsName);
                        
                        System.out.println(String.format("(thread_%s): Joined: %s", getClient().getName(), clientsName));
                        
                        // ToDo here!
                        // Send out all channel packets to the client
                        // For Derek or Alex with Channels
                    }; break;
                    case USER_LEFT_SERVER: {
                        
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
