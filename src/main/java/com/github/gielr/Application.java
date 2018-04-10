package com.github.gielr;

import com.github.gielr.controller.FileSafeController;
import com.github.gielr.controller.PasswordHolderController;
import com.github.gielr.model.PasswordSafe;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        PasswordSafe passwordSafe;
        FileSafeController fsc = new FileSafeController();

        passwordSafe = fsc.readFileToNewPasswordSafe("baza.txt");

        Scanner sc = new Scanner(System.in);

        boolean loop = true;

        while (loop) {
            System.out.println("1: Dodaj nowy wpis \n2. Usun wpis\n3.Koniec programu");
            String wybor = sc.nextLine();

            if(wybor.equals("1")){
                System.out.println("Podaj nazwe serwisu: ");
                String serviceName = sc.nextLine();
                System.out.println("Podaj login: ");
                String login = sc.nextLine();
                System.out.println("Podaj hasło: ");
                char[] password = sc.nextLine().toCharArray();

                PasswordHolderController passwordHolderController = new PasswordHolderController();
                passwordHolderController.addNewRecord(passwordSafe,serviceName,login,password);
            }
            if(wybor.equals("2")){
                System.out.println("Narazie nic!");
            }
            if(wybor.equals("3")){
                loop = false;
            }
        }

    }
}
