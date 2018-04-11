package com.github.gielr.controller;

import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSafeController {

    public void writeNewPasswordSafeToFile(String fileName, Map<Long, PasswordEntry> map) throws IOException {
        Gson gson = new Gson();
        File path = new File(fileName);

        FileUtils.write(path, "");

        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            String zapis = gson.toJson(entry.getValue());
            FileUtils.write(path, zapis + "\n", true);
        }
    }

    public void writeNewPasswordEntryToFile(String fileName, PasswordEntry passwordEntry) throws IOException {
        Gson gson = new Gson();
        File path = new File(fileName);

        String zapis = gson.toJson(passwordEntry);
        FileUtils.write(path, zapis + "\n", true);

    }


    public PasswordSafe readFileToNewPasswordSafe(String fileName) throws IOException {
        File file = new File(fileName);

        String odczyt = FileUtils.readFileToString(file);
        String[] array = odczyt.split("\n");

        return createNewPasswordSafeFromArray(array);
    }

    private PasswordSafe createNewPasswordSafeFromArray(String[] array) {
        Gson gson = new Gson();
        PasswordSafe passwordSafe = new PasswordSafe();
        for (int i = 0; i < array.length; i++) {
            PasswordEntry passwordEntry = gson.fromJson(array[i], PasswordEntry.class);
            passwordSafe.add(passwordEntry);
        }
        return passwordSafe;
    }
}

