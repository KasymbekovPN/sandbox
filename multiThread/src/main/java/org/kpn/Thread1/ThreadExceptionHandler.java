package org.kpn.Thread1;

public class ThreadExceptionHandler {

    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        th.setUncaughtExceptionHandler((thread, throwable) -> System.out.println("ERROR: " + throwable.getMessage()));

        System.out.println(2 / 0);
    }
}
