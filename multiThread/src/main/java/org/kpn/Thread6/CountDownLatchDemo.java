package org.kpn.Thread6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable task = () -> {
            try {
                countDownLatch.countDown();
                log.info("count down: {}", countDownLatch.getCount());
                countDownLatch.await();
                log.info("Finished: {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}
