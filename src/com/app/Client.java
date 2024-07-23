package com.app;
import com.game.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Socket socket;
    private DataOutputStream dos;
    private UserManager userManager;

    public Client() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        userManager = new UserManager();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame myFrame = new MyFrame();
                System.out.println("21");
                try {
                    handleLogin();
                    System.out.println("22");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    handleRegister();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    public void connectToServer() throws IOException {
        socket = new Socket("127.0.0.1", 8888);
        dos = new DataOutputStream(socket.getOutputStream());
        new ClientReaderThread(socket, this).start();
    }

    private void handleLogin() throws IOException {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (userManager.validateUser(username, password)) {
            if (socket == null || socket.isClosed()) {
                connectToServer();
            }
            dos.writeUTF("LOGIN " + username + " " + password);
            dos.flush();
            //showGameSelectionPage();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void handleRegister() throws IOException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (userManager.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Registration successful");
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists");
        }
    }

    private void showGameSelectionPage() {
        //MyFrame myFrame = new MyFrame();
        //this.setVisible(false);
        //new GameSelectionPage(this).setVisible(true);
    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getDataOutputStream() {
        return dos;
    }

    public static void main(String[] args) {
        new Client();
    }
}