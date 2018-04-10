package com.github.gielr.model;

import java.util.HashMap;
import java.util.Map;

public class PasswordSafe {
    Map<Long, PasswordEntry> map = new HashMap<>();

    public void add(PasswordEntry passwordEntry) {
        map.put(passwordEntry.getId(), passwordEntry);
    }

    public void remove(Long id) {
        map.remove(id);
    }

    public void printMap() {
        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            System.out.println("ID : " + entry.getKey() + " Count : " + entry.getValue());
        }
    }

    public Map<Long, PasswordEntry> getMap() {
        return map;
    }
}
