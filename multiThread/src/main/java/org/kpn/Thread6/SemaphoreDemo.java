package org.kpn.Thread6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(0);

        Runnable task = () -> {
            try {
                semaphore.acquire(5);
                log.info("Finished");
                semaphore.release(0);

                log.info("{}", semaphore.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            log.info("{}: {}", i, semaphore.toString());
            semaphore.release(1);
        }



    }
}
