package com.github.gielr.controller;

import com.github.gielr.Application;

public class ActivityChecker extends Thread {
    public static long timeStart = System.currentTimeMillis();
    private long timeCurrent = System.currentTimeMillis();

    @Override
    public void run() {
        while ((timeCurrent - timeStart) < 5000) {
            timeCurrent = System.currentTimeMillis();
        }
        Application.thread2.interrupt();
        System.out.println("Jestes wylogowany z powodu bezczynnosci");
    }
}
