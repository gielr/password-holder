package com.github.gielr.controller;

import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSafeController {
    public static void main(String[] args) throws IOException {
        PasswordSafe ps = new PasswordSafe();
        PasswordEntry pe = new PasswordEntry("wp", "robert2", "123@321");
        PasswordEntry pe2 = new PasswordEntry("wp2", "robert22", "2123@3212");
        ps.add(1, pe);
        ps.add(2, pe2);

        ps.printMap();

        writeToFile("baza.txt", ps.getMap());


//        System.out.println("---Odczyt---");
//
//        PasswordEntry passwordEntry2 = gson.fromJson(string,PasswordEntry.class);
//
//        System.out.println(passwordEntry2.getLogin());
    }

    public static void writeToFile(String fileName, Map<Integer, PasswordEntry> map) throws IOException {
        Gson gson = new Gson();
        String string = gson.toJson(map);
        File path = new File(fileName);

        for (Map.Entry<Integer, PasswordEntry> entry : map.entrySet()) {
            String zapis = gson.toJson(entry.getValue());
            FileUtils.write(path, zapis + "\n", true);
        }
    }
}

