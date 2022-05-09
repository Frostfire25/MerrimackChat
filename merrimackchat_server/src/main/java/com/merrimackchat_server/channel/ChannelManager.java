package com.merrimackchat_server.channel;

import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.client.Client;
import com.merrimackchat_server.exceptions.*;
import com.merrimackchat_server.util.Pair;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class ChannelManager {

    @Getter
    private HashMap<Byte, Channel> channels = new HashMap<>();
    private ArrayList<Pair<Boolean, Byte>> ids; // True = available; False = unavailable

    public ChannelManager() {
        ids = new ArrayList<>();
        // Add all possible IDs for channels and set "true" for availability
        for (byte b = 0; b < 127; b++) {
            ids.add(new Pair(true, b));
        }

        // TEST
        try {
            // Creates a test chanel for this purpose
            createChannel("TestChanel:1");
            createChannel("TestChanel:2");
            createChannel("TestChanel:3");
            createChannel("TestChanel:4");
        } catch (NoIDAvailableException ex) {
            Logger.getLogger(ChannelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns true if a channel with the ID exists.
     *
     * @param id ID we are checking existence for
     * @return true if one exists, false otherwise
     */
    public boolean exists(byte id) {
        return channels.containsKey(id);
    }

    /**
     * Returns all the channels to the client. Meant for first logon
     * Updated method by Derek
     * 
     * @param out Output stream of this client
     */
    public void getAllChannels(OutputStream out) {
        int counter = 1;
        for(Channel c : channels.values()) {
            byte last = 0;
            if (counter == channels.size()) {
                last = 1;
            }
            try {
                // (Name, ID, 0 (add channel Operation), First/Last Packet)
                System.out.println("Sending channel: " + c.getName());
                PacketEncoder.createChannelInfoPacket(c.getName(), (byte) c.getId(), (byte) 0).send(out);
                counter++;
            } catch (IOException ex) {
                System.err.println("Could not print out channel: " + c.getName() + ".");
            }
        }
    }

    /**
     * Creates channel by channel name.
     *
     * @param name channel name
     * @throws NoIDAvailableException if there are no available IDs
     */
    public void createChannel(String name) throws NoIDAvailableException {
        byte id = getAvailableID();
        if (id != -1) {
            channels.put(id, new Channel(name, id));
            broadcastChannelChange(name, id, (byte) 0); // Broadcast change
        } else {
            throw new NoIDAvailableException("No available IDs to create the new channel. Reduce the number of channels.");
        }
    }

    /**
     * Deletes channel by ID.
     *
     * @param id ID of channel
     * @throws ChannelNotFoundException if no such channel exists
     */
    public void deleteChannel(byte id) throws ChannelNotFoundException {
        if (exists(id)) {
            // Get channel
            Channel c = channels.get(id);
            // Remove users
            ArrayList<Client> clients = c.getClients();
            System.out.println("Clients to remove from channel b4 delete: " + clients.toString());
            for(int i = 0; i < clients.size(); i++) {
                System.out.println("Because of channel " + id + " deletion, user " + clients.get(i).getID() + " is being removed from the list of users in this channel.");
                ServerDriver.getClientManager().leaveChannel(clients.get(i).getID());
            }
            // Remove channel
            channels.remove(id);
            broadcastChannelChange(c.getName(), id, (byte) 1); // Broadcast change
        } else {
            throw new ChannelNotFoundException("No such channel is not found, so it cannot be deleted.");
        }
    }

    /**
     * Adds a user to a channel by ID.
     *
     * @param channelID channel ID
     * @param userID client being added
     * @throws com.merrimackchat_server.exceptions.ChannelNotFoundException
     */
    public void userJoinChannel(byte userID, byte channelID) throws ChannelNotFoundException {
        if (exists(channelID)) {
            Channel c = channels.get(channelID);
            c.add(userID);
            // Update list for participants of the channel
            updateForParticipantsOfChannel(c);
//            playSound(new File("soundFile.mp3"));
        } else {
            throw new ChannelNotFoundException("Could not locate channel with ID " + channelID + " for user: " + userID);
        }
    }

    /**
     * Removes a user from a channel by ID.
     *
     * @param channelID channel ID
     * @param userID client being removed
     * @throws com.merrimackchat_server.exceptions.ChannelNotFoundException
     */
    public void userLeaveChannel(byte userID, byte channelID) throws ChannelNotFoundException {
        System.out.println("Testing to see if channel " + channelID + " exists (ChannelManager.java).");
        if (exists(channelID)) {
            Channel c = channels.get(channelID);
            System.out.println("Current channel (to remove): " + c.getId() + " (ChannelManager.java).");
            c.remove(userID);
            // Update list of users for users
            updateForParticipantsOfChannel(c);
//          playSound(new File("soundFile.mp3"));
        } else {
            throw new ChannelNotFoundException("No such channel could be joined");
        }
        
    }

    /**
     * Broadcasts audio to all clients connected.
     *
     * @param input audio input
     * @param senderID ID of the user sending the audio.
     * @param channelID ID of the channel the user is sending the audio to.
     * @param len1 Length of the audio stream * {@code len2}
     * @param len2 Length of the audio stream * {@code len1}
     */
    public void broadcastAudio(byte[] input, byte senderID, byte channelID, byte len1, byte len2) {
        channels.get(channelID).broadcastAudio(PacketEncoder.createAudioBeingSentPacket(senderID, channelID, len1, len2, input), senderID);
    }

    /**
     * Sends packet information of the users to the OutputStream of the
     * requested client one-by-one by channel ID.
     *
     * @param out output stream of client
     * @param channelID ID of specified channel
     */
    public void sendUserListOfUsers(OutputStream out, byte channelID) {
        channels.get(channelID).getClients().forEach(n -> {
            try {
                PacketEncoder.createSendChannelUserPacket(n.getName()).send(out);
            } catch (IOException ex) {
                System.err.println("Could not send data for user " + n.getName() + " for channel information.");
            }
        });
    }

    /*
    Private Methods
     */
    /**
     * Gets the next available ID for a channel.
     *
     * @return available ID or -1 if unavailable
     */
    private byte getAvailableID() {
        for (int i = 0; i < ids.size(); i++) {
            Pair<Boolean, Byte> pair = ids.get(i);
            if (pair.getValue1()) { // If one is found
                pair.setValue1(false); // Set unavailable
                return pair.getValue2();
            }
        }

        return -1; // If one is not available
    }

    /**
     * Plays a sound when a channel event happens
     */
    private void playSound(File sound) {

    }

    private void broadcastChannelChange(String name, byte id, byte operation) {
        ServerDriver.getClientManager().getClientMap().values().forEach(n -> {
            try {
                PacketEncoder.createChannelInfoPacket(name, id, operation).send(n.getOut());
            } catch (IOException ex) {
                System.err.println("Could not send channel info to client: " + n.getName());
            }
        });
    }
    
    private void updateForParticipantsOfChannel(Channel c) {
        c.getClients().forEach(n -> {
            sendUserListOfUsers(n.getOut(), c.getId());
        });
    }
}
