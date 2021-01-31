package org.kpn.Thread3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void log(String text){
        String name = Thread.currentThread().getName();

        String color = ANSI_BLUE;
        int val = Integer.parseInt(name.substring(name.lastIndexOf("-") + 1)) + 1;
        if (val != 0){
            color = ANSI_PURPLE;
        }

        System.out.println(color + name + ": " + text + color);
        try{
            System.out.println(color + name + ": wait for " + val + " sec" + color);
            Thread.sleep(val * 1000L);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lock first = new ReentrantLock();
        Lock second = new ReentrantLock();

        Runnable locker = () -> {
            boolean firstLocked = false;
            boolean secondLocked = false;
            try{
                while (!firstLocked || !secondLocked){
                    firstLocked = first.tryLock(100, TimeUnit.MILLISECONDS);
                    log("First locked: " + firstLocked);
                    secondLocked = second.tryLock(100, TimeUnit.MILLISECONDS);
                    log("Second locked: " + secondLocked);
                }
                first.unlock();
                second.unlock();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        };

        new Thread(locker).start();
        new Thread(locker).start();
    }
}
