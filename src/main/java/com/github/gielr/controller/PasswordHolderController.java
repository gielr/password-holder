package com.github.gielr.controller;

import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordHolderController {
    public void addNewRecord(PasswordSafe passwordSafe, String serviceName, String login, char[] password) throws IOException {
        long firstIdToTake = passwordSafe.getMap().keySet().stream().mapToLong(l -> l).max().orElse(0L) + 1;

        PasswordEntry pe = new PasswordEntry(firstIdToTake, serviceName, login, password);

        passwordSafe.add(pe);
        FileSafeController fsc = new FileSafeController();
        fsc.writeNewPasswordEntryToFile("baza.txt", pe);
    }

    public void removePassword(PasswordSafe passwordSafe, PasswordEntry passwordEntry) throws IOException {
        passwordSafe.remove(passwordEntry.getId());
        FileSafeController fileSafeController = new FileSafeController();

        fileSafeController.writeNewPasswordSafeToFile("baza.txt", passwordSafe.getMap());
    }

    public List<PasswordEntry> listFromMap(PasswordSafe passwordSafe, String serviceName) {
        List<PasswordEntry> passwords = new ArrayList<>();
        Map<Long, PasswordEntry> map = passwordSafe.getMap();

        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            if (entry.getValue().getServiceName().equals(serviceName)) {
                passwords.add(entry.getValue());
            }
        }
        return passwords;
    }

    public void printListOfPasswords(List<PasswordEntry> passwords) {
        for (int i = 0; i < passwords.size(); i++) {
            System.out.println(i + ". " + passwords.get(i).getServiceName() + " " + passwords.get(i).getLogin());
        }
    }

    public List<PasswordEntry> listFromPasswordSafe(PasswordSafe passwordSafe) {
        List<PasswordEntry> passwords = new ArrayList<>();
        Map<Long, PasswordEntry> map = passwordSafe.getMap();

        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            passwords.add(entry.getValue());
        }
        return passwords;
    }

    public void printListOfPasswordsOfPasswordSafe(List<PasswordEntry> passwords) {
        for (int i = 0; i < passwords.size(); i++) {
            System.out.println(passwords.get(i).getId() + ". " + passwords.get(i).getServiceName() + " " + passwords.get(i).getLogin());
        }
    }
}
