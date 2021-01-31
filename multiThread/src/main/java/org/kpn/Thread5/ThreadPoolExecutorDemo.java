package org.kpn.Thread5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

    private static void demo0(){
        int threadBound = 2;

        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
//        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(1);
//        BlockingQueue<Runnable> blockingQueue = new SynchronousQueue<>();

        ThreadPoolExecutor ex = new ThreadPoolExecutor(0, threadBound, 0L, TimeUnit.SECONDS, blockingQueue);

        Callable<String> task = () -> {
            log.info("start");
            Thread.sleep(1000);
            log.info("end");
            return Thread.currentThread().getName();
        };

        for (int i = 0; i < threadBound + 1; i++) {
            ex.submit(task);
        }

        ex.shutdown();
    }

    private static void demo1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
//        Callable<String> task = Thread.currentThread()::getName;
        //<
        Callable<String> task = () -> {
            Thread.sleep(100);
            return Thread.currentThread().getName();
        };
        threadPoolExecutor.setRejectedExecutionHandler((runnable, executor) -> {log.warn("Reject : {}", runnable);});

        for (int i = 0; i < 5; i++) {
//            Thread.sleep(150);
            threadPoolExecutor.submit(task);
        }

        threadPoolExecutor.shutdown();
    }
}
