package com.app;

import java.io.*;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;
    private Server server;
    private RoomManager roomManager;

    public ServerReaderThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.roomManager = new RoomManager(); // Initialize roomManager here
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                String message = dis.readUTF();
                String[] parts = message.split(" ");
                String command = parts[0];
                String roomNumber = parts[1];

                if (command.equals("CREATE")) {
                    GameRoom room = roomManager.createRoom(roomNumber);
                    if (room != null && room.addPlayer(socket)) {
                        // Notify user that room creation was successful
                    }
                } else if (command.equals("JOIN")) {
                    GameRoom room = roomManager.joinRoom(roomNumber);
                    if (room != null && room.addPlayer(socket)) {
                        room.broadcastMessage("Player joined: " + socket.getInetAddress(), socket);
                        // Notify user that joining the room was successful
                    }
                } else {
                    GameRoom room = roomManager.getRoom(roomNumber);
                    if (room != null) {
                        room.broadcastMessage(message, socket);
                    }
                }
            }
        } catch (IOException e) {
            server.removeSocket(socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}