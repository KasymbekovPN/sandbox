package org.kpn.Thread6;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;

@Slf4j
public class PhaserDemo {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser();
        phaser.register();
        log.info("Phaser count is {}", phaser.getPhase());

        testPhaser(new Task(phaser));
        testPhaser(new Task(phaser));
        testPhaser(new Task(phaser));

        Thread.sleep(3000);
        phaser.arriveAndDeregister();
        log.info("Phaser count is {}", phaser.getPhase());
    }

    private static void testPhaser(Task task){
        task.getPhaser().register();
        new Thread(task).start();
    }

    private static class Task implements Runnable{

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        public Phaser getPhaser() {
            return phaser;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            log.info("{} arrived", name);
            phaser.arriveAndAwaitAdvance();
            log.info("{} after passing barrier", name);
        }
    }
}
