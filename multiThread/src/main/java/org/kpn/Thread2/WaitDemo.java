package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitDemo {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Runnable task = () -> {
            log.info("thread staring");
            synchronized (lock){
                try{
                    lock.wait();
                } catch (InterruptedException ex){
                    log.info("Interrupted");
                }
            }

            log.info("thread ending");
        };

        Thread thread = new Thread(task);
        thread.start();

        Thread.sleep(3000);
        log.info("main");

        synchronized (lock){
            lock.notify();
        }
    }
}
