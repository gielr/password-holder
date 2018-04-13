package com.github.gielr.controller;

import com.github.gielr.Application;
import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserMenu extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                menuDisplay();
            } catch (IOException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void menuDisplay() throws IOException {
        FileSafeController fsc = new FileSafeController();

        PasswordSafe passwordSafe = fsc.readFileToNewPasswordSafe("baza.txt");
        Scanner sc = new Scanner(System.in);

        while (true) {
            ActivityChecker.timeStart = System.currentTimeMillis();
            System.out.println("1: Dodaj nowy wpis \n" +
                    "2: Usun wpis \n" +
                    "3: Wyswietl wszystkie wpisy \n" +
                    "4: Pobierz haslo \n" +
                    "5: Zmien haslo aplikacji \n" +
                    "6: Koniec programu\n");
            String wybor = sc.nextLine();

            if (Application.thread2.isInterrupted()) {
                System.out.println("Zakonczono dzialanie programu");
                break;
            }

            if (wybor.equals("1")) {
                firstOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("2")) {
                secondOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("3")) {
                thirdOptionForUser(passwordSafe);
            } else if (wybor.equals("4")) {
                fourthOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("5")) {
                fifthOptionForUser(sc);
            } else if (wybor.equals("6")) {
                Application.thread2.interrupt();
                break;
            } else {
                System.out.println("hmmm?\n\n");
            }
            ActivityChecker.timeStart = System.currentTimeMillis();
        }
    }

    private static void firstOptionForUser(PasswordSafe passwordSafe, Scanner sc) throws IOException {
        System.out.println("Podaj nazwe serwisu: ");
        String serviceName = sc.nextLine();
        System.out.println("Podaj login: ");
        String login = sc.nextLine();
        System.out.println("Podaj hasło: ");
        char[] password = sc.nextLine().toCharArray();
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        passwordHolderController.addNewRecord(passwordSafe, serviceName, login, password);
        System.out.println("_____________________________________________\n\n");
    }

    private static void secondOptionForUser(PasswordSafe passwordSafe, Scanner sc) throws IOException {
        System.out.println("Podaj serwis do skasowania: ");
        String serviceName = sc.nextLine();
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        List<PasswordEntry> list = passwordHolderController.listFromMap(passwordSafe, serviceName);
        System.out.println("Dostępne rekordy do skasowania: ");
        passwordHolderController.printListOfPasswords(list);
        System.out.println("Wybierz numer rekordu: ");
        String toDelete = sc.nextLine();
        PasswordEntry pe = list.get(Integer.valueOf(toDelete));
        passwordHolderController.removePassword(passwordSafe, pe);
        System.out.println("Rekord pomyslnie skasowany!\n");
        System.out.println("_____________________________________________\n\n");
    }

    private static void thirdOptionForUser(PasswordSafe passwordSafe) {
        System.out.println("Baza dostepnych rekordów: ");
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        List<PasswordEntry> list = passwordHolderController.listFromPasswordSafe(passwordSafe);
        passwordHolderController.printListOfPasswordsOfPasswordSafe(list);
        System.out.println("_____________________________________________\n\n");
    }

    private static void fourthOptionForUser(PasswordSafe passwordSafe, Scanner sc) {
        System.out.println("Podaj nazwe serwisu do pobrania hasła: ");
        String serviceName = sc.nextLine();
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        List<PasswordEntry> list = passwordHolderController.listFromMap(passwordSafe, serviceName);
        System.out.println("Dostępne rekordy do pobrania: ");
        passwordHolderController.printListOfPasswords(list);
        System.out.println("Wybierz numer rekordu: ");
        String toPrint = sc.nextLine();
        PasswordEntry pe = list.get(Integer.valueOf(toPrint));
        char[] password = pe.getPassword();
        String stringToPrint = "";
        for (int i = 0; i < password.length; i++) {
            stringToPrint += password[i];
        }
        StringSelection selection = new StringSelection(stringToPrint);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        System.out.println("_____________________________________________\n\n");
    }

    private static void fifthOptionForUser(Scanner sc) throws IOException {
        System.out.println("Wybierz co chcesz zrobic: \n1. Zmieniam haslo aplikacji\n2. Powrot do menu");
        File file = new File("login.txt");
        String option = sc.nextLine();

        if (option.equals("1")) {
            System.out.println("Podaj nowe hasło: ");
            String temp1 = sc.nextLine();
            System.out.println("Powtorz haslo: ");
            String temp2 = sc.nextLine();
            if (temp1.equals(temp2)) {
                FileCrypter.decrypt("login.txt");
                FileUtils.write(file, temp1);
                FileCrypter.encrypt("login.txt");
                System.out.println("Hasło zmienione pomyślnie.\n");
            } else {
                System.out.println("Błędne hasło!");
            }

        } else {

        }
    }
}
