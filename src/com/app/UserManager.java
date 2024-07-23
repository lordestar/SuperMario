package com.app;

import java.io.*;

public class UserManager {
    public UserManager() {
        File file = new File(USER_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final String USER_FILE = "users.txt";

    public boolean registerUser(String username, String password) throws IOException {
        if (isUserExist(username)) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(username + ":" + password);
            bw.newLine();
        }
        return true;
    }

    public boolean validateUser(String username, String password) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isUserExist(String username) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }
}
