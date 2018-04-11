package com.github.gielr.controller;

import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

public class FileSafeControllerTest {
    private FileSafeController fileSafeController = new FileSafeController();
    private PasswordSafe passwordSafe = new PasswordSafe();
    private PasswordEntry passwordEntry1 = new PasswordEntry(1L,"wp","123","123".toCharArray());

    @Test
    public void writeNewPasswordSafeToFile() throws IOException {
        passwordSafe.add(passwordEntry1);
        fileSafeController.writeNewPasswordSafeToFile("test.txt",passwordSafe.getMap());

        PasswordSafe passwordSafe2 = fileSafeController.readFileToNewPasswordSafe("test.txt");

        Assert.assertThat(passwordSafe2.getMap().get(1L).getServiceName(),is("wp"));
    }

    @Test
    public void writeNewPasswordEntryToFile() throws IOException {
        PasswordEntry passwordEntry2 = new PasswordEntry(2L,"onet","xxx111","xxx111".toCharArray());
        fileSafeController.writeNewPasswordEntryToFile("test.txt",passwordEntry2);

        PasswordSafe passwordSafe2 = fileSafeController.readFileToNewPasswordSafe("test.txt");

        Assert.assertThat(passwordSafe2.getMap().get(2L).getServiceName(),is("onet"));
    }

    @Test
    public void readFileToNewPasswordSafeThrowSExceptioWhenNoArguments() throws IOException {
        PasswordSafe passwordSafe2 = fileSafeController.readFileToNewPasswordSafe("test.txt");
        try{
            Assert.assertThat(passwordSafe2.getMap().get(2L).getServiceName(),is("onet"));
        } catch (NullPointerException e){

        }
    }
}
