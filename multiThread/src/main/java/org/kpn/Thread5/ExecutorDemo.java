package org.kpn.Thread5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

@Slf4j
public class ExecutorDemo {

    public static void main(String[] args) {

        Runnable task = () -> {log.info("task executed");};
        Executor executor = runnable -> {new Thread(runnable).start();};

        executor.execute(task);
    }
}
