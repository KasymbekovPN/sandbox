package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock){
                log.info("thread");
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        for (int i = 0; i < 8; i++) {
            synchronized (lock){
                Thread.sleep(1000);
                log.info("{}", i);
            }
        }
        log.info("is done");
        //<
//        synchronized (lock){
//            for (int i = 0; i < 8; i++) {
//                Thread.sleep(1000);
//                log.info("{}", i);
//            }
//            log.info("is done");
//        }
    }
}
