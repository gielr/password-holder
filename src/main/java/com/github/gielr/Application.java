package com.github.gielr;

import com.github.gielr.controller.FileSafeController;
import com.github.gielr.controller.PasswordHolderController;
import com.github.gielr.model.PasswordEntry;
import com.github.gielr.model.PasswordSafe;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        PasswordSafe passwordSafe;
        FileSafeController fsc = new FileSafeController();

        passwordSafe = fsc.readFileToNewPasswordSafe("baza.txt");

        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println("1: Dodaj nowy wpis \n" +
                    "2: Usun wpis \n" +
                    "3: Wyswietl wszystkie wpisy \n" +
                    "4: Pobierz haslo \n" +
                    "5: Koniec programu\n");
            String wybor = sc.nextLine();

            if (wybor.equals("1")) {
                firstOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("2")) {
                secondOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("3")) {
                thirdOptionForUser(passwordSafe);
            } else if (wybor.equals("4")) {
                fourthOptionForUser(passwordSafe, sc);
            } else if (wybor.equals("5")) {
                loop = false;
            } else {
                System.out.println("hmmm?\n\n");
            }
        }
    }

    private static void fourthOptionForUser(PasswordSafe passwordSafe, Scanner sc) {
        System.out.println("Podaj nazwe serwisu do pobrania hasła: ");
        String serviceName = sc.nextLine();
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        List<PasswordEntry> list = passwordHolderController.listFromMap(passwordSafe, serviceName);
        System.out.println("Dostępne rekordy do pobrania: ");
        passwordHolderController.printListOfPasswords(list);
        System.out.println("Wybierz numer rekordu: ");
        int toPrint = sc.nextInt();
        PasswordEntry pe = list.get(toPrint);
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

    private static void thirdOptionForUser(PasswordSafe passwordSafe) {
        System.out.println("Baza dostepnych rekordów: ");
        PasswordHolderController passwordHolderController = new PasswordHolderController();
        List<PasswordEntry> list = passwordHolderController.listFromPasswordSafe(passwordSafe);
        passwordHolderController.printListOfPasswordsOfPasswordSafe(list);
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
        int toDelete = sc.nextInt();
        PasswordEntry pe = list.get(toDelete);
        passwordHolderController.removePassword(passwordSafe, pe);
        System.out.println("Rekord pomyslnie skasowany!\n");
        System.out.println("_____________________________________________\n\n");
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
}
