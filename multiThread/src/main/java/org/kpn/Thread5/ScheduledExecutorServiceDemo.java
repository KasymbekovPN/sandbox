package org.kpn.Thread5;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledExecutorServiceDemo {

    private static final Map<Integer, Runnable> DEMOS = new HashMap<>(){{
        put(0, ScheduledExecutorServiceDemo::demo0);
        put(1, ScheduledExecutorServiceDemo::demo1);
    }};

    public static void main(String[] args) {

        int index = 1;
        if (DEMOS.containsKey(index)){
            DEMOS.get(index).run();
        } else {
            log.info("Invalid index : {}", index);
        }
    }

    private static void demo0(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        Callable<String> task = () -> {
            log.info("{}", Thread.currentThread().getName());
            return Thread.currentThread().getName();
        };
        service.schedule(task, 1L, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        log.info("Done");
    }

    private static void demo1(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        Runnable task = () -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}", Thread.currentThread().getName());
        };
        service.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        log.info("Done");
    }
}
