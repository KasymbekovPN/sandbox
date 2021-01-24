package org.kpn.Thread1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
public class ThreadPriority {

    public static void main(String[] args) throws InterruptedException {

        long stopTime = Instant.now().getEpochSecond() + 5;
        log.info("{}", stopTime);

        CRun cr0 = new CRun(stopTime);
        CRun cr1 = new CRun(stopTime);
        CRun cr2 = new CRun(stopTime);

        Thread th0 = new Thread(cr0);
        th0.setPriority(1);
        Thread th1 = new Thread(cr1);
        th1.setPriority(5);
        Thread th2 = new Thread(cr2);
        th2.setPriority(10);

        th0.start();
        th1.start();
        th2.start();
    }

    private static class CRun implements Runnable{

        private final long stopTime;

        private long count = 0;
        private volatile boolean active = true;

        public CRun(long stopTime) {
            this.stopTime = stopTime;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (active)
            {
                Thread.sleep(1);
                count++;

                long epochSecond = Instant.now().getEpochSecond();
                if (epochSecond >= stopTime)
                {
                    active = false;
                }
            }

            log.info("{}", count);
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public long getCount() {
            return count;
        }
    }
}
