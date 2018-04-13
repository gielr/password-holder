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

        FileUtils.write(path, "uncrypted\n");

        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            String zapis = gson.toJson(entry.getValue());
            FileUtils.write(path, zapis + "\n", true);
        }
        FileCrypter.encrypt("baza.txt");
    }

    public void writeNewPasswordEntryToFile(String fileName, PasswordEntry passwordEntry) throws IOException {
        FileCrypter.decrypt("baza.txt");
        Gson gson = new Gson();
        File path = new File(fileName);

        String zapis = gson.toJson(passwordEntry);
        FileUtils.write(path, zapis + "\n", true);
        FileCrypter.encrypt("baza.txt");
    }


    public PasswordSafe readFileToNewPasswordSafe(String fileName) throws IOException {
        FileCrypter.decrypt("baza.txt");
        File file = new File(fileName);

        String odczyt = FileUtils.readFileToString(file);
        String[] array = odczyt.split("\n");
        FileCrypter.encrypt("baza.txt");
        return createNewPasswordSafeFromArray(array);
    }

    private PasswordSafe createNewPasswordSafeFromArray(String[] array) {
        Gson gson = new Gson();
        PasswordSafe passwordSafe = new PasswordSafe();
        for (int i = 1; i < array.length; i++) {
            PasswordEntry passwordEntry = gson.fromJson(array[i], PasswordEntry.class);
            passwordSafe.add(passwordEntry);
        }
        return passwordSafe;
    }
}

