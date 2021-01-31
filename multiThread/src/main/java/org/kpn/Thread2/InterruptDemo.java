package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class InterruptDemo {

    private static final Runnable R0 = () -> {
        try{
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    };

    private static final Runnable R1 = () -> {
        while (!Thread.currentThread().isInterrupted()){
            /* sth do */
        }
        log.info("Finished");
    };

    public static void main(String[] args) {
//        Runnable runnable = R0;
        Runnable runnable = R1;
        Thread thread = new Thread(runnable);
        thread.start();
        thread.interrupt();
    }
}
