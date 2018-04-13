package com.github.gielr;


import com.github.gielr.controller.FileCrypter;
import com.github.gielr.controller.ActivityChecker;
import com.github.gielr.controller.UserMenu;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    private static UserMenu userMenu = new UserMenu();
    private static ActivityChecker activityChecker = new ActivityChecker();

    private static Thread thread1 = new Thread(activityChecker);
    public static Thread thread2 = new Thread(userMenu);

    public static void main(String[] args) throws IOException {
        login();

        thread1.start();
        thread2.start();
    }

    private static void login() throws IOException {
        File file = new File("login.txt");
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj hasło do aplikacji: ");
        String passwordToAplicationGivenByUser = sc.nextLine();

        FileCrypter.decrypt("login.txt");
        String passwordToAplication = FileUtils.readFileToString(file);
        while (!passwordToAplicationGivenByUser.equals(passwordToAplication)) {
            System.out.println("To nie jest prawdziew hasło!!!");
            passwordToAplicationGivenByUser = sc.nextLine();
        }
        FileCrypter.encrypt("login.txt");
        System.out.println("Witam\n");
    }
}
