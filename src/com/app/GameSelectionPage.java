package com.app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import com.game.*;

public class GameSelectionPage extends JFrame {
    private JButton createRoomButton;
    private JButton joinRoomButton;
    private JButton logoutButton;
    private JTextField roomNumberField;
    private Client parent;
//
    public GameSelectionPage(Client parent) {
        this.parent = parent;
        setTitle("Game Mode Selection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        roomNumberField = new JTextField();
        roomNumberField.setToolTipText("Enter room number");

        createRoomButton = new JButton("Create Game Room");
        joinRoomButton = new JButton("Join Game Room");
        logoutButton = new JButton("Logout");

        createRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createGameRoom();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        joinRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    joinGameRoom();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        panel.add(new JLabel("Room Number:"));
        panel.add(roomNumberField);
        panel.add(createRoomButton);
        panel.add(joinRoomButton);
        panel.add(logoutButton);
        add(panel);
    }


    private void createGameRoom() throws IOException {
        /*
        String roomNumber = roomNumberField.getText();
        if (parent.getSocket() == null || parent.getSocket().isClosed()) {
            parent.connectToServer();
        }
        parent.getDataOutputStream().writeUTF("CREATE " + roomNumber);
        parent.getDataOutputStream().flush();
        JOptionPane.showMessageDialog(this, "Room created. Waiting for another player...");
        // Transition to game*/
      //  Start.main(null);
        MyFrame myFrame = new MyFrame();
      //  myFrame.setVisible(true);
    }
    private void joinGameRoom() throws IOException {
        String roomNumber = roomNumberField.getText();
        if (parent.getSocket() == null || parent.getSocket().isClosed()) {
            parent.connectToServer();
        }
        parent.getDataOutputStream().writeUTF("JOIN " + roomNumber);
        parent.getDataOutputStream().flush();
        // Transition to game UI


    }
    private void logout() {
        this.setVisible(false);
        parent.setVisible(true);
    }

}