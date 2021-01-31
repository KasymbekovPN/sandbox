package org.kpn.Thread6;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        Runnable action = () -> {log.info("Start");};
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, action);
        Runnable task = () -> {
            try{
                int r = new Random().nextInt(10000);
                log.info("random: {}", r);
                Thread.sleep(r);

                cyclicBarrier.await();
                log.info("Finish: {}", Thread.currentThread().getName());
            } catch (BrokenBarrierException | InterruptedException ex){
                ex.printStackTrace();
            }
        };

        for (int j = 0; j < 2; j++) {
            log.info("--------------------------");
            log.info("limit: {}", cyclicBarrier.getParties());
            for (int i = 0; i < 3; i++) {
                new Thread(task).start();
            }
            cyclicBarrier.reset();
        }
    }
}
