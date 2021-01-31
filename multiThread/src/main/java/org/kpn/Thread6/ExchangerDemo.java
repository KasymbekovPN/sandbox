package org.kpn.Thread6;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;

@Slf4j
public class ExchangerDemo {

    private static final Map<Integer, Runnable> DEMOS = new HashMap<>(){{
        put(0, ExchangerDemo::demo0);
        put(1, ExchangerDemo::demo1);
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
        Exchanger<String> exchanger = new Exchanger<>();
        Runnable task = () -> {
            try {
                Thread thread = Thread.currentThread();
                String withThreadName = exchanger.exchange(thread.getName());
                log.info("{} exchanges with {}", thread.getName(), withThreadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }

    private static void demo1(){
        SynchronousQueue<String> sq = new SynchronousQueue<>();
        Runnable task = () -> {
            try{
                log.info("take: {}", sq.take());
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        };

        new Thread(task).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            sq.put("Some message");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
