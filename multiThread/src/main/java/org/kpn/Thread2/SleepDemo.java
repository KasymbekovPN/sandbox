package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepDemo {

    public static void main(String[] args) {

        Runnable task = () -> {
            try{
                log.info("sleep");
                Thread.sleep(5000);
                log.info("wake up");
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
