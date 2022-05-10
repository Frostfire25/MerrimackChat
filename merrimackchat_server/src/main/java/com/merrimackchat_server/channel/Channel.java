package com.merrimackchat_server.channel;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_server.Server;
import com.merrimackchat_server.ServerDriver;
import com.merrimackchat_server.client.Client;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
public class Channel {

    private String name;
    private byte id;
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Client> previewers = new ArrayList<>();

    public Channel(String name, byte id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Remove client by ID.
     *
     * @param clientID client's ID.
     */
    public void remove(byte clientID) {
        Client client = ServerDriver.getClientManager().getClientMap().get(clientID);
        getClients().remove(client);
        updateForPreviewers();
    }

    /**
     * Add client by ID.
     *
     * @param clientID client's ID.
     */
    public void add(byte clientID) {
        Client client = ServerDriver.getClientManager().getClientMap().get(clientID);
        getClients().add(client);
        updateForPreviewers();
    }

    /**
     * Adds to list of previewers by ID.
     *
     * @param clientID previewer's ID
     */
    public void addToPreviewers(byte clientID) {
        Client client = ServerDriver.getClientManager().getClientMap().get(clientID);
        previewers.add(client);
        sendPreviewData(client.getOut());
    }

    /**
     * Removes from list of previewers by ID.
     *
     * @param clientID previewer's ID
     */
    public void removeFromPreviewers(byte clientID) {
        previewers.remove(ServerDriver.getClientManager().getClientMap().get(clientID));
    }

    /**
     * Clears clients from the channel.
     */
    public void clear() {
        getClients().clear();
    }

    /**
     * Broadcasts an audio packet to all clients in this channel.
     *
     * @param packet audio packet
     * @param senderID sender's ID
     */
    public void broadcastAudio(Packet packet, byte senderID) {

        Set<Client> toRemove = new HashSet<>();

        getClients().stream().filter(n -> n.getID() != senderID).forEach(n -> {

            // Assert that the client is still connected
            try {
                packet.send(n.getOut());
            } catch (IOException ex) {
                if (ex.getMessage().contains("Connection reset by peer")) {
                    toRemove.add(n);
                }

                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Removes all the clients that aren't connected
        toRemove.forEach(n -> {
            ServerDriver.getClientManager().removeClient(n.getID());
        });
    }

    /**
     * Broadcasts a text packout out to all the clients inside this channel.
     *
     * @param packet Packet to be sent
     */
    public void broadcastText(Packet packet) {
        // A set of clients that can possibly be removed if there was an error.
        Set<Client> toRemove = new HashSet<>();

        getClients().stream().forEach(n -> {

            // Assert that the client is still connected
            try {
                packet.send(n.getOut());
            } catch (IOException ex) {
                if (ex.getMessage().contains("Connection reset by peer")) {
                    toRemove.add(n);
                }

                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Removes all the clients that aren't connected
        toRemove.forEach(n -> {
            ServerDriver.getClientManager().removeClient(n.getID());
        });
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public byte getId() {
        return id;
    }

    /**
     * @return the clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /*
    Private Methods
     */
    
    private void updateForPreviewers() {
        previewers.forEach(n -> {
            try {
                PacketEncoder.createClearUserListPacket().send(n.getOut());
                sendPreviewData(n.getOut());
            } catch (IOException ex) {
               
            }
        });
    }

    private void sendPreviewData(OutputStream out) {
        if (clients.isEmpty()) {
            try {
                PacketEncoder.createClearUserListPacket().send(out);
            } catch (IOException ex) {

            }
        }
        clients.forEach(n -> {
            try {
                PacketEncoder.createSendUsersInChannelPacket(n.getName()).send(out);
            } catch (IOException ex) {
                
            }
        });

    }
}
