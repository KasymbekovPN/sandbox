package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            log.info("Task is finished");
        };

        Thread thread = new Thread(task);
        thread.start();
//        thread.join();
        log.info("Main is finished");
    }
}
