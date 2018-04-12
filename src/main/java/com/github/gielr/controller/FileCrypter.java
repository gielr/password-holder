package com.github.gielr.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FileCrypter {
    private SecretKeySpec secretKey;
    private Cipher cipher;

    private FileCrypter(String secret, int length, String algorithm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] key = new byte[length];
        key = fixSecret(secret, length);
        this.secretKey = new SecretKeySpec(key, algorithm);
        this.cipher = Cipher.getInstance(algorithm);
    }

    private byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
        if (s.length() < length) {
            int missingLength = length - s.length();
            for (int i = 0; i < missingLength; i++) {
                s += " ";
            }
        }
        return s.substring(0, length).getBytes("UTF-8");
    }

    private void encryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    private void decryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    private void writeToFile(File f) throws IOException, IllegalBlockSizeException, BadPaddingException {
        FileInputStream in = new FileInputStream(f);
        byte[] input = new byte[(int) f.length()];
        in.read(input);

        FileOutputStream out = new FileOutputStream(f);
        byte[] output = this.cipher.doFinal(input);
        out.write(output);

        out.flush();
        out.close();
        in.close();
    }

    public static void encrypt() {
        File dir = new File("baza.txt");

        FileCrypter ske;
        try {
            ske = new FileCrypter(":)Q100sz4", 16, "AES");
            try {
                ske.encryptFile(dir);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                System.err.println("Couldn't encrypt " + dir.getName() + ": " + e.getMessage());
            }

        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void decrypt() {
        File dir = new File("baza.txt");

        FileCrypter ske;
        try {
            ske = new FileCrypter(":)Q100sz4", 16, "AES");

            try {
                ske.decryptFile(dir);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                System.err.println("Couldn't decrypt " + dir.getName() + ": " + e.getMessage());
            }

        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }
    }

}
