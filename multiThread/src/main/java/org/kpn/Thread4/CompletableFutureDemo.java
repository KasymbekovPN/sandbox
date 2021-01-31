package org.kpn.Thread4;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        demo3();
    }

    private static void demo0() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completed = CompletableFuture.completedFuture("Simple value");
        log.info("{}", completed.get());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            log.info("run {}", Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            log.info("supply {}", Thread.currentThread().getName());
            return "Value";
        });
        log.info("{}", uCompletableFuture.get());
    }

    private static void demo1(){

        AtomicLong atomicLong = new AtomicLong(0);
        Runnable task = () -> atomicLong.set(new Date().getTime());
        Function<Long, Date> dateConverter = Date::new;
        Consumer<Date> printer = (date) -> {
            log.info("{}", date);
        };

        CompletableFuture.runAsync(task)
                .thenApply((v) -> atomicLong.get())
                .thenApply(dateConverter)
                .thenAccept(printer);
    }

    private static void demo2() throws ExecutionException, InterruptedException {

        Supplier<String> newSup = NewsService::getMessage;

        CompletableFuture<String> reader = CompletableFuture.supplyAsync(newSup);
        CompletableFuture.completedFuture("!!")
                .thenCombine(reader, (a,b) -> b + a)
                .thenAccept(result -> log.info("{}", result))
                .get();
    }

    private static void demo3(){
        CompletableFuture.completedFuture(2L)
                .thenApply((a) -> {
                    throw new IllegalStateException("error");
                }).thenApply((a) -> 3L)
                .exceptionally(ex -> 0L)
                .thenAccept(val -> log.info("{}", val));
    }

    public static class NewsService {
        public static String getMessage() {
            try {
                Thread.sleep(1000);
                return "Some message";
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
