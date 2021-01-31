package org.kpn.Thread3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VolatileDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        log.info("Start");

        Runnable task = () -> {
            while (!flag) {
                Thread.onSpinWait();
            }
            log.info("Flag is now true");
        };

        new Thread(task).start();
        Thread.sleep(1000);
        flag = true;
    }
}
