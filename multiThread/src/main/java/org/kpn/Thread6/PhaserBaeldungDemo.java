package org.kpn.Thread6;

import jdk.jfr.Threshold;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@Slf4j
public class PhaserBaeldungDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        Phaser phaser = new Phaser(1);
        assert 0 == phaser.getPhase();

        es.submit(new LongRunningAction("thread-1", phaser));
        es.submit(new LongRunningAction("thread-2", phaser));
        es.submit(new LongRunningAction("thread-3", phaser));

        phaser.arriveAndAwaitAdvance();
        assert 1 == phaser.getPhase();

        es.submit(new LongRunningAction("thread-4", phaser));
        es.submit(new LongRunningAction("thread-5", phaser));
        phaser.arriveAndAwaitAdvance();
        assert 2 == phaser.getPhase();

        phaser.arriveAndDeregister();

        es.shutdown();
    }

    private static class LongRunningAction implements Runnable{
        private final String name;
        private final Phaser phaser;

        public LongRunningAction(String name, Phaser phaser) {
            this.name = name;
            this.phaser = phaser;
            this.phaser.register();
        }

        @SneakyThrows
        @Override
        public void run() {
            log.info("This is phase : {}", phaser.getPhase());
            log.info("Thread {} before long action", name);
            phaser.arriveAndAwaitAdvance();

            int r = new Random().nextInt(3000);
            log.info("r : {}", r);
            Thread.sleep(r);
            phaser.arriveAndDeregister();
        }
    }
}
