package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            log.info("Will be parked");
            LockSupport.park();

            log.info("Unparked");
        };

        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(2000);
        log.info("{}", thread.getState());

        LockSupport.unpark(thread);
        Thread.sleep(2000);
    }
}
