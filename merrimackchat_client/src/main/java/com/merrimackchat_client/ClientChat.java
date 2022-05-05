/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client;
import com.merrimackchat_client.gui.myGUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 *
 * @author Mark Case
 */
public class ClientChat extends myGUI implements Runnable {
    
    private Socket socket;
    private static final String IP = "localhost";
    private static final int PORT = 5000;
     private BufferedReader in = null;
    private static PrintWriter out = null;
    
    public static String name = "Mark";
    private byte ID;
    
    public ClientChat(){
        
    }
    
    myGUI get = new myGUI();
    
    public static PrintWriter getOut () {
        return out;
    }

    public String getName() {
        return name;
    }
    
    
    
    @Override
    public void run() {
        // Try to establish a connection to server
        //   Should be in run to allow the other chats to open while this
        //   connection is established asynchronously
        try {
            socket = new Socket(IP, PORT);
            System.out.println(this.name + " connected");
            // takes in from terminal 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.err.println("Can't find the host:\n  "
                    + IP + "\n  " + PORT);
        } catch (IOException i) {
            System.err.println("No input");
        }

        // Keep looking at the input stream and wait for a new chat to come through
        while (true) {
            try {
                String chat = in.readLine();
                myGUI.getChatPanel().setText(myGUI.getChatPanel().getText() + chat + "\n" );
            } catch (IOException ex) {
                System.err.println("Could not retrieve additional chat.");
            }
        }
    }
    
        @Override
    public void finalize() {
        // close the connection 
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.err.println("Error closing connection on chat window.");
        } finally {
            try {
                super.finalize();
            } catch (Throwable ex) {
                System.err.println("Runnable can't finalize");
            }
        }
    }
}
