package org.kpn.Thread4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        log.info("start");

        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Hello world!!!";
        };

        FutureTask<String> futureTask = new FutureTask<>(task);
        new Thread(futureTask).start();

        log.info("{}", futureTask.get());
    }
}
