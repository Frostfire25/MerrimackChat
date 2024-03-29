package com.merrimackchat_server.client;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketDecoder;
import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_packet.data.PacketType;
import com.merrimackchat_packet.data.Util;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.channel.Channel;
import com.merrimackchat_server.channel.ChannelManager;
import com.merrimackchat_server.exceptions.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Getter
    private Socket connection;

    public ClientThread(String address, int port, Socket connection) {
        // Assigns this clien to this thread.
        this.client = client;
        try {
            // Assigns the connection over
            this.connection = connection;
            
            // Get audio input from the client (speaker) // UPDATED ON MONDAY BY ALEX
            in = connection.getInputStream();
            // Get audio output from the client (mic)
            out = connection.getOutputStream();
            // For test, included in the test channel
            //ServerDriver.getChannelManager().getAllChannels(out);

            System.out.println("New client thread has been established " + address + " : " + port);

        } catch (IOException ex) {
            System.err.println("Error with I/O at server.");
        }
    }
    
    /**
     * Closes the clients connection
     */
    public void closeSocket() {
        if(connection != null && !connection.isClosed()) {
            try {
                connection.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                
                byte[] readData = getIn().readNBytes(4600);
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
                        ServerDriver.getChannelManager().broadcastAudio(toBroadCast, packet.getArgs(1), packet.getArgs(2), packet.getArgs(3), packet.getArgs(4));
                        
                    }; break;
                    case USER_JOIN_CHANNEL: {
                        try {                            
                            // Client ID, Channel ID
                            ServerDriver.getClientManager().joinChannel(packet.getArgs(1), packet.getArgs(2));
                        } catch (ChannelNotFoundException ex) {
                            System.out.println("No channel found for user to join.");
                        }
                    }; break;
                    case USER_LEAVE_CHANNEL:  {
                        ServerDriver.getClientManager().leaveChannel(packet.getArgs(1));
                    }; break;
                    case USER_CREATE_CHANNEL: {
                        try {
                            ServerDriver.getChannelManager().createChannel(Util.getStringFromByteArray(packet.getBuffWithoutArgsAndTrailingFillers()));
                            // Saves the channels after they are updated.
                            ServerDriver.getFileManager().saveChannels();
                        } catch (NoIDAvailableException e) {
                            try {
                                PacketEncoder.createErrorMessagePacket(e.getMessage()).send(getOut());
                            } catch (IOException ex) {
                                System.out.println("Could not send NoIDAvailableError");
                            }
                        }
                    }break;
                    case USER_DELETE_CHANNEL: {
                        try {
                            ServerDriver.getChannelManager().deleteChannel(packet.getArgs(1));
                            // Saves the channels after they are updated.
                            ServerDriver.getFileManager().saveChannels();
                        } catch (ChannelNotFoundException e) {
                            try {
                                PacketEncoder.createErrorMessagePacket(e.getMessage()).send(getOut());
                            } catch (IOException ex) {
                                System.out.println("Could not send NoIDAvailableError");
                            }
                        }
                    }break;
                    case USER_JOIN_SERVER: {
                        //System.out.println("Buff: " + Arrays.toString(packet.getBuff()));
                        System.out.println("Buff Without Args: " + Arrays.toString(packet.getBuffWithoutArgsAndTrailingFillers()));
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
                        byte ID = packet.getArgs(1);
                        Client leaveClient = ServerDriver.getClientManager().getClientMap().get(ID);
                        
                        System.out.println("Attempting to disconnect " + leaveClient.getName() + ".");

                        // Removes the client if he exists
                        if(leaveClient != null) {
                            System.out.println("[Disconected] "+leaveClient.getName()+" has disconected from the server.");
                            ServerDriver.getClientManager().removeClient(ID);
                        }
                    }; break;
                    case USER_PREVIEW_CHANNEL:  {
                        try {
                            ChannelManager cm = ServerDriver.getChannelManager();
                            cm.userPreviewChannel(client.getID(), packet.getArgs(1));
                        } catch (ChannelNotFoundException ex) {
                            System.out.println("Could not locate channel of ID " + packet.getArgs(1));
                        }
                    }; break;
                    case USER_SEND_TEXT: {
                        // Gets the client
                        Client client = ServerDriver.getClientManager().getClientMap().get(packet.getArgs(1));
                        
                        // Assures the client exists
                        if(client == null) return;
                        
                        // Formatted text line
                        String formattedText = PacketDecoder.getFormattedTextFromATextPacket(packet, client.getName());
                        ServerDriver.getChannelManager().broadcastText(packet.getArgs(2), PacketEncoder.createUserSendText(packet.getArgs(1), packet.getArgs(2), formattedText));
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
            getOut().write(input);
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
            getOut().write(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Cannot write audio out to socket " + threadNum);
        }
    }

    /**
     * @return the in
     */
    public InputStream getIn() {
        return in;
    }

    /**
     * @return the out
     */
    public OutputStream getOut() {
        return out;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
