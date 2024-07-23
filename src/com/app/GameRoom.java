package com.app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameRoom {
    private String roomNumber;
    private Socket player1;
    private Socket player2;

    public GameRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public synchronized boolean addPlayer(Socket player) {
        if (player1 == null) {
            player1 = player;
            return true;
        } else if (player2 == null) {
            player2 = player;
            return true;
        } else {
            return false; // Room is full
        }
    }

    public synchronized void broadcastMessage(String message, Socket sender) {
        try {
            if (player1 != null && player1 != sender) {
                DataOutputStream dos = new DataOutputStream(player1.getOutputStream());
                dos.writeUTF(message);
                dos.flush();
            }
            if (player2 != null && player2 != sender) {
                DataOutputStream dos = new DataOutputStream(player2.getOutputStream());
                dos.writeUTF(message);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
