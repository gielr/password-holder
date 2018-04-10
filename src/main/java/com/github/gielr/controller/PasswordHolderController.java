package com.github.gielr.controller;

import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;

import java.io.IOException;

public class PasswordHolderController {
    public void addNewRecord(PasswordSafe passwordSafe,String serviceName,String login, char[] password) throws IOException {
        long firstIdToTake = passwordSafe.getMap().keySet().stream().mapToLong(l->l).max().orElse(0L) + 1;

        PasswordEntry pe = new PasswordEntry(firstIdToTake,serviceName,login,password);

        passwordSafe.add(pe);
        FileSafeController fsc = new FileSafeController();
        fsc.writeNewPasswordEntryToFile("baza.txt",pe);
    }
}
