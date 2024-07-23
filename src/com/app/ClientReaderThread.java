package com.app;

import java.io.*;
import java.net.Socket;
class ClientReaderThread extends Thread {
    Socket socket;

    public ClientReaderThread(Socket socket, Client client) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(msg);
                } catch (Exception e) {
                    System.out.println("已下线");
                    dis.close();
                    is.close();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}