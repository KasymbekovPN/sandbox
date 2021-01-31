package org.kpn.Thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class AtomicDemo {

    public static int value = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {

            for (int i = 0; i < 10000; i++) {
                value++;
                atomicInteger.incrementAndGet();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }

        Thread.sleep(300);
        log.info("value: {}", value);
        log.info("atomicInteger: {}", atomicInteger.get());
    }
}
