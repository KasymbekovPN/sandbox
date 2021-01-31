package org.kpn.Thread5;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class WorkStealingPoolDemo {

    private static final Map<Integer, Runnable> DEMOS = new HashMap<>(){{
        put(0, WorkStealingPoolDemo::demo0);
        put(1, WorkStealingPoolDemo::demo1);
    }};

    public static void main(String[] args) {
        int key = 1;
        if (DEMOS.containsKey(key)){
            DEMOS.get(key).run();
        } else {
            log.error("Invalid key: {}", key);
        }
    }

    private static void demo0(){
        Object lock = new Object();
        Callable<String> task = () -> {
            log.info("start: {}", Thread.currentThread().getName());
            lock.wait(200);
            log.info("finish: {}", Thread.currentThread().getName());
            return "result";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
    }

    private static void demo1(){
        Object lock = new Object();
        Callable<String> task = () -> {
            log.info("start: {}", Thread.currentThread().getName());
            lock.wait(2000);
            log.info("finish: {}", Thread.currentThread().getName());
            return "result";
        };

        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
