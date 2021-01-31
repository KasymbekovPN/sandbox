package org.kpn.Thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();
        Runnable task = () -> {
            lock.lock();
            log.info("thread");
            lock.unlock();
        };

        lock.lock();
        Thread thread = new Thread(task);
        thread.start();
        log.info("main");
        Thread.sleep(1000);
        lock.unlock();
    }
}
