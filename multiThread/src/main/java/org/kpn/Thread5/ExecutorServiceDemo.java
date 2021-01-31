package org.kpn.Thread5;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.*;

@Slf4j
public class ExecutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        demo1();
    }

    private static void demo0() throws ExecutionException, InterruptedException {

        Callable<String> task = () -> Thread.currentThread().getName();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Future<String> result = executorService.submit(task);
            log.info("{}: {}", i, result.get());
        }

        executorService.shutdown();
    }

    private static void demo1(){

        Callable<String> task = () -> Thread.currentThread().getName();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        HashMap<Integer, Future<String>> results = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            results.put(i, executorService.submit(task));
        }
        log.info("After cycle");

        boolean run = true;
        while (run){
            run = false;
            for (Future<String> value : results.values()) {
                run |= !value.isDone();
            }
        }

        executorService.shutdown();
        log.info("Done");
    }
}
