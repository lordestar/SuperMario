package com.app;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {
    private Map<String, GameRoom> rooms;

    public RoomManager() {
        rooms = new HashMap<>();
    }

    public synchronized GameRoom createRoom(String roomNumber) {
        if (rooms.containsKey(roomNumber)) {
            return null; // Room already exists
        }
        GameRoom room = new GameRoom(roomNumber);
        rooms.put(roomNumber, room);
        return room;
    }

    public synchronized GameRoom joinRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public synchronized void removeRoom(String roomNumber) {
        rooms.remove(roomNumber);
    }

    public synchronized GameRoom getRoom(String roomNumber){
        return rooms.get(getRoom(roomNumber));
    }
}
