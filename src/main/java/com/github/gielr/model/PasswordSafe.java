package com.github.gielr.model;

import java.util.HashMap;
import java.util.Map;

public class PasswordSafe {
    Map<Integer,PasswordEntry> map = new HashMap<Integer, PasswordEntry>();
    
    public void add(Integer id, PasswordEntry passwordEntry){
        passwordEntry.setId(id);
        map.put(id,passwordEntry);
    }
    
    public void remove(Integer id){
        map.remove(id);
    }
    
    public void printMap(){
        for (Map.Entry<Integer,PasswordEntry> entry : map.entrySet()) {
            System.out.println("ID : " + entry.getKey() + " Count : " + entry.getValue());
        }
    }

    public Map<Integer, PasswordEntry> getMap() {
        return map;
    }
}
