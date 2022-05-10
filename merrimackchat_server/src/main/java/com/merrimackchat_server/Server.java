package com.merrimackchat_server;

import com.merrimackchat_packet.data.Packet;
import com.merrimackchat_packet.data.PacketEncoder;
import com.merrimackchat_server.exceptions.ServerFullException;
import com.merrimackchat_server.client.Client;
import com.merrimackchat_server.client.ClientThread;
import com.merrimackchat_server.exceptions.ChannelNotFoundException;
import com.merrimackchat_server.util.Pair;
import java.io.*;
import java.net.*;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;

/**
 *
 * @author Derek Costello
 */
@Getter
public class Server extends Thread {

    private Socket connection = null;
    private ServerSocket server = null;
    private int port;

    // Constructor with port 
    public Server(int p, String addr) {
        this.port = p;

        // Attempt to build a server socket to the port
        try {
            System.out.println("[Server] Attempting to connect to " +addr+":"+p);
            server = new ServerSocket(this.port, 5, InetAddress.getByName(addr));
            //server = new ServerSocket(p);
        } catch (IOException ex) {
            System.out.println("Problem occurred when creating server on port: " + this.port);
        }
    }

    /**
     * Server's Run method simply spawns a new thread whenever a client connects
     * to the server.
     */
    @Override
    public void run() {
        while (true) {

            try {
                // Every time a connection is made, spawn a new client thread
                connection = server.accept();

                // Adds the new client to the client manager
                // Has empty as the default name until the client sends their join packet allocating their name.
                Pair<Boolean, Client> value = ServerDriver.getClientManager().clientJoined("empty", connection.getInetAddress().getHostAddress(), connection.getLocalPort(), connection);

                // If the client could not join, ex. server is full
                if (!value.getValue1() || value.getValue2() == null) {
                    // Sends the default server is full packet to a client if the server is full Byte.MIN_VALUE
                    Packet packet = PacketEncoder.createResponseToUserConnectToServerAPakcet(Byte.MIN_VALUE);
                    packet.send(value.getValue2().getOut());
                    throw new ServerFullException("Server " + server.getInetAddress().getHostAddress() + " has reached member capacity at this time.");
                }

                Client client = value.getValue2();
                client.start();

                // Sends the user their initial joining packet
                byte clientID = value.getValue2().getID();
                Packet packet = PacketEncoder.createResponseToUserConnectToServerAPakcet(clientID);
                packet.send(client.getOut());

                /**
                 * ALL TEMPORARY BELOW Allocates the user to a default channel
                 */
                
                // Assigns the client to the temp channel
                //client.setChannel((byte)0);
                // Test to add the cline to the default channel
                //ServerDriver.getChannelManager().getChannels().get((byte) 0).add(clientID);


                System.out.println("User's ID: " + clientID);
                ServerDriver.getChannelManager().getAllChannels(client.getOut());

            } catch (IOException ex) {

                ex.printStackTrace();
                System.err.println("Connection to client can't be established");

                return;
            } catch (NullPointerException ex) {

                ex.printStackTrace();
                System.err.println("Connection to client can't be established, there is a null area.");

                return;
            } catch (ServerFullException ex) {
                System.out.println("The server is full, sorry.");
            }
        }
    }

    /**
     * Broadcasts audio to all clients connected.
     *
     * @param input audio input
     */
    public void broadcast(int input) {
        Collection<Client> clients = ServerDriver.getClientManager().getClientMap().values();

        // Play for every client
        clients.stream().forEach(n -> n.play(input, n.getID()));
    }
}
