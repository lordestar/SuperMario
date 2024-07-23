package com.app;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends JFrame {
    public static List<Socket> onlineSockets = new ArrayList<>();
    private JTextArea logArea;

    public Server() {
        initializeUI();
        startServer();
    }

    private void initializeUI() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("服务器");

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(logArea);
        this.add(scrollPane);

        this.setVisible(true);
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    private void startServer() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8888);
                log("------服务端启动成功------");

                // 等待连接请求
                while (true) {
                    Socket socket = serverSocket.accept();
                    onlineSockets.add(socket);
                    log("用户 " + socket.getInetAddress() + " 连接成功");
                    new ServerReaderThread(socket, this).start();
                }
            } catch (IOException e) {
                log("服务器启动失败: " + e.getMessage());
            }
        }).start();
    }

    public static void main(String[] args) {
        new Server();
    }

    public void removeSocket(Socket socket) {
        onlineSockets.remove(socket);
        log("用户 " + socket.getInetAddress().getHostAddress() + " 已下线");
    }

    public void broadcastMessage(String message) {
        for (Socket socket : onlineSockets) {
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(message);
                dos.flush();
            } catch (IOException e) {
                log("消息发送失败: " + e.getMessage());
            }
        }
    }
}